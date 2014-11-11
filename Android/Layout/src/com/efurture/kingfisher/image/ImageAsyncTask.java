package com.efurture.kingfisher.image;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.util.StateSet;
import android.view.View;
import android.widget.ImageView;

public class ImageAsyncTask<T extends View> extends AsyncTask<String, Void, List<Drawable>> {

	 private  WeakReference<T> imageViewReference;
	 private static LruCache<String, WeakReference<Drawable>> imgCache = new LruCache<String,  WeakReference<Drawable>>(24);
	
	 public ImageAsyncTask(T imageView){
		 imageViewReference = new WeakReference<T>(imageView);
	 }
	
	@Override
	protected List<Drawable> doInBackground(String... params) {
		if (params == null || params.length == 0) {
			return null;
		}
		List<Drawable> imageList = new ArrayList<Drawable>(2);
		imageList.add(getDrawableFromUrl(params[0]));
		if (isCancelled()) {
			return null;
		}
		if (params.length > 1) {
			imageList.add(getDrawableFromUrl(params[1]));
		}
		return imageList;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPostExecute(List<Drawable> imageList) {
		if (imageList == null || imageList.size() == 0) {
			return;
		}
		if (imageViewReference == null) {
			return;
		}
		T view = imageViewReference.get();
		if (view == null) {
			return;
		}
		if (view instanceof ImageView) {
			ImageView imageView = (ImageView)view;
			if (imageList.size() == 1 || imageList.get(1) == null) {
				imageView.setImageDrawable(imageList.get(0));
			}else {
				StateListDrawable stateListDrawable = new StateListDrawable();
				stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_focused}, imageList.get(1));
				stateListDrawable.addState(StateSet.WILD_CARD, imageList.get(0));
				imageView.setImageDrawable(stateListDrawable);
			}
			return;
		}
		
		if (imageList.size() == 1 || imageList.get(1) == null) {
			view.setBackgroundDrawable(imageList.get(0));
		}else {
			StateListDrawable stateListDrawable = new StateListDrawable();
			stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_focused}, imageList.get(1));
			stateListDrawable.addState(StateSet.WILD_CARD, imageList.get(0));
			view.setBackgroundDrawable(stateListDrawable);
		}
		
	}
	
	
	private Drawable getDrawableFromUrl(String imageUrl){
		if (imageUrl == null) {
			return null;
		}
		WeakReference<Drawable> drawableCache = imgCache.get(imageUrl);
		if (drawableCache != null) {
			Drawable drawable = drawableCache.get();
			if (drawable != null) {
				return drawable;
			}
		}
		View view = imageViewReference.get();
		if (view == null) {
			 return null;
		}
		Drawable drawable = null;
		InputStream inputStream = null;
		try {
			if (imageUrl.startsWith("http://") 
					|| imageUrl.startsWith("https://")) {
				URL url = new URL(imageUrl);
				URLConnection connection = url.openConnection();
				connection.setUseCaches(true);
				connection.setDefaultUseCaches(true);
				connection.setConnectTimeout(1000*10);
				connection.setReadTimeout(5000);
				connection.connect();
				inputStream = connection.getInputStream();
				drawable = new BitmapDrawable(view.getResources(), inputStream);
			}else {
				int id = view.getResources().getIdentifier(imageUrl, "drawable", view.getContext().getPackageName());
				if (id > 0) {
					drawable = view.getResources().getDrawable(id);
				}else {
					if (imageUrl.endsWith("?resizable=true")) {
						imageUrl.substring(0, imageUrl.length() - 15 + 1);
						id = view.getResources().getIdentifier(imageUrl, "drawable", view.getContext().getPackageName());
						if (id > 0) {
							drawable = view.getResources().getDrawable(id);
						}
					}
				}
			}
		} catch (Exception e) {
			 Log.e("ImageAsyncTask", "ImageAsyncTask Download Error, ImageUrl " + imageUrl, e);
		}finally{
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					 Log.e("ImageAsyncTask", "ImageAsyncTask Close InputStream Error" + imageUrl, e);
				}
			}
		}
		imgCache.put(imageUrl, new WeakReference<Drawable>(drawable));
		return drawable;
	}
	
	

}
