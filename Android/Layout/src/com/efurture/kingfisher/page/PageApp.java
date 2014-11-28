package com.efurture.kingfisher.page;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.efurture.kingfisher.BinderCallback;
import com.efurture.kingfisher.download.DownloadTask;
import com.efurture.kingfisher.filestore.FileStore;
import com.google.furture.R;

public class PageApp implements  Callback{

	private static FileStore pageStore;
	protected  Handler handler;
	protected  DownloadTask pageTask;
	
	protected  WeakReference<Activity> activityRef;
	protected  AbsListView mListview;
	protected  JSONArray items;
	protected  int viewTypeCount = -1;
	protected  boolean hasMore;
	protected String mBaseUrl;
	protected LoadMoreState mLoadMore;
	protected String mCurrentPageUrl;
	protected  boolean useCache = true;
	protected float mVelocityScale = 1.5f;

	public  PageApp (Activity context, String baseUrl) {
		if (pageStore == null) {
			pageStore = FileStore.from(context, "pages_app", 128, 1000*60*60);
		}
		activityRef = new WeakReference<Activity>(context);
		handler = new Handler(this);
		mBaseUrl = baseUrl;
		mLoadMore = getLoadMore();
	}
	

	public void loadPageData(){
		loadPageData(mBaseUrl, mLoadMore.getCurrentPage());
	}
	
