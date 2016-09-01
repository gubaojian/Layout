package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.efurture.glue.engine.ViewInflater;

import org.xml.sax.Attributes;

public class GView extends FrameLayout{

	public GView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GView(Context context) {
		super(context);
	}



	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

}
