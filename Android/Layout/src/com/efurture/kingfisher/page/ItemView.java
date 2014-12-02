package com.efurture.kingfisher.page;

import org.json.JSONObject;

import com.efurture.kingfisher.BinderCallback;
import com.efurture.kingfisher.ViewBinder;
import com.efurture.kingfisher.ViewInflater;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
			String name = item.optString(ItemKeys.NAME, "");
			if (TextUtils.isEmpty(name)) {
				return;
			}
			boolean isNative = item.optBoolean(ItemKeys.NATIVE, false);
			if (isNative) {
				int id = getContext().getResources().getIdentifier(name, "layout", getContext().getPackageName());
				if (id <= 0) {
					return;
				}
				mView = LayoutInflater.from(getContext()).inflate(id, this);
			}else {
				String version = item.optString(ItemKeys.VERSION, "");
				if (!TextUtils.isEmpty(version)) {
					name += "_" + version;
				}
				String downloadUrl = item.optString(ItemKeys.DOWNLOAD_URL);
				mView = ViewInflater.from(getContext()).inflate(name, downloadUrl);
				if (mView != null) {
					addView(mView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				}
			}
		}
		if (mView != null) {
			ViewBinder.doBind(mView, item, binderCallback);
		}
	}
}
