/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *******************************************************************************/
package org.eclipse.jst.jsp.core.contentmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.filebuffers.IFileBufferListener;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsp.core.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.parser.JSPSourceParser;
import org.eclipse.wst.sse.core.internal.text.BasicStructuredDocument;
import org.eclipse.wst.sse.core.text.IStructuredDocument;
import org.eclipse.wst.sse.core.util.Assert;

/**
 * Provides a direct mapping from IStructuredDocument to supporting
 * TLDCMDocumentManager.
 * 
 * Listens to the creation of JSP type TextFileBuffers and forces a text-less
 * reparse after connecting taglib-supporting listeners. Connecting the
 * listeners before the text is set would be ideal, but there is no way to
 * look up taglib references since the location is not yet knowable. Since
 * taglibs can affect the parsing of the document, a reparse is currently
 * required to react to custom tags with tagdependent content.
 * 
 * TODO: Remove the reparse penalty.
 */
public class TaglibController implements IDocumentSetupParticipant {

	class DocumentInfo {
		IStructuredDocument document;
		ITextFileBuffer textFileBuffer;
		TLDCMDocumentManager tldDocumentManager;
	}

	class FileBufferListener implements IFileBufferListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#bufferContentAboutToBeReplaced(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferContentAboutToBeReplaced(IFileBuffer buffer) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#bufferContentReplaced(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferContentReplaced(IFileBuffer buffer) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#bufferCreated(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferCreated(IFileBuffer buffer) {
			if (buffer instanceof ITextFileBuffer) {
				IDocument document = ((ITextFileBuffer) buffer).getDocument();
				// ignore non-JSP documents
				if (!fJSPdocuments.contains(document))
					return;
				Assert.isTrue(document instanceof IStructuredDocument);
				DocumentInfo info = new DocumentInfo();
				info.document = (IStructuredDocument) document;
				info.textFileBuffer = (ITextFileBuffer) buffer;
				info.tldDocumentManager = new TLDCMDocumentManager();
				info.tldDocumentManager.setSourceParser((JSPSourceParser) info.document.getParser());
				fDocumentMap.put(document, info);
				if (document instanceof BasicStructuredDocument) {
					((BasicStructuredDocument) document).reparse(this);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#bufferDisposed(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void bufferDisposed(IFileBuffer buffer) {
			if (buffer instanceof ITextFileBuffer) {
				IDocument document = ((ITextFileBuffer) buffer).getDocument();
				if (!fJSPdocuments.remove(document))
					return;
			}
			synchronized (fDocumentMap) {
				Object[] mapEntrys = fDocumentMap.entrySet().toArray();
				boolean removed = false;
				for (int i = 0; i < mapEntrys.length && !removed; i++) {
					DocumentInfo info = (DocumentInfo) ((Map.Entry) mapEntrys[i]).getValue();
					if (info != null && info.textFileBuffer.equals(buffer)) {
						fDocumentMap.remove(mapEntrys[i]);
						removed = true;
					}
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#dirtyStateChanged(org.eclipse.core.filebuffers.IFileBuffer,
		 *      boolean)
		 */
		public void dirtyStateChanged(IFileBuffer buffer, boolean isDirty) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#stateChangeFailed(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void stateChangeFailed(IFileBuffer buffer) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#stateChanging(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void stateChanging(IFileBuffer buffer) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#stateValidationChanged(org.eclipse.core.filebuffers.IFileBuffer,
		 *      boolean)
		 */
		public void stateValidationChanged(IFileBuffer buffer, boolean isStateValidated) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#underlyingFileDeleted(org.eclipse.core.filebuffers.IFileBuffer)
		 */
		public void underlyingFileDeleted(IFileBuffer buffer) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.filebuffers.IFileBufferListener#underlyingFileMoved(org.eclipse.core.filebuffers.IFileBuffer,
		 *      org.eclipse.core.runtime.IPath)
		 */
		public void underlyingFileMoved(IFileBuffer buffer, IPath path) {
		}


	}

	static TaglibController _instance = null;

	public static ITextFileBuffer getFileBuffer(IDocument document) {
		DocumentInfo info = (DocumentInfo) _instance.fDocumentMap.get(document);
		if (info != null)
			return info.textFileBuffer;
		return null;
	}

	/**
	 * @param manager
	 * @return
	 */
	public static ITextFileBuffer getFileBuffer(TLDCMDocumentManager manager) {
		ITextFileBuffer buffer = null;
		synchronized (_instance.fDocumentMap) {
			Iterator docInfos = _instance.fDocumentMap.values().iterator();
			while (docInfos.hasNext()) {
				DocumentInfo info = (DocumentInfo) docInfos.next();
				if (info.tldDocumentManager == manager)
					buffer = info.textFileBuffer;
			}
		}
		return buffer;
	}

	public static TLDCMDocumentManager getTLDCMDocumentManager(IDocument document) {
		DocumentInfo info = (DocumentInfo) _instance.fDocumentMap.get(document);
		if (info != null)
			return info.tldDocumentManager;
		return null;
	}

	public static void shutdown() {
		FileBuffers.getTextFileBufferManager().removeFileBufferListener(_instance.fBufferListener);
		_instance = null;
	}

	public static void startup() {
		_instance = new TaglibController();
		FileBuffers.getTextFileBufferManager().addFileBufferListener(_instance.fBufferListener);
	}

	IFileBufferListener fBufferListener;
	Map fDocumentMap;

	List fJSPdocuments;

	public TaglibController() {
		super();
		fBufferListener = new FileBufferListener();
		fJSPdocuments = new ArrayList(1);
		fDocumentMap = new HashMap(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup(IDocument document) {
		_instance.fJSPdocuments.add(document);
	}

}
