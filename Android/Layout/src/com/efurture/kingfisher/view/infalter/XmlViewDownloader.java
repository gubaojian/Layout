package com.efurture.kingfisher.view.infalter;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.efurture.kingfisher.download.DownloadTask;
import com.efurture.kingfisher.view.engine.ViewInflater;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;


class XmlViewDownloader {
	private static Map<String,String> downlodingTemplets = new ConcurrentHashMap<String,String>();
	private static Map<String, String> downloadSuccessTemplate = new ConcurrentHashMap<String, String>();
	
	public static void downloadXmlView(Context context, String downloadUrl, final String name, File file) {
		if (TextUtils.isEmpty(downloadUrl)) {
			return;
		}
		if(downlodingTemplets.containsKey(name)){
			return;
		}
		downlodingTemplets.put(name, "");
		final WeakReference<Context> contextRef = new WeakReference<Context>(context);
		DownloadTask downloadTask = new DownloadTask(file, downloadUrl){

			@Override
			protected void onCancelled() {
				downlodingTemplets.remove(name);
				notifyDownLoadFailed();
			}

			@Override
			protected void onPostExecute(Boolean success) {
				downlodingTemplets.remove(name);
				if (success) {
					//prevent circle download, when xml has probleam, cann't infalte view
					if (!downloadSuccessTemplate.containsKey(name)) { 
						 notifyDownLoadSuccess();
					}
					downloadSuccessTemplate.put(name, "");
				}else {
					notifyDownLoadFailed();
				}
			}
			
			public void notifyDownLoadSuccess() {
				Context context = contextRef.get();
				if (context == null) {
					return;
				}
				LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
				Intent intent = new Intent(ViewInflater.ACTION_DOWNLOAD_SUCCESS);
				intent.putExtra(ViewInflater.EXTRA_NAME, name);
				intent.putExtra(ViewInflater.EXTRA_HAS_REMAIN, downlodingTemplets.size() > 0);
				localBroadcastManager.sendBroadcast(intent);
			}

			public void notifyDownLoadFailed() {
				Context context = contextRef.get();
				if (context == null) {
					return;
				}
				LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
				Intent intent = new Intent(ViewInflater.ACTION_DOWNLOAD_FAILED);
				intent.putExtra(ViewInflater.EXTRA_NAME, name);
				intent.putExtra(ViewInflater.EXTRA_HAS_REMAIN, downlodingTemplets.size() > 0);
				localBroadcastManager.sendBroadcast(intent);
			}

		};
		downloadTask.execute();
	}
}
