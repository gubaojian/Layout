
package com.efurture.kingfisher.view.el;

import java.util.StringTokenizer;


public class ElUtil {
	
	private static final String DELIMITER = " ${.[]}";
	
	
	public static boolean isEl(final String el){
		if(el == null) {
			return false;
		}
		return el.trim().startsWith("$");
	}
	
	
	
	public static Object getElValue(final Object data, final String el){
		if (data == null || el == null) {
			return null;
		}
		Object value = data;
		boolean start = false;
		StringTokenizer tokenizer = new StringTokenizer(el, DELIMITER, true);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (token.length() == 1) {
				char ch = token.charAt(0);
				if ('$' == ch) {
				   start = true;
				   continue;
				}
				if (' ' == ch 
						|| '[' == ch
						|| ']' == ch
						|| '{' == ch
						|| '.' == ch) {
					continue;
				}
				if (	'}' == ch) {
					break;
				}
			}
			if (start) {
				value = ValueResolverFactory.getValue(value, token);
			}
		}
		if (value == data) {
			return null;
		}
		return value;
	}
	
	
	
}
