package com.efurture.marlin.view.element;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.efurture.marlin.el.ElUtil;
import com.efurture.marlin.view.util.ScreenUnit;

import org.xml.sax.Attributes;

@SuppressLint("RtlHardcoded")
public class GTextView<T extends TextView>  extends XmlView<T>{
	
	private String textAttr;
	
	private String hintAttr;
	
	public GTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GTextView(Context context) {
		super(context);
	}

	@Override
	protected T createView() {
		TextView textView =  new TextView(getContext());
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setTextColor(Color.BLACK);
		textView.setSingleLine(true);
		textView.setMaxLines(1);
		return (T)textView;
	}

	@Override
	public void initViewAtts(Attributes attrs) {
		super.initViewAtts(attrs);
		textAttr = attrs.getValue("text");
		if (textAttr != null && !ElUtil.isEl(textAttr)) {
			view.setText(textAttr);
		}
		
		hintAttr = attrs.getValue("hint");
		if(hintAttr != null){
			view.setHint(hintAttr);
		}
		
		String textColor = attrs.getValue("textColor");
		if(textColor != null){
			view.setTextColor(Color.parseColor(textColor));
		}
		
		String highlightedTextColor = attrs.getValue("highlightedTextColor");
		if (highlightedTextColor != null) {
			view.setHighlightColor(Color.parseColor(highlightedTextColor));
		}
		
		String fontStyle = attrs.getValue("fontStyle");
		String fontName = attrs.getValue("font");
		if (fontStyle != null 
				|| fontName != null) {
			Typeface typeface = view.getTypeface();
			if (fontName != null) {
				typeface = Typeface.create(fontName, Typeface.NORMAL);
			}
			int style = Typeface.NORMAL;
			if ("bold".equals(fontStyle)) {
				style = Typeface.BOLD;
			}else if ("italic".equals(fontStyle)) {
				style = Typeface.ITALIC;
			}
			view.setTypeface(typeface, style);
		}

		String textSize = attrs.getValue("textSize");
		if (textSize != null) {
			view.setTextSize(TypedValue.COMPLEX_UNIT_PX, ScreenUnit.toTextSize(textSize));
		}
		
		String numberOfLines = attrs.getValue("numberOfLines");
		if (numberOfLines != null) {
			view.setSingleLine(false);
			view.setMaxLines(Integer.parseInt(numberOfLines));
		}
		
		String alignment = attrs.getValue("textAlignment");
		if (alignment != null) {
			String verticalAlignment =  attrs.getValue("verticalAlignment");
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
			view.setGravity(gravity);
		}

		String lineBreakMode = attrs.getValue("lineBreakMode");
		if (lineBreakMode != null) {
			TextUtils.TruncateAt where = TextUtils.TruncateAt.END;
			if ("head".equals(lineBreakMode)) {
				 where = TextUtils.TruncateAt.START;
			}else if ("tail".equals(lineBreakMode)) {
				 where = TextUtils.TruncateAt.END;
			}else if ("middle".equals(lineBreakMode)) {
				 where = TextUtils.TruncateAt.MIDDLE;
			}
			view.setEllipsize(where);
		}


	}


	
	
	
	

}
