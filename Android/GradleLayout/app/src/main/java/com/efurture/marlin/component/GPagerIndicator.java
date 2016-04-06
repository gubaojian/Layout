package com.efurture.marlin.component;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;

import com.efurture.marlin.component.viewpager.CircleIndicator;
import com.efurture.marlin.view.element.XmlView;
import com.efurture.marlin.view.util.ScreenUnit;

import org.xml.sax.Attributes;

/**
 * Created by jianbai.gbj on 15/11/23.
 */
public class GPagerIndicator extends XmlView<CircleIndicator>{


    public GPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GPagerIndicator(Context context) {
        super(context);
    }

    public GPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected CircleIndicator createView() {
        CircleIndicator circleIndicator = new CircleIndicator(getContext());
        return circleIndicator;
    }

    @Override
    public void initViewAtts(Attributes attrs) {
        super.initViewAtts(attrs);

        String circleGravity = attrs.getValue("circleGravity");
        if (circleGravity != null){
            if ("right".equals(circleGravity)){
                view.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            }else if("left".equals(circleGravity)){
                view.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
            }
        }


        String size = attrs.getValue("circleSize");
        if (size != null){
            view.setSize(ScreenUnit.toUnit(size));
        }
        String selectSize = attrs.getValue("circleSelectSize");
        if (selectSize  != null){
            view.setSelectSize(ScreenUnit.toUnit(selectSize));
        }

        String color = attrs.getValue("circleColor");
        if (color != null){
            view.setColor(Color.parseColor(color));
        }
        String selectColor = attrs.getValue("circleSelectColor");
        if (selectColor != null){
            view.setSelectColor(Color.parseColor(selectColor));
        }

        String circleMargin= attrs.getValue("circleMargin");
        if (circleMargin != null){
            view.setMargin(ScreenUnit.toUnit(circleMargin));
        }

        final String viewPagerTag =  attrs.getValue("viewPagerTag");
        if (viewPagerTag != null){
            post(new Runnable() {
                @Override
                public void run() {
                    GViewPager viewPager = (GViewPager) view.getRootView().findViewWithTag(viewPagerTag);
                    view.setViewPager(viewPager.getView());
                }
            });
        }
    }
}
