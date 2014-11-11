package com.efurture.kingfisher.view;

import org.xml.sax.Attributes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class GButton extends GTextView{

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
	protected TextView createView() {
		return new Button(getContext());
	}

	@Override
	public void initViewAtts(Attributes attrs) {
		super.initViewAtts(attrs);
	}

	
	
}
