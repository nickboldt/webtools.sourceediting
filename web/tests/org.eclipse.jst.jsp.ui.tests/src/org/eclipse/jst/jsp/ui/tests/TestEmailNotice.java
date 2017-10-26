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
package org.eclipse.jst.jsp.ui.tests;

import junit.framework.TestCase;

public class TestEmailNotice extends TestCase {
	private boolean sendNotifications = false;

	public TestEmailNotice() {
		super();
	}

	public TestEmailNotice(String name) {
		super(name);
	}

	public void testEmail() {
		assertFalse("Simple test to be sure email gets sent on failure", sendNotifications);
	}
}