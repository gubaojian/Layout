package com.efurture.kingfisher.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.text.TextUtils;


public class FileDownloader {
	private static Map<String,String> downlodingTemplets = new ConcurrentHashMap<String,String>();
	private static Map<String, String> downloadSuccessTemplate = new ConcurrentHashMap<String, String>();
	
	
	public static void downloadTemplate(Context context, String downloadUrl, String name, int version) {
		if (TextUtils.isEmpty(downloadUrl)) {
			return;
		}
		final String fileName = version + "/" + name;
		if(downlodingTemplets.containsKey(fileName)){
			return;
		}
		downlodingTemplets.put(fileName, "");
		DownloadTask downloadTask = new DownloadTask(name, version, downloadUrl, context){
			@Override
			protected void onDownLoadFinish(boolean success) {
				//模板下载完成后，再进行通知
				if (downlodingTemplets.size() <= 1) {
					if (success) {
						if (!downloadSuccessTemplate.containsKey(fileName)) { //prevent circle download
							 notifyDownLoadSuccess();
						}
					}else {
						notifyDownLoadFailed();
					}
				}
				downlodingTemplets.remove(fileName);
				if (success) {
					downloadSuccessTemplate.put(fileName, "");
				}
			}
		};
		downloadTask.execute();
	}
}
