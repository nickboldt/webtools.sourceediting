/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.wst.html.ui.internal.projection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.wst.sse.core.INodeAdapter;
import org.eclipse.wst.sse.core.INodeNotifier;
import org.eclipse.wst.sse.core.IndexedRegion;
import org.eclipse.wst.html.ui.internal.Logger;
import org.w3c.dom.Node;

/**
 * Updates projection annotation model with projection annotations for this
 * adapter node's children
 */
public class ProjectionModelNodeAdapterHTML implements INodeAdapter {

	private class TagProjectionAnnotation extends ProjectionAnnotation {
		private Node fNode;

		public TagProjectionAnnotation(Node node, boolean isCollapsed) {
			super(isCollapsed);
			fNode = node;
		}

		public Node getNode() {
			return fNode;
		}

		public void setNode(Node node) {
			fNode = node;
		}
	}

	// copies of this class located in:
	// org.eclipse.wst.html.ui.internal.projection
	// org.eclipse.jst.jsp.ui.internal.projection
	private final static boolean debugProjectionPerf = "true".equalsIgnoreCase(Platform.getDebugOption("org.eclipse.wst.html.ui/projectionperf")); //$NON-NLS-1$ //$NON-NLS-2$

	private ProjectionModelNodeAdapterFactoryHTML fAdapterFactory;
	private Map fTagAnnotations = new HashMap();

	public ProjectionModelNodeAdapterHTML(ProjectionModelNodeAdapterFactoryHTML factory) {
		fAdapterFactory = factory;
	}

	/**
	 * Create a projection position from the given node. Able to get
	 * projection position if node isNodeProjectable.
	 * 
	 * @param node
	 * @return null if no projection position possible, a Position otherwise
	 */
	private Position createProjectionPosition(Node node) {
		Position pos = null;
		if (fAdapterFactory.isNodeProjectable(node) && node instanceof IndexedRegion) {
			IDocument document = fAdapterFactory.getProjectionViewer().getDocument();
			if (document != null) {
				IndexedRegion inode = (IndexedRegion) node;
				int start = inode.getStartOffset();
				int end = inode.getEndOffset();
				if (start >= 0 && start < end) {
					try {
						int startLine = document.getLineOfOffset(start);
						int endLine = document.getLineOfOffset(end);
						if (endLine + 1 < document.getNumberOfLines()) {
							// projection_TODO allow only multi-line
							// projection
							// // check if projection start/end region is on
							// the same line
							// if (startLine < endLine) {
							int offset = document.getLineOffset(startLine);
							int endOffset = document.getLineOffset(endLine + 1);
							pos = new Position(offset, endOffset - offset);
							// }
						}
					} // }
					catch (BadLocationException x) {
						Logger.log(Logger.WARNING_DEBUG, null, x);
					}
				}
			}
		}
		return pos;
	}

	/**
	 * Find TagProjectionAnnotation for node in the current list of projection
	 * annotations for this adapter
	 * 
	 * @param node
	 * @return TagProjectionAnnotation
	 */
	private TagProjectionAnnotation getExistingAnnotation(Node node) {
		TagProjectionAnnotation anno = null;

		if ((node != null) && (!fTagAnnotations.isEmpty())) {
			Iterator it = fTagAnnotations.keySet().iterator();
			while (it.hasNext() && anno == null) {
				TagProjectionAnnotation a = (TagProjectionAnnotation) it.next();
				Node n = a.getNode();
				if (node.equals(n)) {
					anno = a;
				}
			}
		}
		return anno;
	}

	public boolean isAdapterForType(Object type) {
		return type == ProjectionModelNodeAdapterHTML.class;
	}

	public void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos) {
		// check if folding is even enabled, if not, just ignore notifyChanged
		// events
		if (fAdapterFactory.getProjectionViewer() == null) {
			return;
		}

		if ((eventType == INodeNotifier.STRUCTURE_CHANGED) && (notifier instanceof Node)) {
			updateAdapter((Node) notifier);
		}

		// projection_TODO allow only multi-line projection
		// if ((eventType == INodeNotifier.CONTENT_CHANGED) && (changedFeature
		// instanceof Node)) {
		// Node node = (Node) changedFeature;
		// if (node.getNodeType() == Node.TEXT_NODE)
		// node = node.getParentNode();
		// if (node != null)
		// updateOneNode(node);
		// }
	}

	/**
	 * Update the projection annotation of all the nodes that are children of
	 * node
	 * 
	 * @param node
	 */
	void updateAdapter(Node node) {
		long start = System.currentTimeMillis();

		Map additions = new HashMap();
		Map projectionAnnotations = new HashMap();

		// go through immediate child nodes and figure out projection
		// model annotations
		if (node != null) {
			Node childNode = node.getFirstChild();
			while (childNode != null) {
				Position newPos = createProjectionPosition(childNode);
				if (newPos != null) {
					TagProjectionAnnotation newAnnotation = new TagProjectionAnnotation(childNode, false);
					TagProjectionAnnotation existing = getExistingAnnotation(childNode);
					if (existing == null) {
						// add to map containing all annotations for this
						// adapter
						projectionAnnotations.put(newAnnotation, newPos);
						// add to map containing annotations to add
						additions.put(newAnnotation, newPos);
					}
					else {
						// add to map containing all annotations for this
						// adapter
						projectionAnnotations.put(existing, newPos);
						// remove from map containing annotations to delete
						fTagAnnotations.remove(existing);
					}
				}
				childNode = childNode.getNextSibling();
			}

			// in the end, want to delete anything leftover in old list, add
			// everything in additions, and update everything in
			// projectionAnnotations
			ProjectionAnnotation[] oldList = null;
			if (!fTagAnnotations.isEmpty()) {
				oldList = (ProjectionAnnotation[]) fTagAnnotations.keySet().toArray(new ProjectionAnnotation[0]);
			}
			ProjectionAnnotation[] modifyList = null;
			if (!projectionAnnotations.isEmpty()) {
				modifyList = (ProjectionAnnotation[]) projectionAnnotations.keySet().toArray(new ProjectionAnnotation[0]);
			}
			ProjectionViewer viewer = fAdapterFactory.getProjectionViewer();
			ProjectionAnnotationModel annotationModel = viewer.getProjectionAnnotationModel();
			annotationModel.modifyAnnotations(oldList, additions, modifyList);
		}

		// save new list of annotations
		fTagAnnotations = additions;

		long end = System.currentTimeMillis();
		if (debugProjectionPerf) {
			String nodeName = node != null ? node.getNodeName() : "null";
			System.out.println("ProjectionModelNodeAdapterHTML.updateAdapter (" + nodeName + "):" + (end - start));
		}
	}

	// projection_TODO allow only multi-line projection
	// private void updateOneNode(Node node) {
	// TagProjectionAnnotation existing = getExistingAnnotation(node);
	// if (isNodeProjectable(node)) {
	// Position pos = createProjectionPosition(node);
	// // if node > 1 line and not existing, create and add
	// if (pos != null && existing == null) {
	// TagProjectionAnnotation newAnnotation = new
	// TagProjectionAnnotation(node, false);
	// fAdapterFactory.getProjectionViewer().getProjectionAnnotationModel().addAnnotation(newAnnotation,
	// pos);
	// fTagAnnotations.put(newAnnotation, pos);
	// }
	// // if node == 1 line and existing, remove
	// if (pos == null && existing != null) {
	// fAdapterFactory.getProjectionViewer().getProjectionAnnotationModel().removeAnnotation(existing);
	// fTagAnnotations.remove(existing);
	// }
	// }
	// }
}
