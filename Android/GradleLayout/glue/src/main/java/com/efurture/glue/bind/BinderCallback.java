package com.efurture.glue.bind;

import android.view.View;

import com.efurture.glue.view.GImageView;
import com.efurture.glue.view.GTextView;

/**
 * Created by furture on 16/6/22.
 */
public class BinderCallback{

    public boolean doBind(View view, Object data){
        if(view instanceof GTextView){
            String el = ((GTextView) view).getTextAttr();
            ((GTextView) view).setText(ElUtil.getElStringValue(data, el));
            return  true;
        }

        if(view instanceof GImageView){
            String el = ((GImageView) view).getImageUrlAttr();
            Object value =  ElUtil.getElValue(data, el);
            if(el != null){
                ((GImageView) view).setImageUrl(value.toString());
            }
            return  true;
        }
        return  false;
    }
}