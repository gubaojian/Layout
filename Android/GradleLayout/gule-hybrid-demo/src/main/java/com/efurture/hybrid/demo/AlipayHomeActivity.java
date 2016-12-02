package com.efurture.hybrid.demo;

import android.os.Bundle;

import com.efurture.glue.utils.DevUtils;
import com.efurture.gule.hybrid.HybridActivity;

public class AlipayHomeActivity extends HybridActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DevUtils.initDevTool(getHybridManager().getHybridView(), this);
    }
}
