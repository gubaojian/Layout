package com.efurture.glue.component;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.efurture.glue.component.viewpager.LoopViewPager;
import com.efurture.glue.ui.XmlView;
import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.UriUtils;
import com.efurture.glue.utils.ViewUtils;
import com.efurture.glue.view.GImageView;

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
            final List<String> pages = UriUtils.toList(xmlUrls);
            setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return pages.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    XmlView hybridView = ViewUtils.newHybridView(GViewPager.this);
                    hybridView.loadUrl(pages.get(position));
                    container.addView(hybridView);
                    return hybridView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    if (object instanceof XmlView){
                        container.removeView((XmlView)object);
                    }
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }
            });
        }

        String imgUrls = attrs.getValue("imgUrls");
        if(imgUrls != null){
            final List<String> pages = UriUtils.toList(imgUrls);
            setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return pages.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    GImageView imageView = new GImageView(getContext());
                    imageView.setImageUrl(pages.get(position));
                    container.addView(imageView);
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    if (object instanceof XmlView){
                        container.removeView((XmlView)object);
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
