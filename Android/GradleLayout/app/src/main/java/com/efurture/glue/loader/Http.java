package com.efurture.glue.loader;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by baobao on 15/9/15.
 */
class Http {

    public static OkHttpClient getClient(){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient();
        }
        return  okHttpClient;
    }

    private static OkHttpClient okHttpClient;


}
