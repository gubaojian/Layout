package com.efurture.marlin.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.efurture.marlin.loader.ResourceLoader;
import com.efurture.marlin.view.element.XmlView;
import com.efurture.marlin.view.engine.ViewInflater;

import java.io.InputStream;

public class HybridView extends FrameLayout {


	private XmlView<?> xmlView;

	private ResourceLoader resourceLoader;

	private  HybridListener hybridListener;


	public HybridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public HybridView(Context context) {
		super(context);
		init();
	}

	public HybridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		resourceLoader = new ResourceLoader(getContext());
	}

	/**
	 * @param  pageUri
	 * */
	public void load(final Uri pageUri){
		resourceLoader.load(pageUri, new ResourceLoader.Callback() {
			@Override
			public void onStream(InputStream inputStream) {
				if (inputStream == null) {
					loadFailed();
					return;
				}
				if(xmlView != null){
					removeView(xmlView);
				}
				xmlView = ViewInflater.from(getContext()).inflate(inputStream);
				if (xmlView != null) {
					addView(xmlView);
					loadSuccess();
				} else {
					loadFailed();
				}
			}
		});
	}

	private void loadFailed() {
		if (hybridListener != null){
			hybridListener.onLoadFailed();
		}
	}

	private void loadSuccess() {
		if (hybridListener != null){
			hybridListener.onLoadSuccess();
		}
	}







	@Override
	protected void initializeFadingEdge(TypedArray a) {
	}

	public void setHybridListener(HybridListener hybridListener) {
		this.hybridListener = hybridListener;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}




}
