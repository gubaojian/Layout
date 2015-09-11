/**
 * 
 */
package com.efurture.kingfisher.view.el;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.efurture.kingfisher.GLog;

import android.support.v4.util.LruCache;


class DefaultValueResolver  implements ValueResolver{
	
	private static final LruCache<String, Method> methodCache = new LruCache<String, Method>(64);
	private static final LruCache<String, String> notExistMethodCache = new LruCache<String, String>(16);
	private static final LruCache<String, Field> fieldCache = new LruCache<String, Field>(32);
	private static final LruCache<String, String> notExistFieldCache = new LruCache<String, String>(16);
	
	
	
	private static final String MARK = "";
	
	@Override
	public boolean canResolve(Object target, Class<?> cls, String name) {
		return true;
	}

	@Override
	public Object resolve(Object target, Class<?> cls, String name) {
		String key = cls.getName() +  "[]" + name;
		Method method = methodCache.get(key);
		if (method != null) {
			try {
				return method.invoke(target);
			}catch (Exception e) {
				GLog.w(e.getMessage());
				return null;
			}
		}
		
		Field field = fieldCache.get(key);
		if (field != null) {
			try {
				return field.get(target);
			}
			catch (Exception e) {
				GLog.w(e.getMessage());
				return null;
			}
		}
		
		if (notExistMethodCache.get(key) != MARK) {
			try {
				String methodName = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
				method = cls.getMethod(methodName);
				methodCache.put(key, method);
				return method.invoke(target);
			}catch (NoSuchMethodException notExistGetAccessError){
			    try {
			    	    String methodName = "is" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
					method = cls.getMethod(methodName);
					methodCache.put(key, method);
					return method.invoke(target);
				}
				catch (NoSuchMethodException notExistIsAccessError) {
					notExistMethodCache.put(key, MARK);
				}catch (Exception invokeError) {
					GLog.w(invokeError.getMessage());
					return null;
				}
			}catch (Exception e) {
				GLog.w(e.getMessage());
				return null;
			}
		}

		if (notExistFieldCache.get(key) != MARK) {
			try {
				field = cls.getField(name);
				fieldCache.put(key, field);
				return field.get(target);
			}catch(NoSuchFieldException e){
				notExistFieldCache.put(key, MARK);
			}catch (Exception e) {
				GLog.w(e.getMessage());
				return null;
			}
		}
		return null;
	}

}
