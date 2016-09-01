package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.ViewUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/17.
 */
public class GLinearLayout extends LinearLayout {

    public GLinearLayout(Context context) {
        super(context);
    }

    public GLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initViewAtts(Attributes attrs, ViewInflater inflater) {
         String orientation = attrs.getValue("orientation");
         if("vertical".equals(orientation)){
             setOrientation(LinearLayout.VERTICAL);
         }else{
             setOrientation(LinearLayout.HORIZONTAL);
         }

         String gravity = attrs.getValue("gravity");
        if(gravity != null){
             setGravity(ViewUtils.getGravity(gravity));
        }
    }
}
