package com.efurture.kingfisher.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<Void, Void, Boolean> {
	private File  file;
    private String downloadUrl;
    private boolean override;
    private OutputStream outputStream;
   
	public DownloadTask(String fileName, String downloadUrl) {
		this(fileName, downloadUrl, true);
	}
	
	public DownloadTask(String fileName, String downloadUrl, boolean override) {
		this(new File(fileName),downloadUrl, override);
	}
	
	public DownloadTask(File file, String downloadUrl) {
		this(file, downloadUrl, true);
	}
	
	public DownloadTask(OutputStream outputStream,String downloadUrl){
		this.outputStream = outputStream;
		this.downloadUrl = downloadUrl;
		this.override = true;
	}
	
	public DownloadTask(File file, String downloadUrl, boolean override) {
		super();
		this.file = file;
		this.downloadUrl = downloadUrl;
		this.override = override;
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		if (!override) {
			if (file != null) {
				if (file.exists()) {
					return true;
				}
			}
		}
		InputStream inputStream = null;
		try {
			URL url  = new URL(downloadUrl);
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			connection.setDefaultUseCaches(false);
			connection.connect();
			if (connection instanceof HttpURLConnection) {
				HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
				httpURLConnection.setConnectTimeout(1000*16);
				httpURLConnection.setReadTimeout(1000*8);
				if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					 Log.w("DownloadTask", url + "Server returned HTTP " + httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage());
					return false;
	            }
			}
			inputStream = connection.getInputStream();
			
			boolean writeSuccess = false;
			FileOutputStream fileOutputStream = null;
			File tmp = null;
			try {
				OutputStream output = null;
				if (file != null) {
					tmp = new File(file.getAbsolutePath() + ".tmp");
					if (tmp.exists()) {
						tmp.delete();
					}
					File parent = tmp.getParentFile();
					if (parent != null) {
						if (!parent.exists()) {
							 parent.mkdirs();
						}
					}
					fileOutputStream = new FileOutputStream(tmp);
					output = fileOutputStream;
				}else {
					output = outputStream;
				}
				byte[] bts = new byte[1024];
				int count = -1;
				while ((count = inputStream.read(bts)) >= 0) {
					output.write(bts, 0, count);
					writeSuccess = true;
				}
				if (file != null) {
					if (writeSuccess) {
						if (file.exists()) {
							file.delete();
						}
						writeSuccess = tmp.renameTo(file);
					}
				}
				return writeSuccess;
			}finally {
				if (!writeSuccess) {
					tmp.delete();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					Log.e("DownloaderTask","Close Download Connection Error", e);
				}
			}
		}
	}
	
	
	
}
