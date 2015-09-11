package com.efurture.kingfisher;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import android.content.Context;

import com.efurture.kingfisher.view.element.GButton;
import com.efurture.kingfisher.view.element.GImageView;
import com.efurture.kingfisher.view.element.GTextView;
import com.efurture.kingfisher.view.element.GView;

public class ViewFactory {

	
	public static ViewFactory shareFactory(){
		if (viewFactory != null) {
			return viewFactory;
		}
		synchronized (ViewFactory.class) {
			if (viewFactory == null) {
				viewFactory = new ViewFactory();
			}
		}
		return viewFactory;
	}
	
    private static ViewFactory viewFactory;
	private Map<String, String> elementsMap;
	
	private ViewFactory(){
		elementsMap = new HashMap<String, String>();
		elementsMap.put("View", GView.class.getName());
		elementsMap.put("Button", GButton.class.getName());
		elementsMap.put("ImageView", GImageView.class.getName());
		elementsMap.put("TextView", GTextView.class.getName());
	}
	
	public GView<?> createView(Context context, String element, Attributes attributes) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
		String className = elementsMap.get(element);
		if (className == null) {
			className = element;
		}
		Class<?> viewClass = context.getClassLoader().loadClass(className);
		GView<?> view = (GView<?>) viewClass.getConstructor(Context.class).newInstance(context);
		view.initViewAtts(attributes);
		return view;
	}
	
	public void add(String element, String className){
		elementsMap.put(element, className);
	}
	
	public void add(String element, Class<?> xmlViewClass){
		elementsMap.put(element, xmlViewClass.getName());
	}
}
