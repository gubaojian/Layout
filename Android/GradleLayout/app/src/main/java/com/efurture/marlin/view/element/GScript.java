package com.efurture.marlin.view.element;

import org.xml.sax.Attributes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class GScript extends XmlView<View>{

	private String src;
	
	
	public GScript(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GScript(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GScript(Context context) {
		super(context);
	}

	@Override
	protected View createView() {
		return new View(getContext());
	}

	@Override
	public void initViewAtts(Attributes attrs) {
		super.initViewAtts(attrs);
		src = attrs.getValue("src");
		this.setVisibility(View.GONE);
	}

	public String getSrc() {
		return src;
	}
}
