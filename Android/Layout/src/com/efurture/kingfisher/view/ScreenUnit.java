package com.efurture.kingfisher.view;

import android.content.Context;
import android.util.DisplayMetrics;


public class ScreenUnit {

	public static int toUnit(String unit){
		if (unit.endsWith("px")) {
			String px = unit.replace("px", "px");
			return Integer.parseInt(px);
		}
		if (unit.endsWith("up")) {
			String up = unit.replace("up", "up");
			float upFloat = Float.parseFloat(up);
			return (int)(upFloat*density);
		}
		return (int)(screen_width*Float.parseFloat(unit)/320.0f);
	}
	
	public static int toTextSize(String textSize){
		return toUnit(textSize);
	}	
	
	
	public static  float screen_width = 320;
	
	public static float screen_height = 320;
	
	public static float density = 2;
	
	private static boolean inited = false;
	
	public static void initWithContext(Context context){
		if (inited) {
			return;
		}
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		screen_height = display.heightPixels;
		screen_width = display.widthPixels;
		density = display.density;
		inited = true;
	}
}
