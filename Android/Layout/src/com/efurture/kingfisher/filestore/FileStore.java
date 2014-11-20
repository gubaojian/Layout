/**
 * 
 */
package com.efurture.kingfisher.filestore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;


public abstract class FileStore {

	/**
	 * @param context context
	 * @dir   dir folder in android files folder
	 * */
	public static FileStore from(Context context){
		return from(context,  "file_store");
	}
	
	/**
	 * @param context context
	 * @dir   dir folder in android files folder, with none limit, files never be auto cleaned
	 * */
	public static FileStore from(Context context, String dir){
		return new AndroidFileStore(context, dir);
	}
	
	/**
	 * @param context context
	 * @dir   dir folder in android files folder
	 * @size  file max numbers limit, over this limit, files will be cleaned
	 * */
	public static FileStore from(Context context, String dir, int size){
		return from(context, dir, size, 0);
	}
	
	/**
	 * @param    context context
	 * @dir      dir folder in android files folder
	 * @size     file max numbers limit, when over this limit, files over lifetime will be cleaned
	 * @lifetime file min lifetime, even file over limit number, 
	 * */
	public static FileStore from(Context context, String dir, int size, long lifetime){
		return new AndroidLimitFileStore(context, dir, size, lifetime);
	}
	
	public abstract  File getPath();
	
	public abstract File  toFile(String fileName);
	
	public abstract  boolean exists(String fileName);
	
	public  abstract byte[] read(String fileName) throws IOException;
	
	public  abstract String readString(String fileName) throws IOException;
	
	public  abstract InputStream toInputStream(String fileName) throws IOException;
	
    public abstract boolean delete(String fileName);
	
    public abstract boolean write(String fileName, String content) throws IOException ;
    
	public abstract boolean write(String fileName, byte[] bts) throws IOException ;
	
	public abstract  boolean write(String fileName, InputStream inputStream) throws IOException;
	
	/**
	 * clean file lifetime bigger than the lifetime
	 * */
	public abstract  boolean clean(long lifetime);
	
	
	
}
