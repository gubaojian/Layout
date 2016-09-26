package com.efurture.glue.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.efurture.glue.bind.ElUtil;
import com.squareup.picasso.Picasso;

import org.xml.sax.Attributes;

public class GImageView extends ImageView{

	
	private String imageUrlAttr;
	
	private String placeHolder;

	
	public GImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initDefault();
	}

	public GImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initDefault();
	}

	public GImageView(Context context) {
		super(context);
		initDefault();
	}

	private void initDefault(){
           setScaleType(ImageView.ScaleType.CENTER_CROP);
	}


	public void initViewAtts(Attributes attrs) {
		 imageUrlAttr = attrs.getValue("imageUrl");
		 placeHolder = attrs.getValue("placeHolder");
		 if (imageUrlAttr != null && !ElUtil.isEl(imageUrlAttr)) {
		    setImageUrl(imageUrlAttr);
		 }
		 String scaleType = attrs.getValue("scaleType");
		 if (!TextUtils.isEmpty(scaleType)) {
			 ScaleType type = ScaleType.CENTER_CROP;
			 if ("center".equals(scaleType)) {
				 type = ScaleType.FIT_CENTER;
			 }else if ("centerCrop".equals(scaleType)) {
				 type = ScaleType.CENTER_CROP;
			 }else if ("fitXY".equals(scaleType)) {
				 type = ScaleType.FIT_XY;
			 }else if ("centerInside".equals(scaleType)) {
				 type = ScaleType.CENTER_INSIDE;
			 }else if ("start".equals(scaleType)) {
				 type = ScaleType.FIT_START;
			 }else if ("end".equals(scaleType)) {
				 type = ScaleType.FIT_END;
			 }
             setScaleType(type);
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
			Picasso.with(getContext()).load(Uri.parse(imageUrl)).placeholder(resId).into(this);
		} else {
			Picasso.with(getContext()).load(Uri.parse(imageUrl)).into(this);
		}
	}

	public String getImageUrlAttr() {
		return imageUrlAttr;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}



}
