package com.efurture.hybrid.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.efurture.glue.engine.ViewFactory;
import com.efurture.gule.hybrid.HybridActivity;
import com.efurture.glue.ui.XmlPopupWindow;

public class MainActivity extends AppCompatActivity {



    String domain  = "http://10.15.28.247:8888/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewFactory.shareFactory().add("RecycleView", GStickyRecycleView.class);



        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlipayHomeActivity.class);
                intent.putExtra("url", domain + "build/hello.js");
                startActivity(intent);
            }
        });


        findViewById(R.id.list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HybridActivity.class);
                intent.putExtra("url", domain + "build/list.js");
                startActivity(intent);
            }
        });


        findViewById(R.id.alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlipayHomeActivity.class);
                intent.putExtra("url", domain + "build/alipay.js");
                startActivity(intent);
            }
        });

        findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HybridActivity.class);
                intent.putExtra("url", domain + "build/index.js");
                startActivity(intent);
            }
        });

        XmlPopupWindow.class.getName();
    }
}
