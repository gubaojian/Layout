package com.efurture.kingfisher.page;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.efurture.kingfisher.download.DownloadTask;
import com.efurture.kingfisher.filestore.FileStore;

public class PageActivity extends Activity implements  Callback{

	private static FileStore pageStore;
	private Handler handler;
	private DownloadTask pageTask;
	
	private Activity activity;
	private AbsListView listview;
	private String downloadUrl;
	private JSONArray items;
	private int viewTypeCount;
	private boolean hasMore;
	private View loadMoreView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (pageStore == null) {
			pageStore = FileStore.from(this, "pages", 128, 1000*60*60);
		}
		handler = new Handler(this);
		Uri uri = getIntent().getData();
		if (uri != null) {
			downloadUrl = uri.toString();
			loadPage(downloadUrl, PAGE_FIRST);
		}
	}
	
	
	protected void handlePageData(JSONObject page, int pageNum){
		handleMetas(page.optJSONArray(ItemKeys.KEY_METAS), pageNum);
		if (listview == null) {
			listview = (AbsListView) activity.findViewById(android.R.id.list);
		}
		if (pageNum == PAGE_FIRST) {
			items = page.optJSONArray(ItemKeys.KEY_ITEMS);
		}
		viewTypeCount = items.length() + 1;
		if (viewTypeCount > MAX_VIEW_TYPE_COUNT) {
			viewTypeCount = MAX_VIEW_TYPE_COUNT;
		}
		if (listview.getAdapter() == null) {
			if (hasMore) {
			    if (listview instanceof ListView) {
						//FXIME Create MoreView
						((ListView) listview).addFooterView(loadMoreView);
				}
			}
			
			listview.setAdapter(new DynamicAdapter() {
				
				@Override
				public String getItemType(int position) {
					JSONObject item =  items.optJSONObject(position);
					return item.optString(ItemKeys.KEY_NAME, "");
				}

				@Override
				public int getCount() {
					if (items == null) {
						return 0;
					}
					return items.length();
				}

				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					if (convertView == null) {
						convertView = new ItemContainer(listview.getContext());
					}
					if (convertView instanceof ItemContainer) {
						ItemContainer item = (ItemContainer)convertView;
						item.bindItem(items.optJSONObject(position));
					}
					return convertView;
				}
				
				 public int getViewTypeCount() {
				        return viewTypeCount;
				 }
			});
		}else {
			((BaseAdapter)listview.getAdapter()).notifyDataSetChanged();
		}
	}
	
	
	
	

	private void handleMetas(JSONArray metas, int pageNum) {
		hasMore = false;
		if (metas == null || metas.length() == 0) {
			return;
		}
		for (int i=0; i<metas.length(); i++) {
			JSONObject meta = metas.optJSONObject(i);
			if (meta == null) {
				return;
			}
			String name = meta.optString(ItemKeys.KEY_NAME, "");
			String value = meta.optString("value", "");
			if (TextUtils.isEmpty(name)) {
				continue;
			}
			
		}
	}

	protected void handleMeta(String name, String value){
		if ("hasMore".equals(name)) {
			value = value.toLowerCase();
			if ("yes".equals(value) || "true".equals(value)) {
				hasMore = true;
			}
		}else if ("title".equals(name)) {
			activity.getActionBar().setTitle(value);
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case PAGE_DATA_LOADED:
			handlePageData((JSONObject)msg.obj, msg.arg1);
			break;

		default:
			break;
		}
		return false;
	}
	
	
	protected void loadPage(String pageUrl, final int pageNum){
		final String fileName = Hash.md5(pageUrl);
		if (pageTask != null) {
			pageTask.cancel(true);
		}
		pageTask = new DownloadTask(fileName, pageUrl){
			@Override
			protected Boolean doInBackground(Void... params) {
				try {
					String pageContent = pageStore.readString(fileName);
					if (pageContent != null) {
						JSONObject page = new JSONObject(pageContent);
						Message msg = Message.obtain();
						msg.obj = page;
						msg.arg1 = pageNum;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return super.doInBackground(params);
			}
		};
	}
	

	private static final int PAGE_DATA_LOADED = 1;
	public static final int PAGE_FIRST = 1;
	public static final int MAX_VIEW_TYPE_COUNT = 64;
}
