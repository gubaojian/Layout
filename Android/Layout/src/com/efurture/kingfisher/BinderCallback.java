package com.efurture.kingfisher;

import com.efurture.kingfisher.image.GImageFetcher;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BinderCallback {

	public boolean doBindData(final View view, final Object value) {
		if (view instanceof TextView) {
			if (value == null) {
				((TextView) view).setText("");
			} else {
				((TextView) view).setText(value.toString());
			}
			return true;
		}else if (view instanceof ImageView) {
			String imageUrl = (value == null ? null : value.toString());
			GImageFetcher.shareFetcher().load(view, imageUrl);
		}
		return false;
	}
	
	
    public boolean doBindEvent(final View view, final Object value){
		return false;
	}
    
    public boolean doBindChildView(View view){
    	if (view instanceof ViewPager) {
			return false;
		}
    	return true;
    }
}
