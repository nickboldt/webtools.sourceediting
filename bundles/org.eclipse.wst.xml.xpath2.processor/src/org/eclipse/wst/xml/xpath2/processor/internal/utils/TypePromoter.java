/*******************************************************************************
 * Copyright (c) 2009 Jesper Steen Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *     Jesper Steen Moller - bug 281028 - avg/min/max/sum work
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.utils;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;

/**
 * Generic type promoter for handling subtype substitution and type promotions for functions and operators.
 */
public abstract class TypePromoter {
	private Class<? extends AnyAtomicType> targetType = null;

	abstract public AnyAtomicType doPromote(AnyAtomicType value);	

	public final AnyAtomicType promote(AnyType value) {
		// This is a short cut, really
		if (value.getClass() == getTargetType()) return (AnyAtomicType)value;

		return doPromote(atomize(value));
	}

	/**
	 * @param typeToConsider The 
	 * @return The supertype to treat it as (i.e. if a xs:nonNegativeInteger is treated as xs:number)
	 */
	abstract protected Class<? extends AnyAtomicType> substitute(Class<? extends AnyAtomicType> typeToConsider);	

	abstract protected boolean checkCombination(Class<? extends AnyAtomicType> newType);
		
	public void considerType(Class<? extends AnyAtomicType> typeToConsider) throws DynamicError {
		Class<? extends AnyAtomicType> baseType = substitute(typeToConsider);
		
		if (baseType == null) {
			throw DynamicError.argument_type_error(typeToConsider);
		}
		
		if (targetType == null) {
			targetType = baseType;
		} else {
			if (! checkCombination(baseType)) {
				throw DynamicError.argument_type_error(typeToConsider);
			}
		}
	}
	
	public void considerTypes(Collection<Class<? extends AnyAtomicType>> typesToConsider) throws DynamicError {
		for (Class<? extends AnyAtomicType> type : typesToConsider) {
			considerType(type);
		}
	}
	
	public void considerSequence(ResultSequence sequenceToConsider) throws DynamicError {
		for (int i = 0; i < sequenceToConsider.size(); ++i) {
			AnyType item = sequenceToConsider.get(i);
			considerValue(item);
		}
	}
	
	public Class<? extends AnyAtomicType> getTargetType() {
		return targetType;
	}
	
	protected void setTargetType(Class<? extends AnyAtomicType> class1) {
		this.targetType = class1;
	}

	public AnyAtomicType atomize(AnyType at) {
		if (at instanceof NodeType) {
			return (AnyAtomicType)((NodeType)at).typed_value().first();
		}
		else {
			return (AnyAtomicType)at;
		}
	}
	
	public void considerValue(AnyType at) throws DynamicError {
		considerType(atomize(at).getClass());
	}


}