package com.efurture.marlin.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.FrameLayout;

import com.efurture.marlin.loader.ResourceLoader;
import com.efurture.marlin.view.element.GScript;
import com.efurture.marlin.view.element.XmlView;
import com.efurture.marlin.view.engine.ViewInflater;
import com.efurture.marlin.view.util.IOUtils;
import com.furture.react.DuktapeEngine;

import java.io.InputStream;

public class HybridView extends FrameLayout {


	private XmlView<?> xmlView;

	private ResourceLoader resourceLoader;

	private  HybridListener hybridListener;

	private  DuktapeEngine duktapeEngine;

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
				xmlView = ViewInflater.from(getContext()).inflate(inputStream);
				if (xmlView != null) {
					removeAllViews();
					addView(xmlView);
					loadScript(pageUri);
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


	private  void loadScript(Uri pageUri) {
		GScript javaScript = (GScript)xmlView.findElementByClass(GScript.class);
		if (javaScript == null) {
			return;
		}
		String src = javaScript.getSrc();
		Uri scriptUri = Uri.parse(src);
		//relative path
		if(TextUtils.isEmpty(scriptUri.getHost()) || TextUtils.isEmpty(scriptUri.getScheme())){
			StringBuilder pathBuilder = new StringBuilder();
			String[] paths = pageUri.getPath().split("/");
			if (paths != null && paths.length > 1){
				for(int i=0; i<paths.length -1; i++){
					pathBuilder.append(paths[i]);
					pathBuilder.append('/');
				}
			}
			String relativePath = scriptUri.getPath();
			if (relativePath.startsWith("/")){
				if (pathBuilder.length() > 0){
					pathBuilder.deleteCharAt(pathBuilder.length() - 1);
				}
			}
			pathBuilder.append(relativePath);
			scriptUri = pageUri.buildUpon().path(pathBuilder.toString()).query(scriptUri.getQuery()).build();
		}
		resourceLoader.load(scriptUri, new ResourceLoader.Callback() {
			@Override
			public void onStream(InputStream inputStream) {
				if (inputStream == null) {
					loadFailed();
					return;
				}
				String script = IOUtils.toString(inputStream);
				getDuktapeEngine().execute(script);
				bindClickEvent(HybridView.this);
				loadSuccess();
			}
		});
	}



	private void bindClickEvent(View view){
		if (view instanceof ViewGroup){
			ViewGroup parent = (ViewGroup)view;
			if (view instanceof  XmlView){
				final XmlView xmlView = (XmlView) view;
				if(!TextUtils.isEmpty(xmlView.getOnClickAttr())){
					xmlView.getView().setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							getDuktapeEngine().execute(xmlView.getOnClickAttr());
						}
					});
				}
			}
			for (int index = 0; index < parent.getChildCount(); index++) {
				View child = parent.getChildAt(index);
				bindClickEvent(child);
			}
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


	public DuktapeEngine getDuktapeEngine(){
		if(duktapeEngine == null){
			duktapeEngine = new DuktapeEngine();
			duktapeEngine.init();
		}
		return  duktapeEngine;
	}
}
