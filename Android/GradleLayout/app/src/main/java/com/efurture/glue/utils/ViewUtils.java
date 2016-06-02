package com.efurture.glue.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.engine.XmlException;

import org.xml.sax.Attributes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by furture on 16/6/1.
 */
public class ViewUtils {


    private static Map<Class, ViewAttrable> attrableMap = new HashMap<Class, ViewAttrable>();





    /**
     * @param  view
     * @param  attrs
     *
     * 初始化View的非布局属性
     * */
    public  static void initAttrs(View view, Attributes attrs, ViewInflater inflater){

        /**
         * 布局属性
         * */
        View parent = (View) view.getParent();
        if(parent != null){
            String x = attrs.getValue("x");
            String y = attrs.getValue("y");
            String width = attrs.getValue("width");
            String height = attrs.getValue("height");
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) params;
                if (!TextUtils.isEmpty(x)) {
                    layoutParams.leftMargin =  inflater.toUnit(x, parent, true);
                }

                if (!TextUtils.isEmpty(y)) {
                    layoutParams.topMargin =  inflater.toUnit(y, parent, false);
                }

                if (!TextUtils.isEmpty(width)) {
                    layoutParams.width =  inflater.toUnit(width, parent, true);
                }

                if (!TextUtils.isEmpty(height)) {
                    layoutParams.height =  inflater.toUnit(height, parent, false);
                }
            }
        }

        /**
         *
         * View的属性列表
         * */
        String backgroundAttr =  attrs.getValue("background");
        if(backgroundAttr != null){
            String selectBackground = attrs.getValue("selectBackground");
            if (!TextUtils.isEmpty(backgroundAttr)) {
                if (backgroundAttr.startsWith("#")) {
                    if(!TextUtils.isEmpty(selectBackground)){
                        StateListDrawable stateListDrawable = new StateListDrawable();
                        ColorDrawable selectColorDrawable = new ColorDrawable(Color.parseColor(selectBackground));
                        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, selectColorDrawable);
                        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectColorDrawable);
                        stateListDrawable.addState(new int[]{}, new ColorDrawable(Color.parseColor(backgroundAttr)));
                        view.setBackgroundDrawable(stateListDrawable);
                        view.setClickable(true);
                    }else {
                        view.setBackgroundColor(Color.parseColor(backgroundAttr));
                    }
                }else{
                    int resId = view.getContext().getResources().getIdentifier(backgroundAttr, "drawable", view.getContext().getPackageName());
                    if(resId > 0){
                        view.setBackgroundResource(resId);
                    }
                }
            }
        }


        String alpha = attrs.getValue("alpha");
        if (!TextUtils.isEmpty(alpha)) {
            view.setAlpha(Float.parseFloat(alpha));
        }

        String tag = attrs.getValue("tag");
        if (tag != null) {
            view.setTag(tag);
        }

        String visible = attrs.getValue("visible");
        if (visible != null) {
            if("false".equals(visible)){
                view.setVisibility(View.INVISIBLE);
            }
        }

        String enabled = attrs.getValue("enabled");
        if (enabled != null){
            enabled = enabled.toLowerCase().trim();
            if ("true".equals(enabled) || "yes".equals(enabled)){
                view.setEnabled(true);
            }else{
                view.setEnabled(false);
            }
        }


        /**
         * 采用非强制接口的方式实现, 类似object-c的protocol,减轻接口依赖
         * */
        Class<?> viewClass = view.getClass();
        ViewAttrable viewAttrable = attrableMap.get(viewClass);
        if(viewAttrable == null){
            Method method = null;
            try {
               method =  viewClass.getMethod("initViewAtts", Attributes.class);
               viewAttrable = new ViewAttrable(ONE_ATTR_ABLE, method);
            } catch (NoSuchMethodException e) {
                try{
                    method =  viewClass.getMethod("initViewAtts", Attributes.class, ViewInflater.class);
                    viewAttrable = new ViewAttrable(TWO_ATTR_ABLE, method);
                }catch (NoSuchMethodException e2) {
                    viewAttrable = new ViewAttrable(NONE_ATTR_ABLE, null);
                }
            }
            attrableMap.put(viewClass, viewAttrable);
        }

        if(viewAttrable.attrable != NONE_ATTR_ABLE && viewAttrable.method != null){
            try {
                if(viewAttrable.attrable == ONE_ATTR_ABLE){
                    viewAttrable.method.invoke(view, attrs);
                }else{
                    viewAttrable.method.invoke(view, attrs, inflater);
                }
            } catch (IllegalAccessException e) {
                 throw  new XmlException(e);
            } catch (InvocationTargetException e) {
                e.getTargetException().printStackTrace();
                throw  new XmlException(e.getTargetException());
            }
        }


    }



    private  static final int NONE_ATTR_ABLE = 0;

    private  static final int ONE_ATTR_ABLE = 1;

    private  static final int TWO_ATTR_ABLE = 2;

    public static  class  ViewAttrable {
        final int attrable;
        final Method method;

        public ViewAttrable(int attrable, Method method) {
            this.attrable = attrable;
            this.method = method;
        }
    }


}
