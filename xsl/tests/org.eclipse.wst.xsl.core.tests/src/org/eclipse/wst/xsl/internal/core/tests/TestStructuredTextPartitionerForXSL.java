/*******************************************************************************
 * Copyright (c) 2009 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xsl.internal.core.tests;

import org.eclipse.jface.text.IDocumentPartitioner;
import static org.junit.Assert.*;
import org.eclipse.wst.xsl.core.internal.text.rules.StructuredTextPartitionerForXSL;
import org.junit.Test;

public class TestStructuredTextPartitionerForXSL  {

	@Test
	public void testXSLNewInstance() {
		StructuredTextPartitionerForXSL textPartioner = new StructuredTextPartitionerForXSL();
		IDocumentPartitioner documentPartitioner = textPartioner.newInstance();
		assertTrue("Did not retrieve StructuredTextPartitionerForXSL", documentPartitioner instanceof StructuredTextPartitionerForXSL);
	}
}
