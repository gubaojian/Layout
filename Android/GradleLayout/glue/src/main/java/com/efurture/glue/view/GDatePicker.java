package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;

import com.efurture.glue.engine.ViewInflater;

import org.xml.sax.Attributes;

import java.util.Calendar;

/**
 * Created by furture on 16/6/21.
 */
public class GDatePicker extends DatePicker  implements DatePicker.OnDateChangedListener {


    public GDatePicker(Context context) {
        super(context);
        initDefaultStyle();
    }

    public GDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultStyle();
    }

    public GDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultStyle();
    }


    private void initDefaultStyle(){
         setSpinnersShown(true);
         setCalendarViewShown(false);
         Calendar calendar = Calendar.getInstance();
         init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
    }


    public void setOnDateChangedListener(OnDateChangedListener onDateChangedListener){
         this.onDateChangedListener = onDateChangedListener;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if(onDateChangedListener != null){
            onDateChangedListener.onDateChanged(view, year, monthOfYear, dayOfMonth);
        }
    }

    private OnDateChangedListener onDateChangedListener;



    public void initViewAtts(Attributes attrs, ViewInflater inflater) {

        String year = attrs.getValue("year");
        if(year != null){
             setYear(year);
        }

        String month = attrs.getValue("month");
        if(month != null){
            setMonth(month);
        }

        String day = attrs.getValue("day");
        if(day != null){
            setDay(day);
        }
    }


    public void setMonth(String month){
        updateDate(getYear(), Integer.parseInt(month), getDayOfMonth());
    }


    public void setYear(String year){
        updateDate(Integer.parseInt(year), getMonth(), getDayOfMonth());
    }

    public void setDay(String day){
        updateDate(getYear(), getMonth(), Integer.parseInt(day));
    }
}
