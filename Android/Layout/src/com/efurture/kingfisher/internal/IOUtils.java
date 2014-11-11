package com.efurture.kingfisher.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class IOUtils {
	
	
	public static boolean write(final OutputStream out, byte[] bts) throws IOException{
		try {
			out.write(bts);
			return true;
		}finally{
			if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static byte[] read(final InputStream in) throws IOException{
		ByteArrayOutputStream  output = null;
		try{
			if(in == null){
				return null;
			}
			output = new ByteArrayOutputStream(1024*2);
			byte[]  buffer = new byte[1024];
			int len;
			while((len = in.read(buffer)) != -1){
				output.write(buffer, 0, len);
			}
			return output.toByteArray();
		}finally{
			if(output != null){
				try {
					output.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in != null){
				try {
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
