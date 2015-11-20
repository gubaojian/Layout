package com.efurture.marlin.component;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.efurture.marlin.ui.HybridView;
import com.efurture.marlin.view.element.XmlView;

import org.xml.sax.Attributes;

/**
 * Created by jianbai.gbj on 15/11/20.
 */
public class GHorizontalScrollView extends XmlView<HorizontalScrollView> {

    public GHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GHorizontalScrollView(Context context) {
        super(context);
    }

    @Override
    protected HorizontalScrollView createView() {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getContext());
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setVerticalScrollBarEnabled(false);
        return horizontalScrollView;
    }

    @Override
    public void initViewAtts(Attributes attrs) {
        super.initViewAtts(attrs);
        String xmlUrl = attrs.getValue("xmlUrl");
        if (xmlUrl != null){
            getHybridView().load(Uri.parse(xmlUrl));
        }
    }

    private HybridView hybridView;

    public HybridView getHybridView() {
        if (hybridView == null){
            hybridView = new HybridView(getContext());
            view.addView(hybridView);
        }
        return hybridView;
    }
}
