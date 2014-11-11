package com.efurture.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.efurture.kingfisher.ViewInflater;
import com.google.furture.R;

public class XmlviewActivity extends Activity {

	private String downloadUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xmlview);
		downloadUrl = getIntent().getExtras().getString("url");
		this.loadXmlView();
		findViewById(R.id.container).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loadXmlView();
			}
		});
		LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				Toast.makeText(getBaseContext(), "DownloadSuccess ", Toast.LENGTH_SHORT).show();
				loadXmlView();
			}
			
		}, new IntentFilter(ViewInflater.ACTION_DOWNLOAD_SUCCESS));
		
		LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				Toast.makeText(getBaseContext(), "DownloadFailed ", Toast.LENGTH_SHORT).show();
			}
			
		}, new IntentFilter(ViewInflater.ACTION_DOWNLOAD_FAILED));
	}
	
	private void loadXmlView(){
		if (downloadUrl != null) {
			View view = ViewInflater.from(this).inflate("xmlview", 0, downloadUrl);
			if (view != null) {
				FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
				frameLayout.removeAllViews();
				frameLayout.addView(view);
			}		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xmlview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
