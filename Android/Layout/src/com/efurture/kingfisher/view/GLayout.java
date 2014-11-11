package com.efurture.kingfisher.view;

import java.util.Map;

import org.xml.sax.Attributes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

import com.efurture.kingfisher.math.EvaluatorManager;
import com.efurture.kingfisher.math.ParamMap;
import com.efurture.kingfisher.math.Params;

public abstract class GLayout<T extends View> extends FrameLayout {

	protected T view;
	protected String x;
	protected String y;
	protected String width;
	protected String height;
	protected String expressionX;
	protected String expressionY;
	protected String expressionWidth;
	protected String expressionHeight;
	protected String valueData;
	protected String eventData;
	
	private Paint mPaint;
	private float mRadius;
	private RectF mRectF;
	private Path mPath;
	public GLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public GLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GLayout(Context context) {
		super(context);
		init();
	}

	private void init() {
		view = createView();
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addView(view, params);
	}
	
	protected abstract T createView();
	
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
		
		if (expressionX == null  
				&& expressionY == null
				|| expressionWidth == null 
				|| expressionHeight == null) {
			getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					 MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
				     if (layoutParams == null) {
						 return true;
					 }
					 getViewTreeObserver().removeOnPreDrawListener(this);
				     Map<String, Object> paramsMap  = ParamMap.from(GLayout.this);
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
		
		String  background =  attrs.getValue("background");
		if (!TextUtils.isEmpty(background)) {
			setBackgroundColor(Color.parseColor(background));
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
		
		valueData = attrs.getValue("valueData");
		eventData = attrs.getValue("eventData");
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

	public String getValueData() {
		return valueData;
	}

	public String getEventData() {
		return eventData;
	}

	
}
