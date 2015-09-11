package com.efurture.kingfisher.view.element;

import org.xml.sax.Attributes;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.efurture.kingfisher.view.el.ElUtil;
import com.squareup.picasso.Picasso;

public class GImageView extends GView<ImageView>{

	
	private String imageUrlAttr;
	
	private String placeholderAttr;
	
	
	
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
		 imageUrlAttr = attrs.getValue("imageUrl");
		 placeholderAttr = attrs.getValue("highlightedImageUrl");
		 if (imageUrlAttr != null && !ElUtil.isEl(imageUrlAttr)) {
		    setImageUrl(imageUrlAttr);
		 }
		 
		 String scaleType = attrs.getValue("scaleType");
		 if (scaleType != null) {
			 ScaleType type = ScaleType.FIT_XY;
			 if ("center".equals(scaleType)) {
				 type = ScaleType.CENTER;
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
	
	
	public void setPlaceholder(String imageUrl){
		this.placeholderAttr = imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		
			 int resId = 0;
			 if(!TextUtils.isEmpty(placeholderAttr)){
				 resId = getContext().getResources().getIdentifier(placeholderAttr, "drawable", getContext().getPackageName());	
			 }
			 if(resId > 0){
			     Picasso.with(getContext()).load(Uri.parse(imageUrl)).placeholder(resId).into(view);
			 }else {
				 Picasso.with(getContext()).load(Uri.parse(imageUrl)).into(view);
			 }
	}

	
	@Override
	public void bindData(Object data) {
		if (ElUtil.isEl(imageUrlAttr)) {
			Object imageUrlValue = ElUtil.getElValue(data, imageUrlAttr);
			if(imageUrlValue != null){
				setImageUrl(imageUrlValue.toString());
			}else {
				view.setImageDrawable(null);
			}
		}
		super.bindData(data);
	}
	
	
}
