package com.efurture.glue.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.ViewUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/9/18.
 */
public class GRadioGroup extends RadioGroup {

    public GRadioGroup(Context context) {
        super(context);
    }

    public GRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void initViewAtts(Attributes attrs, ViewInflater inflater) {
        String orientation = attrs.getValue("orientation");
        if("vertical".equals(orientation)){
            setOrientation(LinearLayout.VERTICAL);
        }else{
            setOrientation(LinearLayout.HORIZONTAL);
        }

        String gravity = attrs.getValue("layoutGravity");
        if(gravity != null){
            setGravity(ViewUtils.getGravity(gravity));
        }
    }
}
