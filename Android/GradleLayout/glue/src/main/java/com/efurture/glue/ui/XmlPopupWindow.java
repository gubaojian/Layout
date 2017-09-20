package com.efurture.glue.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.PopupWindow;

import com.efurture.glue.utils.ViewUtils;

/**
 * Created by furture on 16/6/13.
 */
public class XmlPopupWindow extends PopupWindow {




    public XmlPopupWindow(Context context, String xml){
        super(context);
        View view =  ViewUtils.inflate(context, xml, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        setContentView(view);
        setWidth(view.getMeasuredWidth());
        setHeight(view.getMeasuredHeight());
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new BitmapDrawable());
    }
}
