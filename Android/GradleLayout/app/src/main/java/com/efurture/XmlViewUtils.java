package com.efurture;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.efurture.marlin.ui.HybridView;

/**
 * Created by jianbai.gbj on 15/11/2.
 */
public class XmlViewUtils {



    public static Uri xmlUri(String name) {
        return Uri.parse(String.format("file:///android_asset/%s.xml", name));

       // return  Uri.parse(String.format("http://10.15.21.217:8080/%s.xml", name));

    }


    public static void  devTool(HybridView hybridView, final Activity activity) {
        hybridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, activity.getClass());
                intent.setData(activity.getIntent().getData());
                intent.putExtras(activity.getIntent().getExtras());
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }
}
