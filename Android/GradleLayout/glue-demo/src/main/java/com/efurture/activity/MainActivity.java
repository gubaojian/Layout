package com.efurture.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.efurture.XmlViewUtils;
import com.google.furture.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("market").toString());
				startActivity(intent);
			}
		});

		findViewById(R.id.login_button).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("login").toString());
				startActivity(intent);
			}
		});

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("demo").toString());
						startActivity(intent);
			}
		});

		findViewById(R.id.circle).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("circle").toString());
				startActivity(intent);
			}
		});

		findViewById(R.id.textview).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("textView").toString());
				startActivity(intent);
			}
		});

		findViewById(R.id.calc_func).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("calc").toString());
				startActivity(intent);
			}
		});

		findViewById(R.id.refresh_layout).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("refresh").toString());
				startActivity(intent);
			}
		});

		findViewById(R.id.uikit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", XmlViewUtils.xmlUri("uikit").toString());
				startActivity(intent);
			}
		});



		findViewById(R.id.go).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = ((EditText)findViewById(R.id.editText)).getText().toString();
				if(TextUtils.isEmpty(url)){
					Toast.makeText(getBaseContext(), "请输入Url地址", Toast.LENGTH_SHORT).show();
					return;
				}
				getSharedPreferences("url", MODE_PRIVATE).edit().putString("url", url).commit();
				Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
				intent.putExtra("url", url);
				startActivity(intent);
			}
		});


		String url = getSharedPreferences("url", MODE_PRIVATE).getString("url", "");
	    ((EditText)findViewById(R.id.editText)).setText(url);

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
