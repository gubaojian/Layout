package com.efurture.kingfisher;

import android.view.View;
import android.view.ViewGroup;

import com.efurture.kingfisher.view.element.GView;

public class ViewBinder {

	public static void doBind(final View view, final Object data, final BinderCallback binderCallback){
		if (view instanceof GView) {
			GView<?> layout  = (GView<?>) view;
			/**
			String valueData = layout.getValueData();
			if (!TextUtils.isEmpty(valueData)) {
				Object value = Expression.getValue(data, valueData);
				binderCallback.doBindData(layout.getView(), value);
			}
			
			String eventData = layout.getEventData();
			if (!TextUtils.isEmpty(eventData)) {
				Object value = Expression.getValue(data, eventData);
				binderCallback.doBindEvent(layout, value);
			}*/
		}else {
			CharSequence desc = view.getContentDescription();
			if (desc != null && desc.length() >0) {
				String expression = desc.toString();
				if (expression.length() > 1) {
					char ch = expression.charAt(0);
					if (ch == EXPRESSION_PREFIX) {
						//Object value = ElUtil.getValue(data, expression);
						//binderCallback.doBindData(view, value);
					}
				}
			}
			Object tag = view.getTag();
			if (tag instanceof CharSequence){
				final String expression = tag.toString();
				if (expression.length() > 1 && expression.charAt(0) == EXPRESSION_PREFIX) {
					//Object value = ElUtil.getValue(data, expression);
					//binderCallback.doBindEvent(view, value);
				}
			}
		}
		if (view instanceof ViewGroup) {
			if (binderCallback.doBindChildView(view)) {
				ViewGroup root = (ViewGroup) view;
				for (int index = 0; index < root.getChildCount(); index++) {
					View child = root.getChildAt(index);
					doBind(child, data, binderCallback);
				}
			}
		}
	}

	private static final char EXPRESSION_PREFIX = '$';
}
