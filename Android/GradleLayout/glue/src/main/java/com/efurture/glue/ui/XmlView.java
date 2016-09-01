package com.efurture.glue.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.efurture.glue.GLog;
import com.efurture.glue.loader.ResourceLoader;
import com.efurture.glue.engine.ViewInflater;

import java.io.ByteArrayInputStream;

public class XmlView extends FrameLayout {


	private View xmlView;

	private ResourceLoader resourceLoader;

	private XmlViewLoadListener xmlViewLoadListener;


	public XmlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public XmlView(Context context) {
		super(context);
		init();
	}

	public XmlView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {

	}


	/**
	 * @param  pageUrl
	 * */
	public void loadUrl(final String pageUrl){
		loadUrl(pageUrl, true);
	}


	/**
	 * @param  xml xml文件内容
	 * */
	public void loadXml(final String xml){
		if (TextUtils.isEmpty(xml)) {
			loadFailed();
			return;
		}
		loadBytes(null, xml.getBytes());
	}

	/**
	 * @param  pageUrl
	 * */
	public void loadUrl(final String pageUrl, final boolean post){
		getResourceLoader().loadUrl(pageUrl, new ResourceLoader.Callback() {
			@Override
			public void onStream(final byte[] bts) {
				if (bts == null) {
					loadFailed();
					return;
				}

				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						loadBytes(pageUrl, bts);
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


	private void loadBytes(final String pageUrl, final byte[] bts){
		if(xmlView != null){
			removeView(xmlView);
			xmlView = null;
		}
		try {
			ViewGroup.LayoutParams params = XmlView.this.getLayoutParams();
			if(params != null ){
				if(XmlView.this.getMeasuredHeight() <= 0){
					View view = XmlView.this.getRootView().findViewById(android.R.id.content);
					if(view != null){
						params.height = view.getMeasuredHeight();
					}
				}else {
					params.height = XmlView.this.getMeasuredHeight();
				}
			}
			ByteArrayInputStream inputStream =  new ByteArrayInputStream(bts);
			xmlView = ViewInflater.from(getContext()).inflate(inputStream, XmlView.this);
		} catch (Exception e) {
			GLog.e("xml file content:\n" + new String(bts));
			if(pageUrl != null){
				GLog.e("inflater xml ui error " + getResourceLoader().toAbsPageUrl(pageUrl), e);
			}else{
				GLog.e("inflater xml ui error ", e);
			}

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

	private void loadFailed() {
		if (xmlViewLoadListener != null){
			xmlViewLoadListener.onLoadFailed();
		}
	}

	private void loadSuccess() {
		if (xmlViewLoadListener != null){
			xmlViewLoadListener.onLoadSuccess();
		}
	}


	public void setXmlViewLoadListener(XmlViewLoadListener xmlViewLoadListener) {
		this.xmlViewLoadListener = xmlViewLoadListener;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public ResourceLoader getResourceLoader() {
		if(resourceLoader == null){
			resourceLoader = new ResourceLoader(getContext());
		}
		return resourceLoader;
	}

}
