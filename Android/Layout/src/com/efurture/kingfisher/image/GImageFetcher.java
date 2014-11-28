package com.efurture.kingfisher.image;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.WeakHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.efurture.kingfisher.filestore.FileStore;


public class GImageFetcher  {
	
	public static GImageFetcher shareFetcher(){
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
	
	public Bitmap loadBitmap(View view, String imageUrl){
		if (TextUtils.isEmpty(imageUrl)) {
			return null;
		}
		Bitmap bitmap =  null;
		WeakReference<Bitmap>  bitmapReference = getImageCache().get(imageUrl);
		if (bitmapReference != null) {
			bitmap = bitmapReference.get();
		}
		if (bitmap != null) {
			return bitmap;
		}
		if (imageUrl.startsWith("http://") 
				|| imageUrl.startsWith("https://")) {
			bitmap = loadNetworkBitmap(view, imageUrl);
		}else {
		    bitmap = loadLocalBitmap(view, imageUrl);
		}
		if (bitmap != null) {
			getImageCache().put(imageUrl,  new WeakReference<Bitmap>(bitmap));
		}
		return bitmap;
	}
	
	public void load(View view, String imageUrl){
		load(view, imageUrl, 0);
	}
	
	public void load(View view, String imageUrl, int placeholder){
		load(view, imageUrl, null, placeholder);
	}
	
	public void load(View view, String imageUrl, String hightedImageUrl){
		  load(view, imageUrl, hightedImageUrl, 0);
	}
	
	
	
	public void load(View view, String imageUrl, String hightedImageUrl, int placeholder){
		if (TextUtils.isEmpty(imageUrl) && TextUtils.isEmpty(hightedImageUrl)) {
			setDrawable(view, null);
			return;
		}
		if (placeholder > 0) {
			setDrawable(view, view.getResources().getDrawable(placeholder));
		}
		ImageAsyncTask<View> imageAsyncTask = getImageTasks().get(view);
		if (imageAsyncTask != null) {
			imageAsyncTask.cancel(false);
			imageAsyncTask = null;
		}
		imageAsyncTask = new ImageAsyncTask<View>(view, imageUrl, hightedImageUrl);
	    final ImageAsyncTask<View>  imageAsyncTaskRef = imageAsyncTask;
		view.post(new Runnable() {
			
			@Override
			public void run() {
				if(!imageAsyncTaskRef.isCancelled()){
				     imageAsyncTaskRef.execute();
				}
			}
		});
		getImageTasks().put(view, imageAsyncTask);
	}
	
	public void setDrawable(View view, Drawable drawable){
		if (view instanceof ImageView) {
			ImageView imgView = (ImageView) view;
			imgView.setImageDrawable(drawable);
		}else {
			 view.setBackgroundDrawable(drawable);
		}
	}
	
	private Bitmap loadNetworkBitmap(View view, String imageUrl){
		try {
			String md5 = md5(imageUrl);
		    FileStore imageStore = getImageStore(view.getContext());
			Bitmap bitmap = loadBitmapFromImageStore(imageStore, md5);
			if (bitmap != null) {
				return bitmap;
			}
		    URL url = new URL(imageUrl);
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			connection.addRequestProperty("Cache-Control", "no-cache");
			connection.setDefaultUseCaches(false);
			connection.setConnectTimeout(1000*10);
			connection.setReadTimeout(5000);
			connection.connect();
			InputStream httpInputStream = connection.getInputStream();
		    imageStore.write(md5,  httpInputStream);
		    bitmap = loadBitmapFromImageStore(imageStore, md5);
		    return bitmap;
		} catch (Exception e) {
			Log.e("ImageFetcher", "ImageFetcher Fetch Http ImageError," + imageUrl, e);
			return null;
		}
		
	}
	
	private Bitmap loadBitmapFromImageStore(FileStore imageStore, String md5) throws IOException{
		Bitmap bitmap =  null;
	    InputStream bitmapInputStream =  null;
	    try {
	    	bitmapInputStream =imageStore.toInputStream(md5);
		    if (bitmapInputStream != null) {
	              bitmap = BitmapFactory.decodeStream(bitmapInputStream);
	              bitmapInputStream.close();
			}
		}finally{
			if (bitmapInputStream != null) {
				 bitmapInputStream.close();
			}
		}
	    return bitmap;
	}
	
    private Bitmap loadLocalBitmap(View view, String  url){
    	int id = view.getResources().getIdentifier(url, "drawable", view.getContext().getPackageName());
    	Bitmap bitmap = null;
		if (id > 0) {
			bitmap = BitmapFactory.decodeResource(view.getResources(), id);
		}else {
			if (url.endsWith("?scale=true")) {
				url.substring(0, url.length() - 11 + 1);
				id = view.getResources().getIdentifier(url, "drawable", view.getContext().getPackageName());
				if (id > 0) {
					bitmap = BitmapFactory.decodeResource(view.getResources(), id);
				}
			}
		}
		return bitmap;
	}
    
    
    private static String md5(String message){ 
		 try { 
			  MessageDigest md = MessageDigest.getInstance("MD5");
			  byte[] hash = md.digest(message.getBytes("UTF-8")); //converting byte array to Hexadecimal
			  StringBuilder sb = new StringBuilder(2*hash.length); 
		      for(byte b : hash){ 
		    	  sb.append(String.format("%02x", b&0xff)); 
		      }
		      return sb.toString(); 
	    } catch (Exception e) { 
	    	 Log.e("Hash", "Md5 Hash Exception " + message);
	    	 return message.hashCode() + "";
	    } 
	}
    
    
	private WeakHashMap<View, ImageAsyncTask<View>> imageTasks;
	private LruCache<String, WeakReference<Bitmap>> imageCache;
	private FileStore imageStore;
	 
	
	WeakHashMap<View, ImageAsyncTask<View>> getImageTasks(){
		if (imageTasks == null) {
			imageTasks = new WeakHashMap<View, ImageAsyncTask<View>>();
		}
		return imageTasks;
	}
	
	LruCache<String, WeakReference<Bitmap>> getImageCache(){
		if (imageCache == null) {
			imageCache  = new LruCache<String,  WeakReference<Bitmap>>(32);
		}
		return imageCache;
	}
	
	FileStore getImageStore(Context context){
		 if (imageStore == null) {
			 imageStore = FileStore.from(context, "fetch_images", 512, ((long)14)*24*60*60*1000);
		}
        return imageStore;
	}
	
}
