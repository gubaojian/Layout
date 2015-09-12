package com.efurture.kingfisher.hybrid;

import java.io.IOException;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class HybridView {

	
	
	
	OkHttpClient okHttpClient = new OkHttpClient();
	//Cache cache = new Cache(directory, maxSize);
	//okHttpClient.setCache(cache)
	Request request = new Request.Builder().build();
	okHttpClient.newCall(request).enqueue(new Callback() {
		
		@Override
		public void onResponse(Response response) throws IOException {
			response.body().byteStream();
		}
		
		@Override
		public void onFailure(Request request, IOException exception) {
			// TODO Auto-generated method stub
			
		}
	});
}
