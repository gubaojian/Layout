package com.efurture.gule.hybrid.api.local;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.engine.XmlException;
import com.efurture.glue.utils.ViewUtils;
import com.efurture.gule.hybrid.api.globals.OSApi;
import com.furture.react.JSRef;

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

public class UIApi {
	
	private Activity activity;
	public UIApi(Activity activity) {
		super();
		this.activity = activity;
	}

	public void toast(String message){
		Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
	}


	public void alert(String message){
		alert(null, message, null, null, null, null);
	}

	public void alert(String message, String ok){
		alert(null, message, ok, null, null, null);
	}

	public void alert(String message, String ok,  final JSRef okClickListener){
		alert(null, message, ok, okClickListener, null, null);
	}
	public void alert(String title, String message, String ok,  final JSRef okClickListener){
		alert(title, message, ok, okClickListener, null, null);
	}

	public void alert(String title, String message, String ok, final JSRef okClickListener,
					  String cancel, final JSRef cancelClickListener){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if(!TextUtils.isEmpty(message)){
			builder.setMessage(message);
		}
		if(!TextUtils.isEmpty(title)){
			builder.setTitle(title);
		}
		if(!TextUtils.isEmpty(ok)){
			 builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(okClickListener != null){
						       okClickListener.getEngine().call(okClickListener, "onClick");
						}
					}
			 });
		}
		
		if(!TextUtils.isEmpty(cancel)){
			 builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(cancelClickListener != null){
						    cancelClickListener.getEngine().call(cancelClickListener, "onClick");
						}
					}
			 });
		}
	    builder.create().show();
	}


	public View fromXml(String xml,  final ViewGroup parent){
		Context context = activity;
		if(parent != null){
			context = parent.getContext();
		}
		return  ViewUtils.inflate(context, xml, parent);
	}

	public View fromXml(String xml,  final ViewGroup parent, boolean attachRoot){
		Context context = activity;
		if(parent != null){
			context = parent.getContext();
		}
		return  ViewUtils.inflate(context, xml, parent, attachRoot);
	}

	public View  find(String tag){
		return  find(activity.findViewById(android.R.id.content), tag);
	}

	public View  find(View parent, String tag){
		return  parent.findViewWithTag(tag);
	}



	/**
	 * 快捷的事件绑定方法, 通过tag选择器来绑定事件
	 * */
	public  void click(String tags, JSRef click){
		onClick(activity.findViewById(android.R.id.content), tags, click);
	}


	public  void onClick(String tags, JSRef click){
		onClick(activity.findViewById(android.R.id.content), tags, click);
	}

	public  void click(View parent, String tags, JSRef click){
		  onClick(parent, tags, click);
	}


	public  void onClick(View parent, String tags, final JSRef clickRef){
		String [] viewTags =  TAGS_SPLIT_PATTERN.split(tags);
		final View.OnClickListener clickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		         if(clickRef != null){
					 clickRef.getEngine().call(clickRef, "onClick", v);
				 }
			}
		};
		for(String viewTag  : viewTags){
			viewTag = viewTag.trim();
			View view = parent.findViewWithTag(viewTag);
			if(view != null){
				view.setOnClickListener(clickListener);
			}
		}
	}


	/**
	 * 设置titlebar的背景色
	 * */




	/**
	 * 多个tag用 逗号分隔
	 * */
 	private static  final Pattern TAGS_SPLIT_PATTERN = Pattern.compile(",");

}
