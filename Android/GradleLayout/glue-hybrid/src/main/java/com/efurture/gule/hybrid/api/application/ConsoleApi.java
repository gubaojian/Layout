package com.efurture.gule.hybrid.api.application;

import android.util.Log;

/**
 * Created by furture on 16/6/23.
 * Console模块的默认实现
 */
public class ConsoleApi {

    /**
     * 打印log信息log
     * */
    public void log(Object object){
        if(object == null){
            Log.i("console", "null");
            return;
        }
        Log.i("console", object.toString());
    }

    /**
     * 打印warn信息log
     * */
    public void warn(Object object){
        if(object == null){
            Log.w("console", "null");
            return;
        }
        Log.w("console", object.toString());
    }

    /**
     * 打印error信息log
     * */
    public void error(Object object){
        if(object == null){
            Log.e("console", "null");
            return;
        }
        Log.e("console", object.toString());
    }

    /**
     * 打印debug信息log
     * */
    public void debug(Object object){
        if(object == null){
            Log.d("console", "null");
            return;
        }
        Log.d("console", object.toString());
    }
}
