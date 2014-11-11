package com.efurture.kingfisher.view;

import org.xml.sax.Attributes;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.efurture.kingfisher.image.GImageFetcher;

public class GImageView extends GLayout<ImageView>{

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
		 String imageUrl = attrs.getValue("imageUrl");
		 String highlightedImageUrl = attrs.getValue("highlightedImageUrl");
		 if (imageUrl != null 
				 || highlightedImageUrl != null) {
			GImageFetcher.shareImageFetcher().load(view, imageUrl, highlightedImageUrl);
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
	
	

}