	protected void loadPageData(String baseUrl, final int pageNum){
		mCurrentPageUrl = buildPageUrlForPageNum(baseUrl, pageNum);
		final String fileName = Hash.md5(mCurrentPageUrl );
		if (pageTask != null) {
			pageTask.cancel(true);
		}
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		pageTask = new DownloadTask(outputStream, mCurrentPageUrl){
			@Override
			protected Boolean doInBackground(Void... params) {
				try {
					JSONObject pageData = null;
					if (useCache) {
						File file = pageStore.toFile(fileName);
						if (file != null &&  file.exists()) {
							String pageContent = pageStore.readString(fileName);
							if (pageContent != null) {
								pageData = new JSONObject(pageContent);
								long  validTime = pageData.optLong(ItemKeys.KEY_VALID_TIME, ItemKeys.DEFAULT_VALID_TIME)*1000;
								boolean isValid = ((System.currentTimeMillis() - validTime) <= file.lastModified());
								if (isValid) {
									Message msg = Message.obtain();
									msg.obj = pageData;
									msg.arg1 = pageNum;
									msg.what = PAGE_DATA_LOADED;
									handler.sendMessage(msg);
									return true;
								}
							}
						}
					}
					boolean success =  super.doInBackground(params);
					if (!success) {
						Message msg = Message.obtain();
						msg.obj = pageData;
						msg.arg1 = pageNum;
						msg.what = PAGE_DATA_LOADED;
						handler.sendMessage(msg);
						return false;
					}
					pageData = new JSONObject(outputStream.toString(ItemKeys.CHARSET));
					boolean isValid = false;
					if (pageData != null) {
                        long validTime = pageData.optLong(ItemKeys.KEY_VALID_TIME, ItemKeys.DEFAULT_VALID_TIME)*1000;
                        if (validTime > 0) {
							isValid = true;
						}
					}
					Message msg = Message.obtain();
					msg.obj = pageData;
					msg.arg1 = pageNum;
					msg.what = PAGE_DATA_LOADED;
					handler.sendMessage(msg);
					if (mBaseUrl.startsWith("http")) {
						if (isValid) {
							pageStore.write(fileName, outputStream.toByteArray());
							outputStream.close();
						}
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

			@Override
			protected InputStream toInputStream(String downloadUrl) throws IOException {
				if (mCurrentPageUrl.startsWith("http")) {
					return super.toInputStream(downloadUrl);
				}else {
					Activity activity = activityRef.get();
					int id = activity.getResources().getIdentifier(mCurrentPageUrl, "raw", activity.getPackageName());
					if (id > 0) {
						return activity.getResources().openRawResource(id);
					}
				}
				Log.e("PageApp", "UNKnown Page Error " +  downloadUrl  + "   " + mBaseUrl);
				return null;
			}
			
			
			
		};
		pageTask.execute();
	}
	
	protected void onPageData(JSONObject pageData, int pageNum){
		if (pageData == null) {
			mLoadMore.setState(LoadMoreState.ERROR);
			return;
		}
		Activity activity = activityRef.get();
		if (activity == null) {
			return;
		}
		handleMetas(pageData.optJSONArray(ItemKeys.KEY_METAS), pageNum);
		if (pageNum == PAGE_FIRST) {
			mListview = null;
			items = pageData.optJSONArray(ItemKeys.KEY_ITEMS);
			viewTypeCount = pageData.optInt(ItemKeys.KEY_VIEW_TYPE_COUNT, MAX_VIEW_TYPE_COUNT);
		}else {
			JSONArray pageItems = pageData.optJSONArray(ItemKeys.KEY_ITEMS);
			if (pageItems != null) {
				for(int i=0; i<pageItems.length(); i++){
					Object value = pageItems.opt(i);
					if (value == null) {
						continue;
					}
					items.put(value);
				}
			}
		}
		if (mListview == null) {
			mListview = (AbsListView) activity.findViewById(android.R.id.list);
			mListview.setVelocityScale(mVelocityScale);
			if (hasMore) {
			    if (mListview instanceof AbsListView) {
			    	mListview.setOnScrollListener(new OnScrollListener() {
						
						@Override
						public void onScrollStateChanged(AbsListView view, int scrollState) {
							
						}
						
						@Override
						public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
							if (totalItemCount == 0) {
								return;
							}
							if ((firstVisibleItem + visibleItemCount) >= totalItemCount) {
								if (mLoadMore.loadMore()) {
									loadPageData(mBaseUrl, mLoadMore.getCurrentPage());
								}
							}
						}
					});
				}
			}
		}
		if (hasMore) {
			mLoadMore.setState(LoadMoreState.INIT);
		}else {
			mLoadMore.setState(LoadMoreState.NONE_MORE);
		}
		if (mListview.getAdapter() == null) {
			mListview.setAdapter(new DynamicAdapter() {
				
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
						convertView = new ItemView(mListview.getContext());
					}
					if (convertView instanceof ItemView) {
						ItemView item = (ItemView)convertView;
						item.bindItem(items.optJSONObject(position), getBinderCallback());
					}
					return convertView;
				}
				
				 public int getViewTypeCount() {
				        return viewTypeCount;
				 }
			});
		}else {
			((BaseAdapter)mListview.getAdapter()).notifyDataSetChanged();
		}
	}
	
	
	protected void handleMetas(JSONArray metas, int pageNum) {
		 hasMore = false;
		String style = null;
		if (metas == null || metas.length() == 0) {
			return;
		}
		for (int i=0; i<metas.length(); i++) {
			JSONObject meta = metas.optJSONObject(i);
			if (meta == null) {
				return;
			}
			Iterator<?> keys = meta.keys();
			String key = "";
			if (keys.hasNext()) {
				key = keys.next().toString();
			}
			String name = key;
			String value = meta.optString(key, "");
			if (TextUtils.isEmpty(name)) {
				continue;
			}
			handleMeta(name, value,pageNum);
			if (pageNum == PAGE_FIRST) {
				if (ItemKeys.STYLE.equals(name)) {
					style = value;
				}
			}
		}
		if (pageNum == PAGE_FIRST) {
			Activity activity = activityRef.get();
			if (activity == null) {
				return;
			}
			FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.container);
			frameLayout.removeAllViews();
			View view = createViewByStyle(style, activity);
			frameLayout.addView(view, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
	}
	
	private View createViewByStyle(String style, Activity activity){
		if (ItemKeys.STYLE_GRID.equals(style)) {
			return LayoutInflater.from(activity).inflate(R.layout.style_grid,  null);
		}else {
			return LayoutInflater.from(activity).inflate(R.layout.style_list, null);
		}
	}

	protected void handleMeta(String name, String value, int pageNum){
		if (ItemKeys.KEY_HAS_MORE.equals(name)) {
			hasMore =  toBoolean(value);
		}else if (ItemKeys.KEY_TITLE.equals(name)) {
			Activity activity = activityRef.get();
			if (activity == null) {
				return;
			}
			activity.getActionBar().setTitle(value);
		}else if (ItemKeys.KEY_VELOCITY_SCALE.equals(name)) {
			try {
				mVelocityScale = Float.parseFloat(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void onDestroy() {
		if (pageTask != null) {
			pageTask.cancel(true);
			pageTask = null;
		}
		if (activityRef != null) {
              activityRef = null;
		}
		if (mListview != null) {
			mListview = null;
			return;
		}
	}
	
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case PAGE_DATA_LOADED:
			onPageData((JSONObject)msg.obj, msg.arg1);
			msg.obj = null;
			break;

		default:
			break;
		}
		return false;
	}
	
	
	
	
	public LoadMoreState getLoadMore() {
		if (mLoadMore == null) {
			mLoadMore = new LoadMoreState();
		}
		return mLoadMore;
	}

	public String getBaseUrl() {
		return mBaseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.mBaseUrl = baseUrl;
	}

	public BinderCallback getBinderCallback() {
		if (mBinderCallback == null) {
			mBinderCallback = new BinderCallback();
		}
		return mBinderCallback;
	}
	
	protected String buildPageUrlForPageNum(String url, final int pageNum){
		if (url.startsWith("http")) {
			Uri uri = Uri.parse(url);
			Uri.Builder builder = new Uri.Builder();
			builder.scheme(uri.getScheme()).authority(uri.getAuthority()).path(uri.getPath());
			Set<String> paramNameSet = uri.getQueryParameterNames();
			if (paramNameSet != null && paramNameSet.size() >0) {
				for (String paramName : paramNameSet) {
					if (ItemKeys.KEY_PAGE_NUM.equals(paramName)) {
						continue;
					}
					List<String> values = uri.getQueryParameters(paramName);
					if (values == null || values.size() == 0) {
						continue;
					}
					for(String value : values){
					     builder.appendQueryParameter(paramName, value);
					}
				}
			}
			builder.appendQueryParameter(ItemKeys.KEY_PAGE_NUM, String.valueOf(pageNum));
			return builder.build().toString();
		}else {
			return  url + pageNum;
		}
	}
	
	
	public boolean isUseCache() {
		return useCache;
	}


	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}


	protected boolean toBoolean(String value){
		if (value == null) {
			return false;
		}
		value = value.toLowerCase();
		if ("yes".equals(value) || "true".equals(value)) {
			return true;
		}
		return false;
	}
	
	

	public void setBinderCallback(BinderCallback binderCallback) {
		this.mBinderCallback = binderCallback;
	}


	private BinderCallback mBinderCallback;

	private static final int PAGE_DATA_LOADED = 1;
	public static final int PAGE_FIRST = 1;
	public static final int MAX_VIEW_TYPE_COUNT = 32;
}
