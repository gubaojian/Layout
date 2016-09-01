package com.efurture.glue.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by baobao on 15/10/6.
 */
public class IOUtils {

    public static final String toString(InputStream inputStream){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int length = 0;
            while ((length = reader.read(buffer))  >= 0) {
                sb.append(buffer, 0, length);
            }
            return sb.toString();
        }catch (IOException exception){
            throw  new RuntimeException(exception);
        }finally {
            try{
                if (reader != null){
                    reader.close();
                }
                inputStream.close();
            }catch (Exception e){}
        }
    }


    public static final byte[] toBytes(InputStream inputStream){
        BufferedInputStream bufferInputStream = null;
        try {
            bufferInputStream = new BufferedInputStream(inputStream);
            ByteArrayOutputStream  output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = bufferInputStream.read(buffer))  >= 0) {
                output.write(buffer, 0, length);
            }
            return output.toByteArray();
        }catch (IOException exception){
            throw  new RuntimeException(exception);
        }finally {
            try{
                if (bufferInputStream != null){
                    bufferInputStream.close();
                }
                inputStream.close();
            }catch (Exception e){}
        }
    }
}
