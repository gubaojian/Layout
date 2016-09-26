package com.efurture.glue.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.efurture.glue.utils.ViewUtils;

/**
 * Created by furture on 16/6/21.
 */
public class XmlDialog extends Dialog {


    public XmlDialog(Context context) {
        super(context);
    }

    protected XmlDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public XmlDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    public void setXml(String xml){
        View view =  ViewUtils.inflate(getContext(), xml, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        setContentView(view);
    }
}
