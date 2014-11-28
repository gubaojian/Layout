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

public class ItemView extends FrameLayout {

	
	private View mView;
	
	
	public ItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ItemView(Context context) {
		super(context);
	}

	public void bindItem(JSONObject item, BinderCallback binderCallback){
		if (mView == null) {
			String name = item.optString(ItemKeys.KEY_NAME, "");
			if (TextUtils.isEmpty(name)) {
				return;
			}
			mView = ViewInflater.from(getContext()).inflate(name, null);
			if (mView != null) {
				addView(mView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			}
		}
		if (mView != null) {
			ViewBinder.doBind(mView, item, binderCallback);
		}
	}
}
