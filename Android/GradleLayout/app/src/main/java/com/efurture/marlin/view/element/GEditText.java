package com.efurture.marlin.view.element;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import org.xml.sax.Attributes;

/**
 * Created by baobao on 15/9/23.
 */
public class GEditText extends  GTextView<EditText> {

    public GEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GEditText(Context context) {
        super(context);
    }

    @Override
    protected EditText createView() {
        EditText editText = new EditText(getContext());
        editText.setSingleLine(true);
        editText.setMaxLines(1);
        editText.setBackgroundDrawable(null);
        return  editText;
    }


    @Override
    public void initViewAtts(Attributes attrs) {
        super.initViewAtts(attrs);

        String inputType = attrs.getValue("inputType");
        if(inputType != null){
            if("password".equals(inputType)){
                view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else if("number".equals(inputType)){
                view.setInputType(InputType.TYPE_CLASS_NUMBER);
            }else if("numberPassword".equals(inputType)){
                view.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            }
        }
    }


}
