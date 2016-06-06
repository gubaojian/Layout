package com.efurture.glue.component;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.efurture.glue.component.viewpager.LoopViewPager;
import com.efurture.glue.ui.HybridView;
import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.UriUtils;

import org.xml.sax.Attributes;

import java.util.List;

/**
 * Created by jianbai.gbj on 15/11/20.
 */
public class GViewPager extends  LoopViewPager {


    public GViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GViewPager(Context context) {
        super(context);
    }

    public void initViewAtts(Attributes attrs, ViewInflater inflater) {
        String xmlUrls = attrs.getValue("xmlUrls");
        if (xmlUrls != null){
            final List<Uri> pages = UriUtils.toUris(xmlUrls, inflater.getUri());
            setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return pages.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    HybridView hybridView = new HybridView(getContext());
                    hybridView.load(pages.get(position));
                    container.addView(hybridView);
                    return hybridView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    if (object instanceof  HybridView){
                        container.removeView((HybridView)object);
                    }
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            });
        }
    }

}
