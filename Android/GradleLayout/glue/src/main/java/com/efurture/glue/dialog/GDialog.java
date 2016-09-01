package com.efurture.glue.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by furture on 16/6/21.
 */
public class GDialog extends Dialog {


    public GDialog(Context context) {
        super(context);
    }

    protected GDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public GDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


}
