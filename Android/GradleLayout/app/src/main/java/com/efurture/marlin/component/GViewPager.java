package com.efurture.marlin.component;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.efurture.marlin.component.viewpager.LoopViewPager;
import com.efurture.marlin.ui.HybridView;
import com.efurture.marlin.view.element.XmlView;

import org.xml.sax.Attributes;

/**
 * Created by jianbai.gbj on 15/11/20.
 */
public class GViewPager extends XmlView<ViewPager> {

    private LoopViewPager loopViewPager;

    public GViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GViewPager(Context context) {
        super(context);
    }

    @Override
    protected ViewPager createView() {
        loopViewPager = new LoopViewPager(getContext());
        return loopViewPager;
    }


    @Override
    public void initViewAtts(Attributes attrs) {
        super.initViewAtts(attrs);
        String xmlUrls = attrs.getValue("xmlUrls");
        if (xmlUrls != null){
            final String[] pages = xmlUrls.split(";");
            loopViewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return pages.length;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    HybridView hybridView = new HybridView(getContext());
                    hybridView.load(Uri.parse(pages[position]));
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
