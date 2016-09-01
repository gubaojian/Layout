package com.efurture.glue.engine;

import android.content.Context;
import android.view.View;
import android.widget.TimePicker;

import com.efurture.glue.component.GHorizontalScrollView;
import com.efurture.glue.component.GPagerIndicator;
import com.efurture.glue.component.GViewPager;
import com.efurture.glue.view.GButton;
import com.efurture.glue.view.GCheckBox;
import com.efurture.glue.view.GDatePicker;
import com.efurture.glue.view.GEditText;
import com.efurture.glue.view.GGridView;
import com.efurture.glue.view.GImageView;
import com.efurture.glue.view.GLinearLayout;
import com.efurture.glue.view.GListView;
import com.efurture.glue.view.GNumberPicker;
import com.efurture.glue.view.GProgressBar;
import com.efurture.glue.view.GRadioButton;
import com.efurture.glue.view.GRecyclerView;
import com.efurture.glue.view.GRefreshLayout;
import com.efurture.glue.view.GScrollView;
import com.efurture.glue.view.GSeekBar;
import com.efurture.glue.view.GSwitch;
import com.efurture.glue.view.GTextView;
import com.efurture.glue.view.GView;
import com.efurture.glue.view.GWebView;

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
	private Map<String, Class<?>> classMap;



	private ViewFactory(){
		elementsMap = new HashMap<String, String>();
		classMap = new HashMap<String, Class<?>>();
		classMap.put("View", GView.class);
		classMap.put("Button", GButton.class);
		classMap.put("ImageView", GImageView.class);
		classMap.put("TextView", GTextView.class);
		classMap.put("EditText", GEditText.class);
		classMap.put("LinearLayout", GLinearLayout.class);
		classMap.put("WrapLayout",  GLinearLayout.class);
		classMap.put("ListView",  GListView.class);
		classMap.put("ScrollView", GScrollView.class);
		classMap.put("HScrollView", GHorizontalScrollView.class);
		classMap.put("RefreshLayout", GRefreshLayout.class);
		classMap.put("RecyclerView", GRecyclerView.class);
		classMap.put("GridView", GGridView.class);
		classMap.put("WebView", GWebView.class);
		classMap.put("ViewPager", GViewPager.class);
		classMap.put("PagerIndicator", GPagerIndicator.class);
		classMap.put("ProgressBar", GProgressBar.class);
		classMap.put("CheckBox", GCheckBox.class);
		classMap.put("RadioButton", GRadioButton.class);
		classMap.put("Switch", GSwitch.class);
		classMap.put("DatePicker", GDatePicker.class);
		classMap.put("NumberPicker", GNumberPicker.class);
		classMap.put("SeekBar", GSeekBar.class);
		classMap.put("TimePicker", TimePicker.class);


	}
	
	public View createView(Context context, String element, Attributes attributes){
		Class<?> viewClass = classMap.get(element);
		if(viewClass == null){
			String className = elementsMap.get(element);
			if (className == null) {
				className = element;
			}
			try {
				viewClass = context.getClassLoader().loadClass(className);
			} catch (ClassNotFoundException e) {
				throw  new XmlException(e);
			}
		}
		View  view = null;
		try {
			view = (View) viewClass.getConstructor(Context.class).newInstance(context);
		} catch (InstantiationException e) {
			throw  new XmlException(e);
		} catch (IllegalAccessException e) {
			throw  new XmlException(e);
		} catch (InvocationTargetException e) {
			throw  new XmlException(e.getTargetException());
		} catch (NoSuchMethodException e) {
			throw  new XmlException(e);
		}
		return view;
	}
	
	public void add(String element, String className){
		elementsMap.put(element, className);
	}
	
	public void add(String element, Class<?> xmlViewClass){
		classMap.put(element, xmlViewClass);
	}
}
