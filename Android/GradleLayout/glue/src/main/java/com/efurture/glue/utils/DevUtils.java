package com.efurture.glue.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.efurture.glue.ui.XmlView;

/**
 * Created by furture on 16/10/10.
 */
public class DevUtils {

    public static void  devTool(XmlView hybridView, final Activity activity) {
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
