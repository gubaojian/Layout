package com.efurture.glue.bind;

import android.view.View;
import android.view.ViewGroup;

import com.efurture.glue.view.GImageView;
import com.efurture.glue.view.GTextView;

/**
 * Created by jianbai.gbj on 15/11/2.
 */
public class DataBinder {


    public static void doBind(View view, Object data, BinderCallback callback){
         if(callback != null) {
              callback.doBind(view, data);
         }
         if(view instanceof ViewGroup){
             for(int i=0; i<((ViewGroup) view).getChildCount(); i++){
                 doBind(((ViewGroup) view).getChildAt(i), data, callback);
             }
         }
    }


}
