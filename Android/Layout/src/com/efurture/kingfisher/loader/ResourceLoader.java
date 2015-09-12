package com.efurture.kingfisher.loader;

import java.io.IOException;
import java.io.InputStream;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.net.Uri;
import android.webkit.URLUtil;

public class ResourceLoader {

	
	public void loadUrl(Uri uri){
		if(URLUtil.isAssetUrl(uri.toString())){
		  InputStream inputStream =	uri.getPath();
		}
		OkHttpClient okHttpClient = new OkHttpClient();
		//Cache cache = new Cache(directory, maxSize);
		//okHttpClient.setCache(cache)
		Request request = new Request.Builder().cacheControl(CacheControl.FORCE_CACHE).url(uri.toString()).build();
		okHttpClient.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				response.body().byteStream();
			}
			
			@Override
			public void onFailure(Request request, IOException exception) {
				request;
			}
		});
	}
	
	
}
