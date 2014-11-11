
package com.efurture.kingfisher.expression;

import java.util.StringTokenizer;


public class Expression {
	
	private static final String DELIMITER = " ${.[]}";
	
	public static Object getValue(final Object target, final String expression){
		if (target == null || expression == null) {
			return null;
		}
		Object value = target;
		boolean start = false;
		StringTokenizer tokenizer = new StringTokenizer(expression, DELIMITER, true);
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
		if (value == target) {
			return null;
		}
		return value;
	}
}
