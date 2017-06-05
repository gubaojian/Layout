package com.efurture.gule.hybrid.api.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.efurture.glue.utils.ViewUtils;
import com.furture.react.JSRef;

import java.util.regex.Pattern;


/**
 * ui模块的实现， 封装通用的toast， alert，find 以及 click操作
 * */
public class UIApi {
	
	private Activity activity;

	/**
	 * 创建UI模块
	 * */
	public UIApi(Activity activity) {
		super();
		this.activity = activity;
	}

	/**
	 * @param message toast内容
	 * */
	public void toast(String message){
		Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
	}


	/**
	 * @param message alert的内容
	 * */
	public void alert(String message){
		alert(null, message, null, null, null, null);
	}

	/**
	 * @param message alert的内容
	 * @param ok   确定按钮的位置
	 * */
	public void alert(String message, String ok){
		alert(null, message, ok, null, null, null);
	}

	/**
	 * @param message alert的内容
	 * @param ok   确定按钮的位置
	 * @param  okClickListener 按钮点击的回调
	 * */
	public void alert(String message, String ok,  final JSRef okClickListener){
		alert(null, message, ok, okClickListener, null, null);
	}

	/**
	 * @param title alert的标题
	 * @param message alert的内容
	 * @param ok   确定按钮的位置
	 * @param  okClickListener 按钮点击的回调
	 * */
	public void alert(String title, String message, String ok,  final JSRef okClickListener){
		alert(title, message, ok, okClickListener, null, null);
	}

	/**
	 * @param title alert的标题
	 * @param message alert的内容
	 * @param ok   确定按钮的位置
	 * @param  okClickListener 按钮点击的回调
	 * @param   cancel 取消按钮的内容
	 * @param   cancelClickListener 取消按钮的回调
	 * */
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


	/**
	 * 通过xml创建UI
	 * @param  xml 创建XML
	 * @param  parent 父视图，可以传空
	 * */
	public View fromXml(String xml,  final ViewGroup parent){
		Context context = activity;
		if(parent != null){
			context = parent.getContext();
		}
		return  ViewUtils.inflate(context, xml, parent);
	}

	/**
	 * 通过xml创建UI
	 * @param  xml 创建XML
	 * @param  parent 父视图，可以传空
	 * @param  attachRoot 是否attach到父视图
	 * */
	public View fromXml(String xml,  final ViewGroup parent, boolean attachRoot){
		Context context = activity;
		if(parent != null){
			context = parent.getContext();
		}
		return  ViewUtils.inflate(context, xml, parent, attachRoot);
	}

	/**
	 * 根据tag找到父视图的组件
	 * @param  tag 标签
	 * */
	public View  find(String tag){
		return  find(activity.findViewById(android.R.id.content), tag);
	}
	/**
	 * 在容器中根据tag找到父视图的组件
	 * @param  parent 容器
	 * @param  tag 标签
	 * */
	public View  find(View parent, String tag){
		return  parent.findViewWithTag(tag);
	}



	/**
	 * 快捷的事件绑定方法, 通过tag选择器来绑定事件
	 * @param  tags 标签
	 * @param  click 点击
	 * */
	public  void click(String tags, JSRef click){
		onClick(activity.findViewById(android.R.id.content), tags, click);
	}

	/**
	 * 快捷的事件绑定方法, 通过tag选择器来绑定事件
	 * @param  tags 标签
	 * @param  click 点击
	 * */
	public  void onClick(String tags, JSRef click){
		onClick(activity.findViewById(android.R.id.content), tags, click);
	}

	/**
	 * 快捷的事件绑定方法, 通过tag选择器来绑定事件
	 * @param  parent 标签所在的容器
	 * @param  tags 标签
	 * @param  click 点击
	 * */
	public  void click(View parent, String tags, JSRef click){
		  onClick(parent, tags, click);
	}

	/**
	 * 快捷的事件绑定方法, 通过tag选择器来绑定事件
	 * @param  parent 标签所在的容器
	 * @param  tags 标签
	 * @param  click 点击
	 * */
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
	 * 多个tag用 逗号分隔
	 * */
 	private static  final Pattern TAGS_SPLIT_PATTERN = Pattern.compile(",");

}
