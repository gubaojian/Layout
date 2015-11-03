package com.efurture.marlin.view.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import org.xml.sax.Attributes;

public class GButton extends GTextView<TextView>{

	public GButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GButton(Context context) {
		super(context);
	}



	@Override
	public void initViewAtts(Attributes attrs) {
		view.setGravity(Gravity.CENTER);
		super.initViewAtts(attrs);
	}

	
	
}
