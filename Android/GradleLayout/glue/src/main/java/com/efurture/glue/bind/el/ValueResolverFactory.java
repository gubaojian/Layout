
package com.efurture.glue.bind.el;

import com.efurture.glue.GLog;

import java.util.ArrayList;
import java.util.List;


public class ValueResolverFactory {
	
	private static List<ValueResolver> valueResolvers = new ArrayList<ValueResolver>(4);
	static{
		valueResolvers.add(new JSONValueResolver());
		valueResolvers.add(new MapValueResolver());
		valueResolvers.add(new ListValueResolver());
		valueResolvers.add(new ArrayValueResolver());
		valueResolvers.add(new DefaultValueResolver());
	}
	
	
	public static Object getValue(final Object target, final String name){
		if (target == null || name == null) {
			return null;
		}
		try{
			Class<?> cls = target.getClass();
			for (ValueResolver valueResolver : valueResolvers) {
				if (valueResolver.canResolve(target, cls, name)) {
					return valueResolver.resolve(target, cls, name);
				}
			}
			return null;
		}catch (Exception e) {
			GLog.e("ValueResolverFactory resolve value error", e);
			return  null;
		}
	}

}
