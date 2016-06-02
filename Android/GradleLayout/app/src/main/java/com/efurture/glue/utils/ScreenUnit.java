package com.efurture.glue.utils;

import android.content.Context;
import android.util.DisplayMetrics;


public class ScreenUnit {

	public static int toUnit(String unit){
		if (unit.endsWith("px")) {
			String px = unit.replace("px", "");
			return Integer.parseInt(px);
		}
		if (unit.endsWith("dp")) {
			unit = unit.replace("dp", "");
		}
		return (int)(screen_width*Float.parseFloat(unit)/320.0f);
	}


	public static float toFloatUnit(String unit){
		if (unit.endsWith("px")) {
			String px = unit.replace("px", "");
			return Float.parseFloat(px);
		}
		if (unit.endsWith("dp")) {
			unit = unit.replace("dp", "");
		}
		return (screen_width*Float.parseFloat(unit)/320.0f);
	}
	public static int toTextSize(String textSize){
		return toUnit(textSize);
	}	
	
	
	public static  float screen_width = 320;
	
	public static float screen_height = 320;
	
	public static float density = 2;
	
	private static boolean screen_config_init = false;
	
	public static void initWithContext(Context context){
		if (screen_config_init) {
			return;
		}
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		screen_height = display.heightPixels;
		screen_width = display.widthPixels;
		density = display.density;
		screen_config_init = true;
	}
}
