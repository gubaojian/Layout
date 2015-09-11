/**
 * 
 */
package com.efurture.kingfisher.view.el;

import java.lang.reflect.Array;

import com.efurture.kingfisher.GLog;


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
