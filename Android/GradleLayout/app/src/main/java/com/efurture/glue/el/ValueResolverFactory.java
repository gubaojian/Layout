
package com.efurture.glue.el;

import java.util.ArrayList;
import java.util.List;


class ValueResolverFactory {
	
	private static List<ValueResolver> valueResolvers = new ArrayList<ValueResolver>(4);
	static{
		valueResolvers.add(new MapValueResolver());
		valueResolvers.add(new ListValueResolver());
		valueResolvers.add(new ArrayValueResolver());
		valueResolvers.add(new DefaultValueResolver());
	}
	
	
	public static Object getValue(final Object target, final String name){
		if (target == null || name == null) {
			return null;
		}
		Class<?> cls = target.getClass();
		for (ValueResolver valueResolver : valueResolvers) {
			if (valueResolver.canResolve(target, cls, name)) {
				return valueResolver.resolve(target, cls, name);
			}
		}
		return null;
	}

}
