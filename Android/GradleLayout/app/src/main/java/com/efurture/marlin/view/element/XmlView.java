package com.efurture.marlin.view.element;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;

import com.efurture.marlin.view.math.EvaluatorManager;
import com.efurture.marlin.view.math.ParamMap;
import com.efurture.marlin.view.math.Params;
import com.efurture.marlin.view.util.ScreenUnit;

import org.xml.sax.Attributes;

import java.util.Map;

public abstract  class XmlView<T extends View> extends FrameLayout {

	protected T view;


	private String x;
	private String y;
	private String width;
	private String height;
	private String expressionX;
	private String expressionY;
	private String expressionWidth;
	private String expressionHeight;



	private RectF mViewRectF;


	private Paint borderPaint;
	private RectF mBorderDrawRectF;


	private Paint mRadiusPaint;
	private Path  mRadiusPath;
	private float mRadius;



	public XmlView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public XmlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public XmlView(Context context) {
		super(context);
		init();
	}

	private void init() {
		view = createView();
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		addView(view, params);
	}
	
	protected abstract  T createView();
	
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
		String backgroundAttr =  attrs.getValue("background");
		if(backgroundAttr != null){
			String selectBackground = attrs.getValue("selectBackground");
			setBackgroundValue(backgroundAttr, selectBackground);
		}


		
		String alpha = attrs.getValue("alpha");
		if (!TextUtils.isEmpty(alpha)) {
			setAlpha(Float.parseFloat(alpha));
		}
		
		String borderWidth = attrs.getValue("borderWidth");
		String borderColor = attrs.getValue("borderColor");
		if (!TextUtils.isEmpty(borderWidth) && !TextUtils.isEmpty(borderColor)) {
			borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			borderPaint.setAntiAlias(true);
			borderPaint.setStyle(Paint.Style.STROKE);
			borderPaint.setColor(Color.parseColor(borderColor));
			borderPaint.setStrokeWidth(ScreenUnit.toFloatUnit(borderWidth));
			borderPaint.setDither(true);
			mViewRectF = new RectF();
			mBorderDrawRectF = new RectF();
			setWillNotDraw(false);
		}
		
		String cornerRadius = attrs.getValue("cornerRadius");
		if (!TextUtils.isEmpty(cornerRadius)) {
			 mRadius = ScreenUnit.toFloatUnit(cornerRadius);
			 mRadiusPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			 mRadiusPaint.setAntiAlias(true);
			 mRadiusPaint.setColor(Color.TRANSPARENT);
			 mRadiusPaint.setAlpha(0x00);
			 mRadiusPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			if (mViewRectF == null) {
				mViewRectF = new RectF();
			}
			 mRadiusPath = new Path();
			setWillNotDraw(false);
		}

		String valueData = attrs.getValue("valueData");
		if (!TextUtils.isEmpty(valueData)){
			setContentDescription(valueData);
		}

		String eventData = attrs.getValue("eventData");
		if (!TextUtils.isEmpty(eventData)){
			 setTag(eventData);
		}


		String visible = attrs.getValue("visible");
		if (visible != null) {
			if("false".equals(visible)){
				setVisibility(View.INVISIBLE);
			}
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
		boolean draw = (mRadiusPaint != null  ||  borderPaint != null);
		if (draw) {
			canvas.saveLayerAlpha(mViewRectF, 255, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
		}

		super.draw(canvas);

		if (mRadiusPaint != null) {
			canvas.drawPath(mRadiusPath, mRadiusPaint);
		}

		if(borderPaint != null) {
			mBorderDrawRectF.set(mViewRectF);
			mBorderDrawRectF.inset(borderPaint.getStrokeWidth()/2, borderPaint.getStrokeWidth()/2);
			if (mRadius > 0) {
				canvas.drawRoundRect(mBorderDrawRectF, mRadius, mRadius, borderPaint);
			}else {
				canvas.drawRect(mBorderDrawRectF,  borderPaint);
			}
		}

		if (draw) {
			canvas.restore();
		}

	}
	
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mRadiusPaint != null){
			mViewRectF.set(0, 0, getWidth(), getHeight());
			if(mRadiusPath != null) {
				mRadiusPath.reset();
				mRadiusPath.addRoundRect(mViewRectF, mRadius, mRadius, Path.Direction.CCW);
				mRadiusPath.setFillType(FillType.INVERSE_WINDING);
			}
		}
		if (borderPaint != null) {
			mViewRectF.set(0, 0, getWidth(), getHeight());
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
					Map<String, Object> paramsMap = ParamMap.from(XmlView.this);
					paramsMap.put(Params.HEIGHT, getHeight());
					paramsMap.put(Params.WIDTH, getWidth());
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
	}


	
	public void setBackgroundValue(String backgroundValue, String selectBackground) {
		if (!TextUtils.isEmpty(backgroundValue)) {
			if (backgroundValue.startsWith("#")) {
				if(!TextUtils.isEmpty(selectBackground)){
					StateListDrawable stateListDrawable = new StateListDrawable();
					ColorDrawable selectColorDrawable = new ColorDrawable(Color.parseColor(selectBackground));
					stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, selectColorDrawable);
					stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectColorDrawable);
					stateListDrawable.addState(new int[]{}, new ColorDrawable(Color.parseColor(backgroundValue)));
					setBackgroundDrawable(stateListDrawable);
					setClickable(true);
				}else {
					setBackgroundColor(Color.parseColor(backgroundValue));
				}
			}else{
				int resId = getContext().getResources().getIdentifier(backgroundValue, "drawable", getContext().getPackageName());
				if(resId > 0){
					setBackgroundResource(resId);
				}
			} 
		}
	}
	
}
