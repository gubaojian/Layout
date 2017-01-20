package com.efurture.gule.hybrid.api.application;

import android.os.Handler;
import android.os.Looper;

import com.furture.react.JSRef;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * http模块的默认实现
 * */
public class HttpApi {


	/**
	 * 成功回调
	 * */
	public static final String ON_SUCCESS = "onSuccess";

	/**
	 * 错误回调
	 * */
	public static final String ON_ERROR = "onError";

 	
	public void get(String url, JSRef callback){
		Request request = new Request.Builder().get().url(url).build();
		execute(request, callback);
	} 
	
	public void post(String url, String json, JSRef callback){
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
		Request request = new Request.Builder().post(requestBody).url(url).build();
		execute(request, callback);
	}


	public void getJSON(String url, JSRef callback){
		Request request = new Request.Builder().get().url(url).build();
		executeJSON(request, callback);
	}

	public void postJSON(String url, String json, JSRef callback){
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
		Request request = new Request.Builder().post(requestBody).url(url).build();
		executeJSON(request, callback);
	}



	private void  execute(Request request, final JSRef callback){
		okHttpClient.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				try {
					final String  body =  response.body().string();
					handler.post(new Runnable() {
						@Override
						public void run() {
							callback.getEngine().call(callback, ON_SUCCESS, body);
						}
					});
				} catch (final Exception e) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							callback.getEngine().call(callback, ON_ERROR, e.getMessage());
						}
					});
				}
			}

			@Override
			public void onFailure(Request req, final IOException exception) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						callback.getEngine().call(callback, ON_ERROR, exception.getMessage());
					}
				});
			}
		});
	}



	private void  executeJSON(Request request, final JSRef callback){
		okHttpClient.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				
				try {
					final JSONObject json = new JSONObject(response.body().string());
					handler.post(new Runnable() {
						@Override
						public void run() {
							callback.getEngine().call(callback, ON_SUCCESS, json);
						}
					});
				} catch (final Exception e) {

					handler.post(new Runnable() {
						@Override
						public void run() {
							callback.getEngine().call(callback, ON_ERROR, toErrorJSON(e));
						}
					});
				}
			}
			
			@Override
			public void onFailure(Request req, final IOException exception) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						callback.getEngine().call(callback, ON_ERROR, toErrorJSON(exception));
					}
				});
			}
		});
	}


	private static final  JSONObject toErrorJSON(Exception e){
		JSONObject json = new JSONObject();
		try {
			json.put("error", e.getMessage());
		} catch (JSONException jsone) {}
		return json;
	}
	
	private static final OkHttpClient okHttpClient = new OkHttpClient();
	private static final Handler handler = new Handler(Looper.getMainLooper());

}
