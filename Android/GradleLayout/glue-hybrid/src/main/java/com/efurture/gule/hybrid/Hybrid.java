package com.efurture.gule.hybrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.efurture.glue.loader.ResourceLoader;
import com.efurture.glue.utils.ViewUtils;
import com.efurture.gule.hybrid.api.ApiLifecycle;
import com.efurture.gule.hybrid.api.ApiUtils;
import com.efurture.gule.hybrid.api.HybridApi;
import com.efurture.gule.hybrid.utils.AssetUtils;
import com.furture.react.DuktapeEngine;
import com.furture.react.JSApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by furture on 16/6/7.
 *
 * api默认的lifecycle
 */
public  class Hybrid extends ApiLifecycle {

    private static final String ACTIVITY_LISTENER = "activityListener";

    private Activity activity;

    private HybridView hybridView;

    private ResourceLoader resourceLoader;

    protected String pageUrl;

    private DuktapeEngine duktapeEngine;


    private HashMap<String, Object> initedLocalApi;



    public Hybrid(Activity activity) {
        this.activity = activity;
        this.initedLocalApi = new HashMap<>();
    }


    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void setHybridView(HybridView hybridView) {
        this.hybridView = hybridView;
    }

    public HybridView getHybridView() {
        return hybridView;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void init() {
        if(resourceLoader == null){
            resourceLoader = new ResourceLoader(activity.getApplicationContext());
        }
        resourceLoader.setPageUrl(pageUrl);
        duktapeEngine = new DuktapeEngine();
        duktapeEngine.put("activity", activity);
        duktapeEngine.put("hybrid",  this);
        hybridView.setResourceLoader(resourceLoader);
        duktapeEngine.put("hybridView",  hybridView);
        Context context = activity.getApplicationContext();
        String globalScript = AssetUtils.read(context, "hybrid.js");
        duktapeEngine.execute(globalScript);
        if(pageUrl != null){
            resourceLoader.loadUrl(pageUrl, new ResourceLoader.Callback() {
                @Override
                public void onStream(byte[] bts) {
                    if(bts != null){
                        String script = new String(bts);
                        if(duktapeEngine != null){
                            duktapeEngine.execute(script);
                            duktapeEngine.call(ACTIVITY_LISTENER, "onCreate");
                        }
                    }
                }
            });
        }


    }




    public final boolean importApi(String module){
        Object api = JSApi.getContext().get(module);
        if(api != null){
            return true;
        }

        Class<?> apiClass = HybridApi.getGlobalClassMap().get(module);
        if(apiClass != null){
            api = ApiUtils.newApi(apiClass, activity.getApplicationContext());
            if(api != null){
                JSApi.getContext().put(module, api);
                duktapeEngine.put(module, api);
                return true;
            }
        }

        api = initedLocalApi.get(module);
        if(api != null){
            if(api instanceof ApiLifecycle){
                ((ApiLifecycle) api).onDestroy();
            }
        }

        apiClass = HybridApi.getLocalClassMap().get(module);
        if(apiClass != null){
            api  =  ApiUtils.newApi(apiClass, activity);
            initedLocalApi.put(module, api);
            duktapeEngine.put(module, api);
        }
        return  api != null;
    }


    @Override
    public void onPause() {
        Set<Map.Entry<String,Object>> localApis = initedLocalApi.entrySet();
        for(Map.Entry<String,Object> entry : localApis){
            Object api = entry.getKey();
            if(api instanceof ApiLifecycle){
                ((ApiLifecycle) api).onPause();
            }
        }
        if(duktapeEngine != null){
            duktapeEngine.call(ACTIVITY_LISTENER, "onPause");
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        Set<Map.Entry<String,Object>> localApis = initedLocalApi.entrySet();
        for(Map.Entry<String,Object> entry : localApis){
            Object api = entry.getKey();
            if(api instanceof ApiLifecycle){
                ((ApiLifecycle) api).onResume();
            }
        }
        if(duktapeEngine != null){
            duktapeEngine.call(ACTIVITY_LISTENER, "onResume");
        }
        super.onResume();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Set<Map.Entry<String,Object>> localApis = initedLocalApi.entrySet();
        for(Map.Entry<String,Object> entry : localApis){
            Object api = entry.getKey();
            if(api instanceof ApiLifecycle){
                ((ApiLifecycle) api).onActivityResult(requestCode, resultCode, intent);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }




    @Override
    public void onDestroy() {
        Set<Map.Entry<String,Object>> localApis = initedLocalApi.entrySet();
        for(Map.Entry<String,Object> entry : localApis){
            Object api = entry.getKey();
            if(api instanceof ApiLifecycle){
                ((ApiLifecycle) api).onDestroy();
            }
        }
        if(duktapeEngine != null){
            duktapeEngine.destory();
        }
        super.onDestroy();
    }
}


