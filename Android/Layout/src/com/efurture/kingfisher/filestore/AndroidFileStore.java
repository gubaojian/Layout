/**
 * 
 */
package com.efurture.kingfisher.filestore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

class AndroidFileStore  extends FileStore{

	protected Context mContext;
	protected File mFileSystemPath;
	protected String dir;
	
	
	public AndroidFileStore(Context context, String dir) {
		super();
		this.mContext = context.getApplicationContext();
		this.dir = dir;
	}
	
	public void initIfNeed(){
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
					Log.e("FileSystem","getExternalFilesDir Error", e);
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
	public  File getPath(){
		initIfNeed();
		return mFileSystemPath;
	}
	
	@Override
	public File toFile(String name) {
		File file = fileFromName(name);
		return file;
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
	
	public  String readString(String fileName) throws IOException{
		 byte[] bts = read(fileName);
		 if (bts == null) {
			return null;
		}
		 return new String(bts, "UTF-8");
	}
	
	public InputStream toInputStream(String name) throws IOException{
		File file = fileFromName(name);
		if (!file.exists()) {
			return null;
		}
		FileInputStream inputStream;
		inputStream = new FileInputStream(file);
		return inputStream;
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
		if (bts == null) {
			return false;
		}
		File file = fileFromName(name);
		beforeWrite(file);
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
				writeSuccess = tmp.renameTo(file);
			}
			return writeSuccess;
		}finally {
			if (!writeSuccess) {
				tmp.delete();
			}
		}
	}

	@Override
	public boolean write(String fileName, InputStream inputStream) throws IOException {
		File file = fileFromName(fileName);
		beforeWrite(file);
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
				writeSuccess = tmp.renameTo(file);
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
	public boolean write(String fileName, String content) throws IOException {
		if (content == null) {
			return false;
		}
        return write(fileName, content.getBytes("UTF-8"));
	}

	@Override
	public boolean clean(long lifetime){
		initIfNeed();
		long createTime  = System.currentTimeMillis() - lifetime;
		cleanFolder(mFileSystemPath, createTime);
		return false;
	}
	
	
	public File fileFromName(String name){
		initIfNeed();
		File f = new File(mFileSystemPath, name);
		return f;
	}
	
	
	public void beforeWrite(File file){
		  return;
	}
	
	public void cleanFolder(File file, long minCreateTime){
		if (file == null) {
			return;
		}
		if (file.isDirectory()) {
			File[] subFiles =  file.listFiles();
			if (subFiles != null) {
				for(File subFile : subFiles){
					  cleanFolder(subFile, minCreateTime);
				}
			}
		}
		if (file.lastModified() < minCreateTime) {
			if (file.isDirectory()) {
				File[] subFiles =  file.listFiles();
				if (subFiles == null || subFiles.length ==0) {
					file.delete();
				}
			}else {
				file.delete();
			}
		}
	}
	
}
