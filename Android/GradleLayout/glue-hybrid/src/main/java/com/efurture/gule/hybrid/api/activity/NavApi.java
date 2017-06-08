package com.efurture.gule.hybrid.api.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.efurture.glue.GLog;
import com.efurture.gule.hybrid.HybridManager;
import com.efurture.gule.hybrid.HybridActivity;
import com.efurture.gule.hybrid.HybridView;
import com.furture.react.JSRef;

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
            HybridManager hybridManager = ((HybridActivity) activity).getHybridManager();
            HybridView hybridView = hybridManager.getHybridView();
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


    /**
     * 打开第三方APP
     * */
    public void openApp(String url){
        openApp(url, null);
    }

    /**
     * 打开第三方APP
     * */
    public void openApp(String url, JSRef failedCallback){
        try{
            Intent intent = new Intent();
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }catch (Exception e){
            if(failedCallback != null) {
                failedCallback.call("error", e.getMessage());
            }
        }
    }



    /**
     * 打开系统默认的浏览器
     * */
    public void openBrowser(String url){
        openBrowser(url, null);
    }

    /**
     * 打开系统默认的浏览器
     * */
    public void openBrowser(String url, JSRef failedCallback){
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }catch (Exception e){
            if(failedCallback != null){
                failedCallback.call("error", e.getMessage());
            }
        }
    }




}
