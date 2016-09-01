package com.efurture.glue.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TimePicker;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.LangUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/21.
 */
public class GTimerPicker extends TimePicker {

    public GTimerPicker(Context context) {
        super(context);
    }

    public GTimerPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GTimerPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initViewAtts(Attributes attrs, ViewInflater inflater) {

        String hour = attrs.getValue("hour");
        if(hour != null){
             setHour(LangUtils.toInt(hour));
        }

        String minute = attrs.getValue("minute");
        if(minute != null){
           setMinute(LangUtils.toInt(minute));
        }
    }

    @Override
    public void setHour(int hour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setHour(hour);
        }else {
             super.setCurrentHour(hour);
        }
    }

    @Override
    public int getHour() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           return super.getHour();
        }else {
            return  getCurrentHour();
        }
    }

    @Override
    public void setMinute(int minute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setMinute(minute);
        }else {
            super.setCurrentMinute(minute);
        }
    }


    @Override
    public int getMinute() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return super.getMinute();
        }else {
            return  super.getCurrentMinute();
        }
    }
}
