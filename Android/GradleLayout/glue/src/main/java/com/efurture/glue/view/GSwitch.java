package com.efurture.glue.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Switch;

import com.efurture.glue.bind.ElUtil;
import com.efurture.glue.engine.ViewInflater;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/21.
 */
public class GSwitch extends Switch {

    public GSwitch(Context context) {
        super(context);
    }

    public GSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    /**
     * 默认实现
     * */
    public void initViewAtts(Attributes attrs, ViewInflater inflater) {
         String textOn = attrs.getValue("textOn");

        if(textOn != null){
            setTextOn(textOn);
        }

        String textOff = attrs.getValue("textOff");
        if(textOff != null){
            setTextOff(textOff);
        }

       String textAttr = attrs.getValue("text");
        if (textAttr != null && !ElUtil.isEl(textAttr)) {
            setText(textAttr);
        }

        String hintAttr = attrs.getValue("hint");
        if(hintAttr != null){
            setHint(hintAttr);
        }

        String hintColor = attrs.getValue("hintColor");
        if(hintColor != null){
            setHintTextColor(Color.parseColor(hintColor));
        }

        String textColor = attrs.getValue("textColor");
        if(textColor != null){
            setTextColor(Color.parseColor(textColor));
        }

        String highlightedTextColor = attrs.getValue("highlightColor");
        if (highlightedTextColor != null) {
            setHighlightColor(Color.parseColor(highlightedTextColor));
        }

        String fontStyle = attrs.getValue("fontStyle");
        String fontName = attrs.getValue("font");
        if (fontStyle != null
                || fontName != null) {
            Typeface typeface = getTypeface();
            if (fontName != null) {
                typeface = Typeface.create(fontName, Typeface.NORMAL);
            }
            int style = Typeface.NORMAL;
            if ("bold".equals(fontStyle)) {
                style = Typeface.BOLD;
            }else if ("italic".equals(fontStyle)) {
                style = Typeface.ITALIC;
            }
            setTypeface(typeface, style);
        }

        String textSize = attrs.getValue("textSize");
        if (textSize != null) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, inflater.toUnit(textSize));
        }

        String numberOfLines = attrs.getValue("maxLines");
        if (numberOfLines != null) {
            setSingleLine(false);
            setMaxLines(Integer.parseInt(numberOfLines));
        }

        String alignment = attrs.getValue("textAlign");
        if (alignment != null) {
            String verticalAlignment =  attrs.getValue("verticalAlign");
            int verticalGravity =  Gravity.CENTER_VERTICAL;
            if (verticalAlignment != null){
                if("top".equals(verticalGravity)){
                    verticalGravity = Gravity.TOP;
                }else if ("bottom".equals(alignment)) {
                    verticalGravity = Gravity.BOTTOM;
                }
            }
            int gravity = Gravity.LEFT |verticalGravity;
            if ("center".equals(alignment)) {
                gravity = Gravity.CENTER_HORIZONTAL | verticalGravity;
            }else if ("right".equals(alignment)) {
                gravity = Gravity.RIGHT | verticalGravity;
            }
            setGravity(gravity);
        }

        String lineBreakMode = attrs.getValue("breakMode");
        if (lineBreakMode != null) {
            TextUtils.TruncateAt where = TextUtils.TruncateAt.END;
            if ("head".equals(lineBreakMode)) {
                where = TextUtils.TruncateAt.START;
            }else if ("tail".equals(lineBreakMode)) {
                where = TextUtils.TruncateAt.END;
            }else if ("middle".equals(lineBreakMode)) {
                where = TextUtils.TruncateAt.MIDDLE;
            }
            setEllipsize(where);
        }
    }

}
