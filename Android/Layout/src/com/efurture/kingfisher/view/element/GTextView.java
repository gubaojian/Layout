package com.efurture.kingfisher.view.element;

import org.xml.sax.Attributes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.efurture.kingfisher.view.el.ElUtil;
import com.efurture.kingfisher.view.util.ScreenUnit;

public class GTextView  extends GView<TextView>{
	
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
	protected TextView createView() {
		TextView textView =  new TextView(getContext());
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setTextColor(Color.BLACK);
		return textView;
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
			view.setTextSize(ScreenUnit.toTextSize(textSize));
		}
		
		String numberOfLines = attrs.getValue("numberOfLines");
		if (numberOfLines != null) {
			view.setMaxLines(Integer.parseInt(numberOfLines));
		}
		
		String alignment = attrs.getValue("textAlignment");
		if (alignment != null) {
			int gravity = Gravity.LEFT;
			 if ("center".equals(alignment)) {
				gravity = Gravity.CENTER_HORIZONTAL;
			}else if ("right".equals(alignment)) {
				gravity = Gravity.RIGHT;
			}
			view.setGravity(Gravity.CENTER_VERTICAL|gravity);
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

	@Override
	public void bindData(Object data) {
		if (ElUtil.isEl(textAttr)) {
			Object backgroundValue = ElUtil.getElValue(data, textAttr);
			if(backgroundValue != null){
				setBackgroundValue(backgroundValue.toString());
			}else {
				setBackgroundDrawable(null);
			}
		}
		super.bindData(data);
	}
	
	
	
	

}
