package com.efurture.kingfisher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class GView extends  GLayout<View> {
	
	public GView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GView(Context context) {
		super(context);
	}
	
	protected View createView() {
		return new View(getContext());
	}

	
	
}
