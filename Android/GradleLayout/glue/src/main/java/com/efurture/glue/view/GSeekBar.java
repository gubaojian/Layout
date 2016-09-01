package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import java.util.Calendar;

/**
 * Created by furture on 16/6/21.
 * 暂时不对外公开
 */
public class GSeekBar extends SeekBar {

    public GSeekBar(Context context) {
        super(context);
        initDefaultStyle();
    }

    public GSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultStyle();
    }

    public GSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultStyle();
    }

    private void initDefaultStyle(){
        setThumbOffset(0);
    }
}
