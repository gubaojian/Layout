package com.efurture.kingfisher.view.engine;

import java.io.InputStream;

import android.content.Context;

import com.efurture.kingfisher.view.element.GView;
import com.efurture.kingfisher.view.infalter.DefaultViewInflater;
import com.efurture.kingfisher.view.util.ScreenUnit;

public abstract class ViewInflater {

	
	public abstract GView<?> inflate(int rawId);
	public abstract GView<?> inflate(InputStream inputStream);
	
	
	public static ViewInflater from(Context context){
		ScreenUnit.initWithContext(context);
		return new DefaultViewInflater(context);
	}
	
	public static final String ACTION_DOWNLOAD_SUCCESS = "viewinfalter_download_success";
	public static final String ACTION_DOWNLOAD_FAILED = "viewinfalter__download_failed";
	public static final String EXTRA_NAME = "name";
	public static final String EXTRA_HAS_REMAIN = "has_remain";
}
