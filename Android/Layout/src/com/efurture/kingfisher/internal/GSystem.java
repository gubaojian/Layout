/**
 * 
 */
package com.efurture.kingfisher.internal;


import android.content.Context;

/**
 * @author 剑白
 * @date 2014年4月11日
 *  */
public abstract class GSystem {
	
	private static FileSystem mFileSystem;
    private static boolean init = false;
    
  
    
	public static FileSystem getFileSystem(){
		return mFileSystem;
	}
	

	public static void init(Context context){
		if (!init) {
			synchronized (GSystem.class) {
				if (!init) {
					initPutiSystem(context);
					init = true;
				}
			}
		}
	}
	
	/**
	 * 首先初始化文件系统
	 * */
	private static final void initPutiSystem(Context context){
		mFileSystem = new AndroidFileSystem(context);
	}
}
