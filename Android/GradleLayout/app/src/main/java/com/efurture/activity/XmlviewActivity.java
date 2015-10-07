package com.efurture.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.efurture.marlin.ui.HybridView;
import com.furture.react.DuktapeEngine;
import com.google.furture.R;

public class XmlviewActivity extends Activity {

	public static final String ACTIVITY_LISTENER = "activityListener";

	protected HybridView hybridView;
	protected DuktapeEngine duktapeEngine;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xmlview);
		hybridView = (HybridView) findViewById(R.id.hybird_view);
		duktapeEngine = hybridView.getDuktapeEngine();
		duktapeEngine.register("activity",this);

		Uri uri = getIntent().getData();
		String url = getIntent().getExtras().getString("url");
		if (!TextUtils.isEmpty(url)) {
			uri = Uri.parse(url);
		}
		hybridView.load(Uri.parse(getIntent().getExtras().getString("url")));
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (duktapeEngine != null) {
			duktapeEngine.call(ACTIVITY_LISTENER, "onResume");
		}
	}



	@Override
	protected void onPause() {
		if (duktapeEngine != null) {
			duktapeEngine.call(ACTIVITY_LISTENER, "onPause");
		}
		super.onPause();

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xmlview, menu);
		menu.findItem(R.id.action_reload).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(getBaseContext(), XmlviewActivity.this.getClass());
				intent.setData(getIntent().getData());
				intent.putExtras(getIntent().getExtras());
				startActivity(intent);
				finish();
				return false;
			}
		});
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_reload) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onDestroy() {
		if (duktapeEngine != null) {
			duktapeEngine.destory();
			duktapeEngine = null;
		}
		super.onDestroy();
	}

}
