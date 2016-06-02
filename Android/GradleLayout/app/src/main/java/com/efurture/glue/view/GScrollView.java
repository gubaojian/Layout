package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.ui.HybridView;
import com.efurture.glue.utils.UriUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/2.
 */
public class GScrollView extends ScrollView {

    public GScrollView(Context context) {
        super(context);
    }

    public GScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initViewAtts(Attributes attrs, final ViewInflater inflater) {
        final String xmlUrl = attrs.getValue("xmlUrl");
        if (xmlUrl != null){
            post(new Runnable() {
                @Override
                public void run() {
                    getHybridView().load(UriUtils.toUri(xmlUrl, inflater.getUri()));
                }
            });
        }
    }

    private HybridView hybridView;

    public HybridView getHybridView() {
        if (hybridView == null){
            hybridView = new HybridView(getContext());
            addView(hybridView);
        }
        return hybridView;
    }




}
