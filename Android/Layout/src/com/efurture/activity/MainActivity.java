package com.efurture.activity;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.efurture.kingfisher.internal.DefaultViewInflater;
import com.efurture.kingfisher.view.ScreenUnit;
import com.google.furture.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputStream inputStream = getResources().openRawResource(R.raw.market);
				try {
					long start = System.currentTimeMillis();
					View view = DefaultViewInflater.from(getBaseContext()).inflate(inputStream);
					Log.e("Inflater", "Inflater Used " + (System.currentTimeMillis() - start));
					FrameLayout container = (FrameLayout) findViewById(R.id.container);
					container.removeAllViews();
					container.addView(view);
					inputStream.close();
				}catch (Exception e) {
						e.printStackTrace();
				}
				Intent intent = new Intent(getBaseContext(), XmlviewActivity.class);
				intent.putExtra("url", "https://raw.githubusercontent.com/gubaojian/Layout/master/server/market.xml");
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
