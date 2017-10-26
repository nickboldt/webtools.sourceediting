/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.css.core.internal.provisional.document;



import org.w3c.dom.css.CSSValueList;

/**
 * 
 */
public interface ICSSValueList extends ICSSValue, CSSValueList {

	/**
	 * @return org.w3c.dom.css.CSSPrimitiveValue
	 * @param value
	 *            org.w3c.dom.css.CSSPrimitiveValue
	 */
	ICSSPrimitiveValue appendValue(ICSSPrimitiveValue value);

	/**
	 * @return org.w3c.dom.css.CSSPrimitiveValue
	 * @param value
	 *            org.w3c.dom.css.CSSPrimitiveValue
	 */
	ICSSPrimitiveValue removeValue(ICSSPrimitiveValue value);
}
