package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.LangUtils;

import org.xml.sax.Attributes;

import java.util.Calendar;

/**
 * Created by furture on 16/6/21.
 */
public class GNumberPicker extends NumberPicker {

    public GNumberPicker(Context context) {
        super(context);
    }

    public GNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initDefaultStyle(){
        setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setWrapSelectorWheel(false);
    }


    public void initViewAtts(Attributes attrs, ViewInflater inflater) {

        String maxValue = attrs.getValue("maxValue");
        if(maxValue != null){
             setMaxValue(LangUtils.toInt(maxValue));
        }
        String minValue = attrs.getValue("minValue");
        if(minValue != null){
             setMinValue(LangUtils.toInt(minValue));
        }
        String value  = attrs.getValue("value");
        if(value != null) {
            setValue(LangUtils.toInt(value));
        }


    }
}
