package com.efurture.glue.ui;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.efurture.glue.GLog;
import com.efurture.glue.loader.ResourceLoader;
import com.efurture.glue.engine.ViewInflater;

import java.io.InputStream;

public class HybridView extends FrameLayout {


	private View xmlView;

	private ResourceLoader resourceLoader;

	private  HybridListener hybridListener;

	private  Uri pageUri;

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
		load(pageUri, true);
	}
	public void load(final Uri pageUri, final boolean post){
		this.pageUri  = pageUri;
		resourceLoader.load(pageUri, new ResourceLoader.Callback() {
			@Override
			public void onStream(final InputStream inputStream) {
				if (inputStream == null) {
					loadFailed();
					return;
				}
				if(xmlView != null){
					removeView(xmlView);
				}

				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							ViewGroup.LayoutParams params = HybridView.this.getLayoutParams();
							if(params != null ){
							    if(HybridView.this.getMeasuredHeight() <= 0){
									View view = HybridView.this.getRootView().findViewById(android.R.id.content);
									if(view != null){
										params.height = view.getMeasuredHeight();
									}
								}
							}
							xmlView = ViewInflater.from(getContext()).setUri(pageUri).inflate(inputStream, HybridView.this);
						} catch (Exception e) {
							GLog.e("inflater xml ui error " + pageUri.toString(), e);
						}
						if (xmlView != null) {
							if(xmlView.getParent() == null){
								addView(xmlView);
							}
							loadSuccess();
						} else {
							loadFailed();
						}
					}
				};

				if(post && getMeasuredHeight() <= 0){
					post(runnable);
				}else{
					runnable.run();
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


	public void setHybridListener(HybridListener hybridListener) {
		this.hybridListener = hybridListener;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}


	public Uri getUri() {
		return pageUri;
	}
}
