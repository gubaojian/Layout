/**
 * 
 */
package com.efurture.kingfisher.view.el;

import java.util.Map;


class MapValueResolver implements ValueResolver{

	
	@Override
	public boolean canResolve(Object target, Class<?> cls, String name) {
		return target instanceof Map<?, ?>;
	}

	
	@Override
	public Object resolve(Object target, Class<?> cls, String name) {
		Map<?,?> map = (Map<?, ?>) target;
		return map.get(name);
	}

}
