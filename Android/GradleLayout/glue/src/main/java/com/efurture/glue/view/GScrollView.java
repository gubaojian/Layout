package com.efurture.glue.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.ui.XmlView;
import com.efurture.glue.utils.LangUtils;
import com.efurture.glue.utils.ViewUtils;

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
                    getHybridView().loadUrl(xmlUrl);
                }
            });
        }

        String fillViewport = attrs.getValue("fillViewport");
        if(!TextUtils.isEmpty(fillViewport)){
            setFillViewport(LangUtils.isTrue(fillViewport));
        }
    }

    private XmlView hybridView;

    public XmlView getHybridView() {
        if (hybridView == null){
            hybridView = ViewUtils.newHybridView(this);
            addView(hybridView);
        }
        return hybridView;
    }




}
