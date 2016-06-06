package com.efurture.glue.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.efurture.glue.ui.HybridView;
import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.UriUtils;

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
