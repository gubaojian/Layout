package com.efurture.marlin.view.element;

import org.xml.sax.Attributes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class GView<T extends View> extends XmlView {


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
	protected T createView(){
		return (T)new View(getContext());
	}

	@Override
	public void initViewAtts(Attributes attrs) {
		super.initViewAtts(attrs);
	}
}
