package com.efurture.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

import com.efurture.kingfisher.page.HybridPage;
import com.google.furture.R;

public class HybridActivity extends Activity {

	
	private HybridPage mHybridPage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pageapp);
		Uri uri = getIntent().getData();
		if (uri != null) {
			mHybridPage = new HybridPage(this, "app");
			mHybridPage.loadPageData();
		}else {
			Toast.makeText(this, "无法创建连接", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
	
}
