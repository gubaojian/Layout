package com.efurture.gule.hybrid.api.local;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.efurture.glue.GLog;
import com.efurture.gule.hybrid.Hybrid;
import com.efurture.gule.hybrid.HybridActivity;
import com.efurture.gule.hybrid.HybridView;

/**
 * Created by furture on 16/6/6.
 */
public class NavApi {

    private Activity activity;

    public NavApi(Activity activity) {
        this.activity = activity;
    }


    public void toUrl(String url){
        toUrl(url, HybridActivity.class.getName());

    }

    public void toUrl(String url, String className){
         toUrl(url, className, false);
    }

    public void toUrl(String url, String className, boolean finish){
        if(activity instanceof HybridActivity){
            Hybrid hybrid = ((HybridActivity) activity).getHybrid();
            HybridView hybridView = hybrid.getHybridView();
            url = hybridView.getResourceLoader().toAbsPageUrl(url).toString();
        }
        try {
            Intent intent = new Intent(activity, activity.getClassLoader().loadClass(className));
            intent.setData(Uri.parse(url));
            activity.startActivity(intent);
            if(finish){
                activity.finish();
            }
        } catch (ClassNotFoundException e) {
            GLog.e("to url " + url + "error", e);
        }
    }



}
