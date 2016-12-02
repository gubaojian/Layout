package com.efurture.gule.hybrid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.efurture.glue.utils.DevUtils;

/**
 * Created by furture on 16/6/6.
 */
public class HybridActivity extends AppCompatActivity {


    HybridManager hybridManager;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HybridView hybridView = new HybridView(this);
        setContentView(hybridView);
        hybridManager = new HybridManager(this);
        hybridManager.setPageUrl(parsePageUrl());
        hybridManager.setHybridView(hybridView);
        hybridManager.init();

    }



    @Override
    protected void onResume() {
        super.onResume();
        if(hybridManager != null){
            hybridManager.onResume();
        }
    }




    @Override
    protected void onPause() {
        if(hybridManager != null){
            hybridManager.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(hybridManager != null){
            hybridManager.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        if(hybridManager != null){
            hybridManager.onDestroy();
        }
        super.onDestroy();
    }



    public final boolean importApi(String module){
          return hybridManager.importApi(module);
    }



    public HybridManager getHybridManager() {
        return hybridManager;
    }

    protected String parsePageUrl() {
        Uri uri = getIntent().getData();
        if(uri != null){
            if(uri.getPath().endsWith(".js")){
                return  uri.toString();
            }
            String url = uri.getQueryParameter("url");
            if(url != null && url.endsWith(".js")){
                return  url;
            }
        }
        if(getIntent().getExtras() != null){
            String url = getIntent().getExtras().getString("url");
            if (!TextUtils.isEmpty(url)) {
                return url;
            }
        }
        return  null;
    }


}
