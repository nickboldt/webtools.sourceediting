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
package org.eclipse.wst.html.core.tests.parser;

import org.eclipse.wst.xml.core.document.DOMDocument;
import org.eclipse.wst.xml.core.document.DOMDocumentType;
import org.eclipse.wst.xml.core.document.DOMModel;
import org.w3c.dom.Element;

public class DocTypeTest extends ModelTest {
	/**
	 * Constructor for DocTypeTest.
	 * 
	 * @param name
	 */
	public DocTypeTest(String name) {
		super(name);
	}

	public DocTypeTest() {
		super();
	}

	public static void main(java.lang.String[] args) {
		new DocTypeTest().testModel();
	}

	public void testModel() {
		DOMModel model = createHTMLModel();
		try {
			DOMDocument document = model.getDocument();

			DOMDocumentType docType = (DOMDocumentType) document.createDoctype("HTML");
			document.appendChild(docType);
			Element html = document.createElement("HTML");
			document.appendChild(html);

			printSource(model);
			printTree(model);

			docType.setSystemId("sytem");

			printSource(model);
			printTree(model);

			docType.setPublicId("public");

			printSource(model);
			printTree(model);

			document.insertBefore(document.createTextNode(" "), docType);

			printSource(model);
			printTree(model);

			saveAndCompareTestResults();
		}
		finally {
			model.releaseFromEdit();
		}

	}
}