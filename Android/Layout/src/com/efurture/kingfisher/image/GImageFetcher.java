package com.efurture.kingfisher.image;

import android.view.View;
import android.widget.ImageView;

public class GImageFetcher  {
	
	public static GImageFetcher shareImageFetcher(){
		if (imageFetcher != null) {
			return imageFetcher;
		}
		synchronized (GImageFetcher.class) {
			if (imageFetcher  == null) {
				imageFetcher = new GImageFetcher();
			}
		}
		return imageFetcher;
	}

	private static GImageFetcher imageFetcher;
	private GImageFetcher(){
	}
	
	public void load(View imageView, String imageUrl){
		load(imageView, imageUrl, null);
	}
	public void load(View imageView, String imageUrl, String hightedImageUrl){
		if (imageUrl == null && hightedImageUrl == null) {
			if (imageView instanceof ImageView) {
				((ImageView)imageView).setImageDrawable(null);
			}else {
				 imageView.setBackgroundDrawable(null);
			}
			return;
		}
		if (imageView.getTag() instanceof ImageAsyncTask) {
			 ImageAsyncTask<?> imageAsyncTask  = (ImageAsyncTask<?>) imageView.getTag();
			 imageAsyncTask.cancel(true);
			 imageAsyncTask = null;
		}
		ImageAsyncTask<View> imageAsyncTask = new ImageAsyncTask<View>(imageView);
		if (hightedImageUrl != null) {
			 imageAsyncTask.execute(imageUrl, hightedImageUrl);
		}else {
			 imageAsyncTask.execute(imageUrl);
		}
		imageView.setTag(imageAsyncTask);
	}

}
