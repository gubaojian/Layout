package com.efurture.kingfisher;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;

import android.content.Context;

import com.efurture.kingfisher.view.GButton;
import com.efurture.kingfisher.view.GImageView;
import com.efurture.kingfisher.view.GLayout;
import com.efurture.kingfisher.view.GTextView;
import com.efurture.kingfisher.view.GView;

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
	private Map<String, String> classMap;
	
	private ViewFactory(){
		classMap = new HashMap<String, String>();
		classMap.put("View", GView.class.getName());
		classMap.put("Button", GButton.class.getName());
		classMap.put("ImageView", GImageView.class.getName());
		classMap.put("TextView", GTextView.class.getName());
	}
	
	public GLayout<?> createView(Context context, String element, Attributes attributes) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
		String className = classMap.get(element);
		if (className == null) {
			className = element;
		}
		Class<?> viewClass = context.getClassLoader().loadClass(className);
		GLayout<?> view = (GLayout<?>) viewClass.getConstructor(Context.class).newInstance(context);
		view.initViewAtts(attributes);
		return view;
	}
	
	public void add(String element, String className){
		classMap.put(element, className);
	}
	
}
