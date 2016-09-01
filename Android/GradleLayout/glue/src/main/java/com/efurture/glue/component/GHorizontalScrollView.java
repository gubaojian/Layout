package com.efurture.glue.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.efurture.glue.ui.XmlView;
import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.ViewUtils;

import org.xml.sax.Attributes;

/**
 * Created by jianbai.gbj on 15/11/20.
 */
public class GHorizontalScrollView extends HorizontalScrollView {

    public GHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public GHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GHorizontalScrollView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
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
