package com.efurture.glue.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.LangUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/3.
 */
public class GRecyclerView extends RecyclerView{

    public GRecyclerView(Context context) {
        super(context);
        initDefault();
    }

    public GRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDefault();
    }

    public GRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initDefault();
    }

    private void initDefault(){

    }


    public void initViewAtts(Attributes attrs, final ViewInflater inflater) {
            int orientation = LinearLayoutManager.VERTICAL;
            if("horizontal".equals(attrs.getValue("orientation"))){
                orientation = LinearLayoutManager.HORIZONTAL;
            }
            String layout = attrs.getValue("layout");
            if("grid".equals(layout)){
                int spanCount = LangUtils.toInt(attrs.getValue("columns"), 2);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount, orientation, false);
                setLayoutManager(gridLayoutManager);
            }else if ("stagGrid".equals(layout)){
                int spanCount = LangUtils.toInt(attrs.getValue("columns"), 2);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, orientation);
                setLayoutManager(staggeredGridLayoutManager);
            }else {
                setLayoutManager(new LinearLayoutManager(getContext(), orientation, false));
            }
    }



}
