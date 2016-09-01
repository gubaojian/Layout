package com.efurture.gule.hybrid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * Created by furture on 16/6/6.
 */
public class HybridActivity extends AppCompatActivity {


    Hybrid hybrid;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HybridView hybridView = new HybridView(this);
        setContentView(hybridView);
        hybrid = new Hybrid(this);
        hybrid.setPageUrl(parsePageUrl());
        hybrid.setHybridView(hybridView);
        hybrid.init();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(hybrid != null){
            hybrid.onResume();
        }
    }




    @Override
    protected void onPause() {
        if(hybrid != null){
            hybrid.onPause();
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
        if(hybrid != null){
            hybrid.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        if(hybrid != null){
            hybrid.onDestroy();
        }
        super.onDestroy();
    }



    public final boolean importApi(String module){
          return hybrid.importApi(module);
    }



    public Hybrid getHybrid() {
        return hybrid;
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
