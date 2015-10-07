package com.efurture.marlin.view.engine;

import java.io.InputStream;

import android.content.Context;

import com.efurture.marlin.view.element.XmlView;
import com.efurture.marlin.view.infalter.DefaultViewInflater;
import com.efurture.marlin.view.util.ScreenUnit;

public abstract class ViewInflater {


	public abstract XmlView<?> inflate(InputStream inputStream);

	public static ViewInflater from(Context context){
		ScreenUnit.initWithContext(context);
		return new DefaultViewInflater(context);
	}
}
