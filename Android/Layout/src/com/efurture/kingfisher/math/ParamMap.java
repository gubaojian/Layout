/**
 * 
 */
package com.efurture.kingfisher.math;

import java.util.HashMap;
import java.util.Map;

import android.util.DisplayMetrics;
import android.view.View;

/**
 * @author gubaojian   email: gubaojian@163.com
 *  */
public class ParamMap {
	
	

	/**
	 * parameters  for eval layout expression
	 * */
	public static Map<String, Object>  from(View v){
		if (commonParamsMap == null) {
			commonParamsMap = new HashMap<String, Object>();
			DisplayMetrics  metrics = v.getContext().getResources().getDisplayMetrics();
			commonParamsMap.put(Params.DENSITY, metrics.density);
			commonParamsMap.put(Params.SCREEN_WIDTH, metrics.widthPixels);
			commonParamsMap.put(Params.SCREEN_HEIGHT, metrics.heightPixels);
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.putAll(commonParamsMap);
		paramsMap.put(Params.HEIGHT,  v.getHeight());
	    paramsMap.put(Params.WIDTH,   v.getWidth());
	    if (v.getParent() instanceof View) {
			 View parent = (View) v.getParent();
			 paramsMap.put(Params.PARENT_HEIGHT,  parent.getHeight());
			 paramsMap.put(Params.PARENT_WIDTH,   parent.getWidth());
		}
		return paramsMap;
	}
	
	private static  Map<String, Object> commonParamsMap;
}
