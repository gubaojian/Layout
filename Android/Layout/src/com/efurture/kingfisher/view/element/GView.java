package com.efurture.kingfisher.view.element;

import java.util.Map;

import org.xml.sax.Attributes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

import com.efurture.kingfisher.view.el.ElUtil;
import com.efurture.kingfisher.view.math.EvaluatorManager;
import com.efurture.kingfisher.view.math.ParamMap;
import com.efurture.kingfisher.view.math.Params;
import com.efurture.kingfisher.view.util.ScreenUnit;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

public class GView<T extends View> extends FrameLayout {

	protected T view;
	
	
	private String x;
	private String y;
	private String width;
	private String height;
	private String expressionX;
	private String expressionY;
	private String expressionWidth;
	private String expressionHeight;
	private String onClickAttr;
	private String backgroundAttr;
	
	private Paint mPaint;
	private float mRadius;
	private RectF mRectF;
	private Path  mPath;
	public GView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public GView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GView(Context context) {
		super(context);
		init();
	}

	private void init() {
		view = createView();
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addView(view, params);
	}
	
	protected T createView(){
		return (T)new View(getContext());
	}
	
	public T getView(){
		return view;
	}
	
	public void initViewAtts(Attributes attrs){
		x = attrs.getValue("x");
		y = attrs.getValue("y");
		width = attrs.getValue("width");
		height = attrs.getValue("height");
		expressionX = attrs.getValue("expressionX");
		expressionY = attrs.getValue("expressionY");
		expressionWidth = attrs.getValue("expressionWidth");
		expressionHeight = attrs.getValue("expressionHeight");
		requestMathLayout();
		backgroundAttr =  attrs.getValue("background");
		if(backgroundAttr != null && !ElUtil.isEl(backgroundAttr)){
			setBackgroundValue(backgroundAttr);
		}
		
		String alpha = attrs.getValue("alpha");
		if (!TextUtils.isEmpty(alpha)) {
			view.setAlpha(Float.parseFloat(alpha));
		}
		
		String borderWidth = attrs.getValue("borderWidth");
		if (!TextUtils.isEmpty(borderWidth)) {
			int border =ScreenUnit.toUnit(borderWidth);
			setPadding(border, border, border, border);
		}
		
		String borderColor = attrs.getValue("borderColor");
		if (!TextUtils.isEmpty(borderColor)) {
			view.setBackgroundColor(Color.parseColor(borderColor));
		}
		
		String cornerRadius = attrs.getValue("cornerRadius");
		if (!TextUtils.isEmpty(cornerRadius)) {
			 mRadius = ScreenUnit.toUnit(cornerRadius);
			 mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			 mPaint.setAntiAlias(true);
			 mPaint.setColor(Color.TRANSPARENT);
			 mPaint.setAlpha(0x00);
			 mPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			 mRectF = new RectF();
			 mPath = new Path();
		}
		
	}
	

	@Override
	public void setLayoutParams(android.view.ViewGroup.LayoutParams params) {
		if (getLayoutParams() == null) {
			if (params instanceof MarginLayoutParams) {
				MarginLayoutParams layoutParams = (MarginLayoutParams) params;
				if (layoutParams != null) {
					if (!TextUtils.isEmpty(x)) {
						layoutParams.leftMargin =  ScreenUnit.toUnit(x);
					}
				
					if (!TextUtils.isEmpty(y)) {
						layoutParams.topMargin =  ScreenUnit.toUnit(y);
					}
					
					if (!TextUtils.isEmpty(width)) {
						layoutParams.width =  ScreenUnit.toUnit(width);
					}
					
					if (!TextUtils.isEmpty(height)) {
						layoutParams.height =  ScreenUnit.toUnit(height);
					}
					params = layoutParams;
				}	
			}
		}
		super.setLayoutParams(params);
	}

	@Override
	public void draw(Canvas canvas) {
		if (mPaint != null) {
			canvas.saveLayerAlpha(mRectF, 255, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
		}
		super.draw(canvas);
		if (mPaint != null) {
			canvas.drawPath(mPath, mPaint);
			canvas.restore();
		}
	}
	
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mPaint != null) {
			 mRectF.set(0, 0, getWidth(),getHeight());
			 mPath.reset();
			 mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CCW);
			 mPath.setFillType(FillType.INVERSE_WINDING);
		}
    }

	public void requestMathLayout(){
		if (expressionX != null  
				|| expressionY != null
				|| expressionWidth != null 
				|| expressionHeight != null) {
			getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					 MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
				     if (layoutParams == null) {
						 return true;
					 }
					 getViewTreeObserver().removeOnPreDrawListener(this);
				     Map<String, Object> paramsMap  = ParamMap.from(GView.this);
				     paramsMap.put(Params.HEIGHT,  getHeight());
				     paramsMap.put(Params.WIDTH,   getWidth());
				     if (!TextUtils.isEmpty(expressionX)) {
				    	      layoutParams.leftMargin = EvaluatorManager.getDefaultEvaluator().eval(expressionWidth, paramsMap);
					 }
				     
				     if (!TextUtils.isEmpty(expressionY)) {
				    	      layoutParams.topMargin = EvaluatorManager.getDefaultEvaluator().eval(expressionWidth, paramsMap);
					 }
				     
				     if (!TextUtils.isEmpty(expressionWidth)) {
						  layoutParams.width = EvaluatorManager.getDefaultEvaluator().eval(expressionWidth, paramsMap);
					 }
				     
					 if (!TextUtils.isEmpty(expressionHeight)) {
						 layoutParams.height = EvaluatorManager.getDefaultEvaluator().eval(expressionHeight, paramsMap);
					 }
					 setLayoutParams(layoutParams);
					 return false;
				}
			});
		}
		requestLayout();
	}
	
	
	

	

	public String getBackgroundAttr() {
		return backgroundAttr;
	}

	public void setBackgroundAttr(String backgroundAttr) {
		this.backgroundAttr = backgroundAttr;
	}
	
	public void setBackgroundValue(String backgroundValue) {
		if (!TextUtils.isEmpty(backgroundValue)) {
			if (backgroundValue.startsWith("#")) {
				setBackgroundColor(Color.parseColor(backgroundValue));
			}else{
				Picasso.with(getContext()).load(backgroundValue).into(new Target() {
					
					@Override
					public void onPrepareLoad(Drawable drawable) {
						
					}
					
					@SuppressWarnings("deprecation")
					@Override
					public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
                           setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
					}
					
					@Override
					public void onBitmapFailed(Drawable drawable) {
						
					}
				});
			} 
		}
	}

	public String getOnClickAttr() {
		return onClickAttr;
	}
	
	

	public void bindData(Object data){
		if (ElUtil.isEl(backgroundAttr)) {
			Object backgroundValue = ElUtil.getElValue(data, backgroundAttr);
			if(backgroundValue != null){
				setBackgroundValue(backgroundValue.toString());
			}else {
				setBackgroundDrawable(null);
			}
		}
		for (int index = 0; index < getChildCount(); index++) {
			View child = getChildAt(index);
			if (child instanceof GView) {
				GView<?> element  = (GView<?>) child;
				element.bindData(data);
			}
		}
	}
	
	

	
}
