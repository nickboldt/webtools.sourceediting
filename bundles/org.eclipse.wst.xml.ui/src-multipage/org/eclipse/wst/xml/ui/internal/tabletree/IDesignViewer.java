/*****************************************************************************
 * Copyright (c) 2004 IBM Corporation and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms
 * of the Eclipse Public License v1.0 which accompanies this distribution, and
 * is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ****************************************************************************/
package org.eclipse.wst.xml.ui.internal.tabletree;



import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wst.sse.ui.internal.ViewerSelectionManager;


public interface IDesignViewer {
	public Control getControl();

	String getTitle();

	void setDocument(IDocument document);

	void setViewerSelectionManager(ViewerSelectionManager viewerSelectionManager);
}