/**
 * 
 */
package com.efurture.kingfisher.view.el;

/**
 * @author ??????
 * @date 2014�?4???14???
 *  */
interface ValueResolver {
	
	public boolean canResolve(Object target, Class<?> cls, String name);
	
	public Object resolve(Object target, Class<?> cls, String name);
}
