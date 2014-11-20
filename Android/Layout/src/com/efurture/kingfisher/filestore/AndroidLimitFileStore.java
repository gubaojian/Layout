/**
 * 
 */
package com.efurture.kingfisher.filestore;

import java.io.File;

import android.content.Context;

class AndroidLimitFileStore  extends AndroidFileStore{

	protected int size;
	protected long lifetime;
	
	public AndroidLimitFileStore(Context context, String dir, int size, long lifetime) {
		super(context, dir);
		this.size = size;
		this.lifetime = lifetime;
	}
	
	@Override
	public File fileFromName(String name){
		initIfNeed();
		int  code = hash(name.hashCode()) & (size-1);
		String identify = name;
		File f = new File(mFileSystemPath, code + "/" + identify);
		return f;
	}
	
	public void beforeWrite(File file){
		File parent = file.getParentFile();
		if (parent == null) {
			return;
		}
		long createTime = System.currentTimeMillis() - lifetime;
		cleanFolder(parent, createTime);
		return;
	}
	
	/**
	 * hash functions copy from hashmap 
	 * */
	 static int hash(int h) {
	        // This function ensures that hashCodes that differ only by
	        // constant multiples at each bit position have a bounded
	        // number of collisions (approximately 8 at default load factor).
	        h ^= (h >>> 20) ^ (h >>> 12);
	        return h ^ (h >>> 7) ^ (h >>> 4);
	    }
}
