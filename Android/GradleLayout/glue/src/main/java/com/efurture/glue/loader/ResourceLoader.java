package com.efurture.glue.loader;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.webkit.URLUtil;

import com.efurture.glue.GLog;
import com.efurture.glue.utils.IOUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;



/***
 if(absoluteUrl.getPath() != null){
 String path = absoluteUrl.getPath().trim();
 final File file = new File(context.getFilesDir(),  HYBRID_DIR + "/" + path);
 if(file.exists()){
 try {
 FileInputStream fileInputStream = new FileInputStream(file);
 callback.onStream(fileInputStream);
 }catch (Exception e){
 GLog.e("illegal file on files dir " + file.getPath());
 file.delete();
 }
 }else {
 if ("true".equals(absoluteUrl.getQueryParameter("assets"))){
 InputStream inputStream = null;
 try {
 inputStream = context.getAssets().open(path);
 if (inputStream != null){
 callback.onStream(inputStream);
 }
 } catch (IOException e) {}
 }
 }


 if (URLUtil.isAssetUrl(absoluteUrl.toString())){
 InputStream inputStream = null;
 try {
 String fileName = path.substring(15);
 inputStream = context.getAssets().open(fileName);
 if (inputStream != null){
 callback.onStream(inputStream);
 return;
 }
 } catch (IOException e) {
 throw  new RuntimeException(e);
 }
 return;
 }
 }
 可通过扩展,进行离线加载
 *
 * */

public class ResourceLoader {

	private static final String HYBRID_DIR = "hybrid";

	private URL pageUrl;

	protected Context context;

	protected Handler handler = new Handler();

	public ResourceLoader(Context context) {
		this.context = context;
	}



	public void loadUrl(final String url, final Callback callback){
		loadURL(toAbsPageUrl(url), callback);
	}


	protected  void loadURL(final URL url, final Callback callback){


		if(url == null){
			callback.onStream(null);
			return;
		}

		if(pageUrl  == null){
			pageUrl = url;
		}

		if("file".equals(url.getProtocol())){
			if (URLUtil.isAssetUrl(url.toString())){
				InputStream inputStream = null;
				try {
					String path = url.getPath();
					String fileName = path.substring(15);
					inputStream = context.getAssets().open(fileName);
					if (inputStream != null){
						byte[] bts = IOUtils.toBytes(inputStream);
						callback.onStream(bts);
						return;
					}
				} catch (IOException e) {
					throw  new RuntimeException(e);
				}
				return;
			}
		}


		OkHttpClient okHttpClient = Http.getClient();
		Request request = new Request.Builder().url(url).build();
		okHttpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				GLog.e("Request url error " + url, e);
				handler.post(new Runnable() {
					@Override
					public void run() {
						callback.onStream(null);
					}
                });
			}

			@Override
			public void onResponse(Response response) throws IOException {
				final byte[] bts = response.body().bytes();
				handler.post(new Runnable() {
					@Override
					public void run() {
						callback.onStream(bts);
					}
				});
			}
		});

	}

	public URL toAbsPageUrl(String url){
		try {
			URL absoluteUrl = null ;
			if(pageUrl != null){
				absoluteUrl = new URL(pageUrl, url);
			}else{
				absoluteUrl = new URL(url);
			}
			return  absoluteUrl;
		}catch (MalformedURLException mfe){
			 GLog.e("toAbsPageUrl error " + url,  mfe);
			 return null;
		}
	}

	public URL getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		try {
			this.pageUrl = new URL(pageUrl);
		} catch (MalformedURLException e) {
			throw  new RuntimeException(pageUrl + " is illegal URL", e);
		}
	}

	public interface Callback{
		void onStream(byte[]  bts);
	}
	
}
