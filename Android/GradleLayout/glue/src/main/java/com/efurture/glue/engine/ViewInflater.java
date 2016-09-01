package com.efurture.glue.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public abstract class ViewInflater {

	/**
	 * 屏幕信息
	 * */
	private DisplayMetrics displayMetrics;

	/**
	 * 屏幕宽度单位, 默认750的标注单位
	 * */
	protected float screenUnit = 750.0f;





	public abstract View inflate(InputStream inputStream, final ViewGroup parent) throws ParserConfigurationException, SAXException, IOException;


	public abstract View inflate(InputStream inputStream, final ViewGroup parent, final  boolean attach) throws ParserConfigurationException, SAXException, IOException;



	public static ViewInflater from(Context context){
		ViewInflater inflater = new DefaultViewInflater(context);;
		inflater.displayMetrics = context.getResources().getDisplayMetrics();
		return inflater;
	}



	/**
	 * @param
	 * unit 支持单位 px  dp 默认的ScreenUnit单位等比相当于css的rem单位
	 *      支持calc单位  calc(100% - 10px)
	 * */
	public int  toUnit(String unit){
		if (unit.endsWith("px")) {
			String px = unit.replace("px", "");
			return Integer.parseInt(px);
		}else if (unit.endsWith("dp")) {
			unit = unit.replace("dp", "");
			return  (int)(Float.parseFloat(unit)*displayMetrics.density);
		}else if (unit.equals("match")) {
			return ViewGroup.LayoutParams.MATCH_PARENT;
		}else if (unit.equals("wrap")) {
			return ViewGroup.LayoutParams.WRAP_CONTENT;
		}

		return (int)(displayMetrics.widthPixels*Float.parseFloat(unit)/screenUnit);
	}


	/**
	 * @param
	 * unit 支持单位 px  dp 默认的ScreenUnit单位等比相当于css的rem单位, 并且支持%V
	 * @param   unit  父容器的高度, 如果单位为100% 则采用父容器的高度 乘以100%
	 * unit 支持单位 px  dp 默认的ScreenUnit单位等比相当于css的rem单位
	 *      支持calc单位  calc(100% - 10px)
	 * */
	public int  toUnit(String unit, View parent, boolean isW){
		if(unit.endsWith("%")){
			String percent = unit.replace("%", "");
			float px;
			if(isW){
				px = parent.getLayoutParams().width;
			}else{
				px = parent.getLayoutParams().height;
			}
			if(px <= 0){
				px = screenUnit;
			}
			return (int)(Float.parseFloat(percent)*px/100);
		}
		if(unit.startsWith("calc")){
			unit = unit.replace(" ", "");
			String[] units = CALC_PATTERN.split(unit);
			if(units.length == 0 || units.length > 2){
				throw  new XmlException(unit  + " is illegal calc expression ");
			}
			String firstUnit = units[0].replace("calc(", "");
			if(units.length < 2){
				return  toUnit(firstUnit, parent, isW);
			}
			String secondUnit = units[1].replace(")", "");
			return  toUnit(firstUnit, parent, isW) - toUnit(secondUnit, parent, isW);
		}
		return  toUnit(unit);
	}

	/**
	 * 支持calc语法, 类似 https://developer.mozilla.org/zh-CN/docs/Web/CSS/calc
	 * 但仅支持减法
	 * */
	private static  final Pattern CALC_PATTERN = Pattern.compile("-");




	public DisplayMetrics getDisplayMetrics() {
		return displayMetrics;
	}


	public float getScreenUnit() {
		return screenUnit;
	}

	public void setScreenUnit(float screenUnit) {
		this.screenUnit = screenUnit;
	}


}
