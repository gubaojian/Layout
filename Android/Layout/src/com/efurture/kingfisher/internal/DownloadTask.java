/**
 * 
 */
package com.efurture.kingfisher.internal;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import com.efurture.kingfisher.ViewInflater;



class DownloadTask extends AsyncTask<Void, Void, Boolean> {

	private String fileName;
	private WeakReference<Context> contextRef;
    private String downloadUrl;
    private String name;
    private int version;
	public DownloadTask(String name, int version, String downloadUrl, Context context) {
		super();
		this.name = name;
		this.version = version;
		this.fileName = version + "/" + name;
		this.downloadUrl = downloadUrl;
		this.contextRef = new WeakReference<Context>(context);
	}

	@Override
	protected Boolean doInBackground(Void... params) {
			GLog.d("Download Template " + downloadUrl);
			URLConnection connection = null;
			try {
				URL url  = new URL(downloadUrl);
				connection = url.openConnection();
				connection.setUseCaches(false);
				connection.connect();
				if (connection instanceof HttpURLConnection) {
					HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
					httpURLConnection.setConnectTimeout(1000*16);
					httpURLConnection.setReadTimeout(1000*8);
					if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
						 GLog.w(url + "Server returned HTTP " + httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage());
						return false;
		            }
				}
				InputStream inputStream = connection.getInputStream();
				return GSystem.getFileSystem().write(fileName, inputStream);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}finally {
				if (connection != null){
					if (connection instanceof HttpURLConnection) {
						try {
							((HttpURLConnection)connection).disconnect();
						} catch (Exception e) {
							GLog.e("Close Download Connection Error", e);
						}
					}
				}
			}
	}

	@Override
	protected void onCancelled() {
		this.onDownLoadFinish(false);
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Boolean result) {
		try {
			if (result == null) {
				result = false;
			}
			this.onDownLoadFinish(result);
			super.onPostExecute(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onDownLoadFinish(boolean success) {

	}

	public void notifyDownLoadSuccess() {
		Context context = contextRef.get();
		if (context == null) {
			return;
		}
		LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
		Intent intent = new Intent(ViewInflater.ACTION_DOWNLOAD_SUCCESS);
		intent.putExtra(ViewInflater.EXTRA_NAME, name);
		intent.putExtra(ViewInflater.EXTRA_VERSION, version);
		localBroadcastManager.sendBroadcast(intent);
	}

	public void notifyDownLoadFailed() {
		Context context = contextRef.get();
		if (context == null) {
			return;
		}
		LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
		Intent intent = new Intent(ViewInflater.ACTION_DOWNLOAD_FAILED);
		intent.putExtra(ViewInflater.EXTRA_NAME, fileName);
		localBroadcastManager.sendBroadcast(intent);
	}

	
	

}
