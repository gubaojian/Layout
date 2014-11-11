/**
 * 
 */
package com.efurture.kingfisher.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author 剑白
 * @date 2014年2月28日
 *  */
interface FileSystem {

	public String getPath();
	public File toFile(String fileName);
	public boolean exists(String fileName);
	
	public byte[] read(String fileName) throws IOException ;
	
	public boolean delete(String fileName);
	
	public boolean write(String fileName, byte[] bts) throws IOException ;
	
	public boolean write(String fileName, InputStream inputStream) throws IOException ;
}
