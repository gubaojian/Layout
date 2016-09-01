/**
 * 
 */
package com.efurture.glue.bind.el;

/**
 * @author jianbai.gbj
 * @date 2014/05/06
 *  */
public interface ValueResolver {
	
	public boolean canResolve(Object target, Class<?> cls, String name);
	
	public Object resolve(Object target, Class<?> cls, String name);
}
