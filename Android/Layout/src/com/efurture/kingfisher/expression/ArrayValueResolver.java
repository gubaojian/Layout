/**
 * 
 */
package com.efurture.kingfisher.expression;

import java.lang.reflect.Array;

import com.efurture.kingfisher.internal.GLog;


class ArrayValueResolver implements ValueResolver{

	
	@Override
	public boolean canResolve(Object target, Class<?> cls, String name) {
		return cls.isArray();
	}

	
	@Override
	public Object resolve(Object target, Class<?> cls, String name) {
		try {
			int index = Integer.parseInt(name);
			return Array.get(target, index);
		}
		catch (Exception e) {
			GLog.w(e.getMessage());
			return null;
		}
	}

}
