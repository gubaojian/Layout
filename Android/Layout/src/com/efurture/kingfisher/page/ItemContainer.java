package com.efurture.kingfisher.page;

import org.json.JSONObject;

import com.efurture.kingfisher.BinderCallback;
import com.efurture.kingfisher.ViewBinder;
import com.efurture.kingfisher.ViewInflater;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class ItemContainer extends FrameLayout {

	
	private View mView;
	
	public ItemContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ItemContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ItemContainer(Context context) {
		super(context);
	}

	public void bindItem(JSONObject item){
		if (mView == null) {
			String name = item.optString("name", "");
			if (TextUtils.isEmpty(name)) {
				return;
			}
			mView = ViewInflater.from(getContext()).inflate(name, null);
		}
		ViewBinder.doBind(mView, item, new BinderCallback(){
			
		});
	}
	
	
}
