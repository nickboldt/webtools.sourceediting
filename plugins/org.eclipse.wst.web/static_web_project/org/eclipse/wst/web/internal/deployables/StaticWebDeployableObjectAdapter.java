/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.wst.web.internal.deployables;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.model.ModuleArtifactAdapterDelegate;

public class StaticWebDeployableObjectAdapter extends ModuleArtifactAdapterDelegate implements IAdapterFactory

{

	public StaticWebDeployableObjectAdapter() {
		super();
	}

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[]{IStaticWebModuleArtifact.class};
	}

	@Override
	public IModuleArtifact getModuleArtifact(Object obj) {
		return StaticWebDeployableObjectAdapterUtil.getModuleObject(obj);
	}
}