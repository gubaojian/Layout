package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by furture on 16/6/2.
 */
public class GListView extends ListView{

    public GListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultStyle();
    }

    public GListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultStyle();
    }

    public GListView(Context context) {
        super(context);
        initDefaultStyle();
    }


    private void initDefaultStyle(){
        this.setHorizontalScrollBarEnabled(false);
        setDivider(null);
        setDividerHeight(0);
        setSelector(android.R.color.transparent);

    }
}
