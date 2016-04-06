package com.efurture.marlin.component.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.efurture.marlin.view.util.ScreenUnit;

public class CircleIndicator extends LinearLayout implements ViewPager.OnPageChangeListener{


    private int color = 0x8FFFFFFF;
    private int selectColor = Color.WHITE;

    private int size = 10;
    private int selectSize = 16;
    private int margin = size;

    private ViewPager mViewpager;


    public CircleIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        size = ScreenUnit.toUnit("4");
        selectSize = ScreenUnit.toUnit("6");
        margin = selectSize/2;
    }


    public void setViewPager(ViewPager viewPager) {
        mViewpager = viewPager;
        mViewpager.setOnPageChangeListener(this);
        onPageSelected(mViewpager.getCurrentItem());
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int position) {
        if (mViewpager.getAdapter() == null || mViewpager.getAdapter().getCount() <= 0) {
            return;
        }
        int count = mViewpager.getAdapter().getCount();
        if (count != getChildCount()){
            createIndicatorsCount(count);
        }
        for(int i=0; i<count; i++){
            View view = getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            ShapeDrawable shapeDrawable = (ShapeDrawable) view.getBackground();
            int size = this.size;
            if (i == position){
                size = selectSize;
                shapeDrawable.getPaint().setColor(selectColor);
            }else{
                shapeDrawable.getPaint().setColor(color);
            }
            if (params.height != size){
                params.height = size;
                params.width = size;
                view.setLayoutParams(params);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private void createIndicatorsCount(int count) {
        removeAllViews();
        if (count <= 0) {
            return;
        }
        for(int i=0; i<count; i++){
            View indicator = new View(getContext());
            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
            shapeDrawable.getPaint().setColor(color);
            indicator.setBackgroundDrawable(shapeDrawable);
            addView(indicator);
            LayoutParams lp = (LayoutParams) indicator.getLayoutParams();
            lp.height = size;
            lp.width = size;
            lp.leftMargin = margin;
            lp.rightMargin = margin;
            indicator.setLayoutParams(lp);
        }
    }


    public void setSize(int size) {
        this.size = size;
    }

    public void setSelectSize(int selectSize) {
        this.selectSize = selectSize;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }
}
