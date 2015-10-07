package com.efurture.marlin.view.util;

import java.io.BufferedReader;
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
}
