package com.efurture.marlin.view.engine;

import android.content.Context;

import com.efurture.marlin.component.GHorizontalScrollView;
import com.efurture.marlin.component.GPagerIndicator;
import com.efurture.marlin.component.GViewPager;
import com.efurture.marlin.view.element.GButton;
import com.efurture.marlin.view.element.GEditText;
import com.efurture.marlin.view.element.GImageView;
import com.efurture.marlin.view.element.GTextView;
import com.efurture.marlin.view.element.GView;
import com.efurture.marlin.view.element.XmlView;

import org.xml.sax.Attributes;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

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
		elementsMap.put("EditText", GEditText.class.getName());
		elementsMap.put("HScrollView", GHorizontalScrollView.class.getName());
		elementsMap.put("ViewPager", GViewPager.class.getName());
		elementsMap.put("PagerIndicator", GPagerIndicator.class.getName());
	}
	
	public XmlView<?> createView(Context context, String element, Attributes attributes) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
		String className = elementsMap.get(element);
		if (className == null) {
			className = element;
		}
		Class<?> viewClass = context.getClassLoader().loadClass(className);
		XmlView<?> view = (XmlView<?>) viewClass.getConstructor(Context.class).newInstance(context);
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
