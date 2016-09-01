/**
 * 
 */
package com.efurture.glue.bind.el;

import java.lang.reflect.Array;


public class ArrayValueResolver implements ValueResolver{

	
	@Override
	public boolean canResolve(Object target, Class<?> cls, String name) {
		return cls.isArray();
	}

	
	@Override
	public Object resolve(Object target, Class<?> cls, String name) {
		int index = Integer.parseInt(name);
		return Array.get(target, index);
	}

}
