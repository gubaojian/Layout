package com.efurture.glue.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;

import com.efurture.glue.component.viewpager.CircleIndicator;
import com.efurture.glue.engine.ViewInflater;

import org.xml.sax.Attributes;

/**
 * Created by jianbai.gbj on 15/11/23.
 */
public class GPagerIndicator extends CircleIndicator{



    public GPagerIndicator(Context context) {
        super(context);
    }

    public GPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void initViewAtts(Attributes attrs, ViewInflater inflater) {

        String circleGravity = attrs.getValue("circleGravity");
        if (circleGravity != null){
            if ("right".equals(circleGravity)){
               this.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            }else if("left".equals(circleGravity)){
                this.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            }
        }


        String size = attrs.getValue("circleSize");
        if (size != null){
            this.setSize(inflater.toUnit(size));
        }
        String selectSize = attrs.getValue("circleSelectSize");
        if (selectSize  != null){
            this.setSelectSize(inflater.toUnit(selectSize));
        }

        String color = attrs.getValue("circleColor");
        if (color != null){
            this.setColor(Color.parseColor(color));
        }
        String selectColor = attrs.getValue("circleSelectColor");
        if (selectColor != null){
            this.setSelectColor(Color.parseColor(selectColor));
        }

        String circleMargin= attrs.getValue("circleMargin");
        if (circleMargin != null){
            this.setMargin(inflater.toUnit(circleMargin));
        }

        final String viewPagerTag =  attrs.getValue("viewPagerTag");
        if (viewPagerTag != null){
            post(new Runnable() {
                @Override
                public void run() {
                    GViewPager viewPager = (GViewPager) GPagerIndicator.this.getRootView().findViewWithTag(viewPagerTag);
                    setViewPager(viewPager);
                }
            });
        }
    }
}
