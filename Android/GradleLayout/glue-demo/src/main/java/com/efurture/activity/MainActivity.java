package com.efurture.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.efurture.XmlViewUtils;
import com.google.furture.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


	private static  class Item{
		public   String name;
		public   String xml;

		public Item(String name, String xml) {
			this.name = name;
			this.xml = xml;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		final List<Item> items = new ArrayList<Item>();
		items.add(new Item("View", "ui/view"));
		items.add(new Item("FrameLayout", "ui/frameLayout"));
		items.add(new Item("LinearLayout", "ui/linearLayout"));
		items.add(new Item("TextView", "ui/textView"));
		items.add(new Item("ImageView", "ui/imageView"));
		items.add(new Item("Button", "ui/button"));
		items.add(new Item("EditText", "ui/editText"));
		items.add(new Item("CheckBox", "ui/checkBox"));
		items.add(new Item("DatePicker", "ui/datePicker"));
		items.add(new Item("NumberPicker", "ui/numberPicker"));
		items.add(new Item("ProgressBar", "ui/progressBar"));
		items.add(new Item("SeekBar", "ui/seekBar"));
		items.add(new Item("RadioButton", "ui/radioButton"));
		items.add(new Item("Switch", "ui/switch"));
		items.add(new Item("TimePicker", "ui/timePicker"));
		items.add(new Item("WebView", "ui/webView"));
		items.add(new Item("ViewPager", "ui/viewPager"));
		items.add(new Item("Demo", "demo/demo"));


		ListView listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new BaseAdapter() {
			@Override
			public int getCount() {
				return items.size();
			}

			@Override
			public Object getItem(int position) {
				return position;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null){
					convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.list_item, null);
				}
				Button button = (Button) convertView.findViewById(R.id.button);
				final Item item = items.get(position);
				button.setText(item.name + " Demo ");
				button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(getBaseContext(), XmlViewActivity.class);
						intent.putExtra("url", XmlViewUtils.xmlUri(item.xml).toString());
						intent.putExtra("title", item.name);
						startActivity(intent);
					}
				});
				return button;
			}
		});



		/**

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
		**/
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
