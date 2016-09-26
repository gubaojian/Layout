package com.efurture.glue.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.glue.engine.XmlException;
import com.efurture.glue.ui.XmlView;

import org.xml.sax.Attributes;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by furture on 16/6/1.
 */
public class ViewUtils {



    private static  final Pattern GRAVITY_PATTERN = Pattern.compile("\\|");

    /**
     * 快捷扩展的infaler方法
     * */
    public static int getGravity(String gravityStr){
        int  gravity = 0;
        String[] gravities = GRAVITY_PATTERN.split(gravityStr);
        for(String gravityItem : gravities){
             String item = gravityItem.trim();
             if("top".equals(item)){
                 gravity = gravity | Gravity.TOP;
             }else if("bottom".equals(item)){
                gravity = gravity | Gravity.BOTTOM;
             }else if("centerVertical".equals(item)){
                gravity = gravity | Gravity.CENTER_VERTICAL;
             }else if("centerHorizontal".equals(item)){
                gravity = gravity | Gravity.CENTER_HORIZONTAL;
             }else if("left".equals(item)){
                 gravity = gravity | Gravity.LEFT;
             }else if("right".equals(item)){
                gravity = gravity | Gravity.RIGHT;
             }else if("start".equals(item)){
                gravity = gravity | Gravity.START;
             }else if("end".equals(item)){
                 gravity = gravity | Gravity.END;
             }else if("center".equals(item)){
                gravity = gravity | Gravity.CENTER;
             }

        }
        return  gravity;
    }


    /**
     * 快捷扩展的infaler方法
     * */
    public static View inflate(Context context, String xml, final ViewGroup parent){
        return  inflate(context, xml, parent, parent != null);
    }

    public static View inflate(Context context, String xml, final ViewGroup parent, boolean attachRoot){
        ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes());
        try {
            return  ViewInflater.from(context).inflate(stream, parent, attachRoot);
        } catch (Exception e) {
            throw  new XmlException(e);
        }
    }





    public static boolean isDebugable(Context context) {
        return  true;
        /**
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags& ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {
            return  false;
        }*/
    }


    /**
     * 创建hybridview, 继承资源加载器
     * */
    public static XmlView newHybridView(View context){
         XmlView hybridView = new XmlView(context.getContext());
         XmlView parentHybrid = findParent(context);
        if(parentHybrid != null){
            hybridView.setResourceLoader(parentHybrid.getResourceLoader());
        }
        return  hybridView;
    }



    /**
     * 查找 父容器中是否已经有XmlView
     * */
    public static XmlView findParent(View view){
        XmlView hybridView = null;
        while (view.getParent() != null){
            if(view.getParent() instanceof  View){
                view = (View) view.getParent();
                if(view instanceof XmlView){
                    hybridView = (XmlView) view;
                }
            }else{
                break;
            }
        }
        return hybridView;
    }

    /**
     * @param  view
     * @param  attrs
     *
     * 初始化View的非布局属性, 及默认的公共属性
     * */
    public  static void initAttrs(View view, Attributes attrs, ViewInflater inflater){

        /**
         * 布局属性
         * */

        ViewGroup.LayoutParams params = view.getLayoutParams();
        if(params != null){
            String x = attrs.getValue("x");
            String y = attrs.getValue("y");
            String width = attrs.getValue("width");
            String height = attrs.getValue("height");
            String right = attrs.getValue("right");
            String bottom = attrs.getValue("bottom");
            if (params instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) params;
                View parent = (View) view.getParent();
                if(parent != null){
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


                    if(!TextUtils.isEmpty(right)){
                        layoutParams.rightMargin = inflater.toUnit(right, parent, false);
                    }

                    if(!TextUtils.isEmpty(bottom)){
                        layoutParams.bottomMargin = inflater.toUnit(bottom, parent, false);
                    }


                }else {
                    if (!TextUtils.isEmpty(x)) {
                        layoutParams.leftMargin =  inflater.toUnit(x);
                    }

                    if (!TextUtils.isEmpty(y)) {
                        layoutParams.topMargin =  inflater.toUnit(y);
                    }

                    if (!TextUtils.isEmpty(width)) {
                        layoutParams.width =  inflater.toUnit(width);
                    }

                    if (!TextUtils.isEmpty(height)) {
                        layoutParams.height =  inflater.toUnit(height);
                    }

                    if(!TextUtils.isEmpty(right)){
                        layoutParams.rightMargin = inflater.toUnit(right);
                    }

                    if(!TextUtils.isEmpty(bottom)){
                        layoutParams.bottomMargin = inflater.toUnit(bottom);
                    }
                }
            }


            if(params instanceof FrameLayout.LayoutParams){

                String gravity = attrs.getValue("gravity");
                if(!TextUtils.isEmpty(gravity)) {
                    ((FrameLayout.LayoutParams) params).gravity = getGravity(gravity);


                }
            }else if(params instanceof LinearLayout.LayoutParams){
                String gravity = attrs.getValue("gravity");
                if(!TextUtils.isEmpty(gravity)) {
                    ((LinearLayout.LayoutParams) params).gravity = getGravity(gravity);
                }


                String  weight = attrs.getValue("weight");
                if(!TextUtils.isEmpty(weight)){
                    ((LinearLayout.LayoutParams) params).weight = Integer.parseInt(weight);
                }
            }
        }

        /**
         *
         * View的属性列表
         * */
        String backgroundAttr =  attrs.getValue("background");
        if(backgroundAttr != null){
            if (!TextUtils.isEmpty(backgroundAttr)) {
                if (backgroundAttr.startsWith("#")) {
                    String selectBackground = attrs.getValue("selectBackground");
                    if(!TextUtils.isEmpty(selectBackground)){
                        String radius = attrs.getValue("radius");
                        if(!TextUtils.isEmpty(radius)){
                             view.setBackgroundDrawable(StateUtils.getStateDrawable(backgroundAttr, selectBackground, inflater.toUnit(radius)));
                        }else{
                            StateListDrawable stateListDrawable = new StateListDrawable();
                            ColorDrawable selectColorDrawable = new ColorDrawable(Color.parseColor(selectBackground));
                            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, selectColorDrawable);
                            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectColorDrawable);
                            stateListDrawable.addState(new int[]{}, new ColorDrawable(Color.parseColor(backgroundAttr)));
                            view.setBackgroundDrawable(stateListDrawable);
                        }
                        view.setClickable(true);
                    }else {
                        String radius = attrs.getValue("radius");
                        if(!TextUtils.isEmpty(radius)){
                            view.setBackgroundDrawable(StateUtils.getShapeDrawable(backgroundAttr, inflater.toUnit(radius)));
                        }else{
                            view.setBackgroundColor(Color.parseColor(backgroundAttr));
                        }
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
            view.setEnabled(LangUtils.isTrue(visible));
        }

        String enabled = attrs.getValue("enabled");
        if (enabled != null){
            view.setEnabled(LangUtils.isTrue(enabled));
        }


        String clickable = attrs.getValue("clickable");
        if (clickable != null){
            view.setClickable(LangUtils.isTrue(clickable));
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
    private static Map<Class, ViewAttrable> attrableMap = new HashMap<Class, ViewAttrable>();



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
