package com.efurture.glue.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RadioButton;

import com.efurture.glue.bind.ElUtil;
import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.utils.StateUtils;
import com.efurture.glue.utils.LangUtils;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/21.
 */
public class GRadioButton extends RadioButton {


    public GRadioButton(Context context) {
        super(context);
    }

    public GRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void initViewAtts(Attributes attrs, ViewInflater inflater) {
        String checked = attrs.getValue("checked");
        if(checked != null){
            setChecked(LangUtils.isTrue(checked));
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
            String selectTextColor = attrs.getValue("selectTextColor");
            if(selectTextColor == null){
                setTextColor(Color.parseColor(textColor));
            }else{
                setTextColor(StateUtils.getColorStateList(textColor, selectTextColor));
            }
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
            int verticalGravity =  Gravity.CENTER_VERTICAL;

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
