package com.efurture.glue.engine;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.efurture.glue.view.GImageView;
import com.efurture.glue.view.GTextView;
import com.squareup.picasso.Picasso;

/**
 * Created by jianbai.gbj on 15/11/2.
 */
public class DataBinder {


    public static void doBind(View view, Object data, BinderCallback callback){

    }

    public abstract class BinderCallback{

        public void doBind(View view, Object value){

        }


        /**
        public void onBindData(View view, Object desc){
             if(view instanceof GTextView){
                 if(desc == null){
                     ((TextView) ((GTextView) view)).setText("");
                 }else{
                     ((TextView) ((GTextView) view)).setText(desc.toString());
                 }
             } else if(view instanceof GImageView){
                 if(desc == null){
                     ((ImageView) ((GImageView) view)).setImageDrawable(null);
                 }else{
                     Picasso.with(view.getContext()).load(Uri.parse(desc.toString())).into((ImageView) ((GImageView) view));
                 }
            }else if (view instanceof TextView){
                 if(desc == null){
                     ((TextView) view).setText("");
                 }else{
                     ((TextView) view).setText(desc.toString());
                 }
             }else  if (view instanceof ImageView){
                if(desc == null){
                     ((ImageView) view).setImageDrawable(null);
                }else{
                    Picasso.with(view.getContext()).load(Uri.parse(desc.toString())).into((ImageView) view);
                }
            }
        }*/
    }
}
