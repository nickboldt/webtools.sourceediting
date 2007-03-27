/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.jsdt.web.core.internal.java.search;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.jsdt.internal.core.search.JavaSearchDocument;
import org.eclipse.wst.jsdt.web.core.internal.java.JSPTranslationExtension;

/**
 * Wrapper method to set getPath() path to be the path of the compilation unit
 * for the jsp file. (since it's a final method, it needs to be set via
 * constructor)
 * 
 * @author pavery
 */

/* Used to extend SearchDocument */

public class JavaSearchDocumentDelegate extends JavaSearchDocument {
	
	private JSPSearchDocument fJSPSearchDoc = null;
	
	public JavaSearchDocumentDelegate(JSPSearchDocument jspSearchDoc) {
		
		super(jspSearchDoc.getPath(), jspSearchDoc.getParticipant());
		this.fJSPSearchDoc = jspSearchDoc;
	}
	
	@Override
	public byte[] getByteContents() {
		
		return this.fJSPSearchDoc.getByteContents();
	}
	
	@Override
	public char[] getCharContents() {
		
		return this.fJSPSearchDoc.getCharContents();
	}
	
	@Override
	public String getEncoding() {
		
		return this.fJSPSearchDoc.getEncoding();
	}
	
	public IFile getFile() {
		
		return this.fJSPSearchDoc.getFile();
	}
	
	public String getJavaText() {
		return this.fJSPSearchDoc.getJavaText();
	}
	
	public int getJspOffset(int javaOffset) {
		
		return this.fJSPSearchDoc.getJspOffset(javaOffset);
	}
	
	public JSPTranslationExtension getJspTranslation() {
		
		return this.fJSPSearchDoc.getJSPTranslation();
	}
	
	public void release() {
		this.fJSPSearchDoc.release();
	}
}