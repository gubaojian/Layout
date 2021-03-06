package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import com.efurture.glue.engine.ViewInflater;

import org.xml.sax.Attributes;

public class GButton extends GTextView{

	public GButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public GButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GButton(Context context) {
		super(context);
		init();
	}


	private void init(){
		setClickable(true);
	}


	@Override
	public void initViewAtts(Attributes attrs, ViewInflater inflater) {
		super.initViewAtts(attrs, inflater);
		setGravity(Gravity.CENTER);


	}

	
	
}
