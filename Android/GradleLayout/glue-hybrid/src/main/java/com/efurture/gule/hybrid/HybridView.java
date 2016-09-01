package com.efurture.gule.hybrid;

import android.content.Context;
import android.util.AttributeSet;

import com.efurture.glue.ui.XmlView;
import com.efurture.glue.ui.XmlViewLoadListener;
import com.furture.react.JSRef;

/**
 * Created by furture on 16/6/8.
 */
public class HybridView extends XmlView {

    public HybridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HybridView(Context context) {
        super(context);
    }

    public HybridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public  void setOnLoad(final JSRef jsRef){
        setXmlViewLoadListener(new XmlViewLoadListener() {
            @Override
            public void onLoadFailed() {
                jsRef.getEngine().call(jsRef, "onload", false);
            }

            @Override
            public void onLoadSuccess() {
                jsRef.getEngine().call(jsRef, "onload", true);
            }
        });
    }
}
