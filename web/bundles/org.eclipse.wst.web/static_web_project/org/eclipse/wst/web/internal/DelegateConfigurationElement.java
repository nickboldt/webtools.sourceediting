/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on December 14, 2004
 */
package org.eclipse.wst.web.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.InvalidRegistryObjectException;

/**
 * An object which represents the user-defined contents of an extension in a plug-in manifest.
 */
public class DelegateConfigurationElement implements IConfigurationElement {

	private final IConfigurationElement delegateElement;
	private static final String DELEGATE_NAME = "delegateConfigurationElement"; //$NON-NLS-1$ 
	private static final String DELEGATE_NAMESPACE = "delegateConfigurationElementNamespace"; //$NON-NLS-1$ 
	private static final String DELEGATE_NULL_STRING = "delegateConfigurationElement: NULL"; //$NON-NLS-1$ 

	public DelegateConfigurationElement(IConfigurationElement aDelegate) {
		delegateElement = aDelegate;
	}

	/**
	 * @param propertyName
	 * @return
	 * @throws org.eclipse.core.runtime.CoreException
	 */
	public Object createExecutableExtension(String propertyName) throws CoreException {
		if (delegateElement == null)
			return null;
		return delegateElement.createExecutableExtension(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (delegateElement == null)
			return false;
		return delegateElement.equals(obj);
	}

	/**
	 * @param name
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String getAttribute(String name) throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return null;
		return delegateElement.getAttribute(name);
	}

	/**
	 * @param name
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String getAttributeAsIs(String name) throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return null;
		return delegateElement.getAttributeAsIs(name);
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String[] getAttributeNames() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return new String[0];
		return delegateElement.getAttributeNames();
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return new IConfigurationElement[0];
		return delegateElement.getChildren();
	}

	/**
	 * @param name
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public IConfigurationElement[] getChildren(String name) throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return new IConfigurationElement[0];
		return delegateElement.getChildren(name);
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public IExtension getDeclaringExtension() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			throw new InvalidRegistryObjectException();
		return delegateElement.getDeclaringExtension();
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String getName() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return DELEGATE_NAME;
		return delegateElement.getName();
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String getNamespace() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return DELEGATE_NAMESPACE;
		return delegateElement.getNamespace();
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public Object getParent() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return null;
		return delegateElement.getParent();
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String getValue() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return null;
		return delegateElement.getValue();
	}

	/**
	 * @return
	 * @throws org.eclipse.core.runtime.InvalidRegistryObjectException
	 */
	public String getValueAsIs() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return null;
		return delegateElement.getValueAsIs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (delegateElement == null)
			return -1;
		return delegateElement.hashCode();
	}

	/**
	 * @return
	 */
	public boolean isValid() {

		if (delegateElement == null)
			return false;
		return delegateElement.isValid();
	}

	/**
	 * @return 
	 */
	public int getHandleId() {
		if( delegateElement == null )
			return -1;
		return delegateElement.getHandleId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (delegateElement == null)
			return DELEGATE_NULL_STRING;
		return delegateElement.toString();
	}

	public IConfigurationElement toEquinox() {
		return null;
	}

	public IContributor getContributor() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			throw new InvalidRegistryObjectException();
		return delegateElement.getContributor();
	}

	public String getNamespaceIdentifier() throws InvalidRegistryObjectException {
		if (delegateElement == null)
			return DELEGATE_NAMESPACE;
		return delegateElement.getNamespaceIdentifier();
	}

	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=296122
	public String getAttribute(String attrName, String locale) throws InvalidRegistryObjectException {
		if (delegateElement == null) return null;
		return delegateElement.getAttribute(attrName, locale);
	}

	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=296122
	public String getValue(String locale) throws InvalidRegistryObjectException {
		if (delegateElement == null) return null;
		return delegateElement.getValue(locale);
	}

}
