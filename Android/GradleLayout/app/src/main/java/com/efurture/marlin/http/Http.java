package com.efurture.marlin.http;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by baobao on 15/9/15.
 */
public class Http {

    public static OkHttpClient getClient(){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient();
        }
        return  okHttpClient;
    }

    private static OkHttpClient okHttpClient;


}
