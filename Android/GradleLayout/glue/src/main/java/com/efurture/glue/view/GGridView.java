package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.LangUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/8.
 */
public class GGridView extends GridView {


    public boolean scrollEnable = false;

    public GGridView(Context context) {
        super(context);
        initDefaultStyle();
    }

    public GGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultStyle();
    }

    public GGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultStyle();
    }

    private void initDefaultStyle(){
        setSelector(android.R.color.transparent);
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
    }


    public void initViewAtts(Attributes attrs, final ViewInflater inflater) {
        if(LangUtils.isTrue(attrs.getValue("scrollEnable"))){
            setVerticalScrollBarEnabled(true);
            scrollEnable = true;
        }

        String horizontalSpacing = attrs.getValue("hSpace");
        if(horizontalSpacing != null){
            setHorizontalSpacing(inflater.toUnit(horizontalSpacing));
        }

        String verticalSpacing = attrs.getValue("vSpace");
        if(verticalSpacing != null){
            setVerticalSpacing(inflater.toUnit(verticalSpacing));
        }

        String numColumns = attrs.getValue("columns");
        if(numColumns != null){
            setNumColumns(Integer.parseInt(numColumns));
        }else{
            setNumColumns(2);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(scrollEnable){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }else{
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
    }

}
