/**
 * 
 */
package com.efurture.kingfisher.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;

class AndroidFileSystem  implements FileSystem{

	
	public static final String DIR = "kingfiser";
	
	private Context mContext;
	private File mFileSystemPath;

	public AndroidFileSystem(Context context) {
		super();
		this.mContext = context.getApplicationContext();
	}
	
	private void initIfNeed(){
		if (mFileSystemPath != null) {
			if(!mFileSystemPath.canWrite()){
				mFileSystemPath = null;
			}
		}
		if (mFileSystemPath == null) {
			File parentPath = null ;
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				try {
					parentPath = mContext.getExternalFilesDir(null);
				} catch (Exception e) {
					GLog.e("AndroidFileSystemInitDirError", e);
					parentPath = null;
				}
				if (parentPath != null && !parentPath.canWrite()) {
					parentPath = null;
				}
				
				if (parentPath == null) {
					parentPath = mContext.getExternalCacheDir();
					if (parentPath != null && !parentPath.canWrite()) {
						parentPath = null;
					}
				}
			}
			
			
			
			if (parentPath == null) {
				parentPath = mContext.getFilesDir();	
				if (parentPath != null && !parentPath.canWrite()) {
					parentPath = null;
				}
			}
			

			if (parentPath == null) {
				parentPath = mContext.getCacheDir();
				if (parentPath != null && !parentPath.canWrite()) {
					parentPath = null;
				}
			}
			
			if (parentPath == null) {
				return;
			}
			
			String dir = DIR;
			mFileSystemPath = new File(parentPath, dir);
		}
		
		if (!mFileSystemPath.exists()) {
			boolean success = mFileSystemPath.mkdirs();
			if(!success){
				mFileSystemPath.mkdirs();
			}
		}
	}
	

	@Override
	public String getPath() {
		initIfNeed();
		return mFileSystemPath.getAbsolutePath();
	}

	@Override
	public boolean exists(String name) {
		File file = fileFromName(name);
		return  file.exists();
	}

	@Override
	public byte[] read(String name) throws IOException {
		File file = fileFromName(name);
		if (!file.exists()) {
			return null;
		}
		FileInputStream inputStream;
		inputStream = new FileInputStream(file);
		byte[] bts = IOUtils.read(inputStream);
		return  bts;
	}
	
	@Override
	public boolean delete(String fileName) {
		File file = fileFromName(fileName);
		File tmp = new File(file.getAbsolutePath() + ".tmp");
		if (tmp.exists()) {
			tmp.delete();
		}
		return file.delete();
	}

	
	@Override
	public boolean write(String name, byte[] bts) throws IOException {
		File file = fileFromName(name);
		GLog.d("AndroidFileSystem Write File " + name + " to " + file);
		File tmp = new File(file.getAbsolutePath() + ".tmp");
		if (tmp.exists()) {
			tmp.delete();
		}
		File parent = tmp.getParentFile();
		if (parent != null) {
			if (!parent.exists()) {
				parent.mkdirs();
			}
		}
		boolean writeSuccess = false;
		try {
			FileOutputStream outputStream;
			outputStream = new FileOutputStream(tmp);
			writeSuccess = IOUtils.write(outputStream, bts);
			if (writeSuccess) {
				if (file.exists()) {
					file.delete();
				}
				tmp.renameTo(file);
			}
			return writeSuccess;
		}finally {
			if (!writeSuccess) {
				tmp.delete();
			}
		}
	}
	
	private File fileFromName(String name){
		initIfNeed();
		File f = new File(mFileSystemPath, name);
		return f;
	}

	@Override
	public boolean write(String fileName, InputStream inputStream) throws IOException {
		File file = fileFromName(fileName);
		GLog.d("AndroidFileSystem Write File " + fileName + " to " + file);
		File tmp = new File(file.getAbsolutePath() + ".tmp");
		if (tmp.exists()) {
			tmp.delete();
		}
		File parent = tmp.getParentFile();
		if (parent != null) {
			if (!parent.exists()) {
				parent.mkdirs();
			}
		}
		boolean writeSuccess = false;
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(tmp);
			byte[] bts = new byte[1024];
			int count = -1;
			while ((count = inputStream.read(bts)) >= 0) {
				outputStream.write(bts, 0, count);
				writeSuccess = true;
			}
			
			if (writeSuccess) {
				if (file.exists()) {
					file.delete();
				}
				tmp.renameTo(file);
			}
			return writeSuccess;
		}finally {
			if (!writeSuccess) {
				tmp.delete();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	@Override
	public File toFile(String name) {
		File file = fileFromName(name);
		return file;
	}

	
}
