package com.efurture.marlin.view.element;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.squareup.picasso.Picasso;

import org.xml.sax.Attributes;

public class GImageView extends XmlView<ImageView>{

	
	private String imageUrl;
	
	private String placeHolder;
	
	
	
	public GImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GImageView(Context context) {
		super(context);
	}

	@Override
	protected ImageView createView() {
		return new ImageView(getContext());
	}

	@Override
	public void initViewAtts(Attributes attrs) {
		 super.initViewAtts(attrs);
		 imageUrl = attrs.getValue("imageUrl");
		 placeHolder = attrs.getValue("placeHolder");
		 if (imageUrl != null) {
		    setImageUrl(imageUrl);
		 }
		 
		 String scaleType = attrs.getValue("scaleType");
		 if (scaleType != null) {
			 ScaleType type = ScaleType.FIT_XY;
			 if ("center".equals(scaleType)) {
				 type = ScaleType.FIT_CENTER;
			 }else if ("centerCrop".equals(scaleType)) {
				 type = ScaleType.CENTER_CROP;
			 }else if ("centerInside".equals(scaleType)) {
				 type = ScaleType.CENTER_INSIDE;
			 }else if ("left".equals(scaleType)) {
				 type = ScaleType.FIT_START;
			 }else if ("right".equals(scaleType)) {
				 type = ScaleType.FIT_END;
			 }
             view.setScaleType(type);
		 }else {
		     view.setScaleType(ScaleType.FIT_XY);	
		 }
	}


	public void setPlaceholder(String placeHolder){
		this.placeHolder = placeHolder;
	}
	
	public void setImageUrl(String imageUrl) {
		int resId = 0;
		if (!TextUtils.isEmpty(placeHolder)) {
			resId = getContext().getResources().getIdentifier(placeHolder, "drawable", getContext().getPackageName());
		}
		if (resId > 0) {
			Picasso.with(getContext()).load(Uri.parse(imageUrl)).placeholder(resId).into(view);
		} else {
			Picasso.with(getContext()).load(Uri.parse(imageUrl)).into(view);
		}
	}
	
}