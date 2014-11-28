package com.efurture.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.efurture.kingfisher.page.PageApp;
import com.google.furture.R;

public class PageAppActivity extends Activity {

	
	private PageApp mPageApp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pageapp);
		mPageApp = new PageApp(this, "app");
		mPageApp.loadPageData();
	}
	
	
	
	
}
