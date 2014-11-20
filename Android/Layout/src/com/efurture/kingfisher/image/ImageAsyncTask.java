package com.efurture.kingfisher.image;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.StateSet;
import android.view.View;

public class ImageAsyncTask<T extends View> extends AsyncTask<Void, Void, Void> {


	 private  WeakReference<T> imageViewReference;
	 private  String imageUrl;
	 private  String hightedImageUrl;
	 private  Bitmap normalBitmap;
	 private  Bitmap hightedBitmap;
	 
	 public ImageAsyncTask(T imageView, String imageUrl, String hightedImageUrl){
		 imageViewReference = new WeakReference<T>(imageView);
		 this.imageUrl = imageUrl;
		 this.hightedImageUrl = hightedImageUrl;
	 }
	
	 public boolean isTaskValid(){
		 boolean isCancel = isCancelled();
		 if (isCancel) {
			return false;
		 }
		 return imageViewReference.get() != null;
	 }
	 
	 
	@Override
	protected Void doInBackground(Void... params) {
		if (!isTaskValid()) {
			return null;
		}
		if (!TextUtils.isEmpty(imageUrl)) {
			View  view =  imageViewReference.get();
			if (view != null) {
				normalBitmap = GImageFetcher.shareFetcher().loadBitmap(view, imageUrl);
			}
		}
		if (!isTaskValid()) {
			return null;
		}
		if (!TextUtils.isEmpty(hightedImageUrl)) {
			View  view =  imageViewReference.get();
			if (view != null) {
				hightedBitmap = GImageFetcher.shareFetcher().loadBitmap(view, hightedImageUrl);
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		if (!isTaskValid()) {
			return;
		}
		View  view =  imageViewReference.get();
		if (view == null) {
			
		}
		Drawable drawable = null;
		if (normalBitmap != null && hightedBitmap != null) {
			StateListDrawable stateListDrawable = new StateListDrawable();
			stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_focused}, new BitmapDrawable(view.getContext().getResources(), hightedBitmap));
			stateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(view.getContext().getResources(), normalBitmap));
			drawable = stateListDrawable;
		}else if (normalBitmap != null) {
			drawable = new BitmapDrawable(view.getContext().getResources(), normalBitmap);
		}else if (hightedBitmap != null) {
			StateListDrawable stateListDrawable = new StateListDrawable();
			stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_focused}, new BitmapDrawable(view.getContext().getResources(), hightedBitmap));
			stateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(view.getContext().getResources(), hightedBitmap));
			drawable = stateListDrawable;
		}
		if (drawable != null) {
			GImageFetcher.shareFetcher().setDrawable(view, drawable);
		}
		GImageFetcher.shareFetcher().getImageTasks().remove(view);
		normalBitmap = null;
		hightedBitmap = null;
	}

	@Override
	protected void onCancelled() {
		View  view =  imageViewReference.get();
		if (view == null) {
			GImageFetcher.shareFetcher().getImageTasks().remove(view);
		}
	}
	
	  

	
}
