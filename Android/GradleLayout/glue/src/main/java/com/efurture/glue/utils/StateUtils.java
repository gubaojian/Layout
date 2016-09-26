package com.efurture.glue.utils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * Created by furture on 16/9/14.
 */
public class StateUtils {


    public static ColorStateList getColorStateList(String normalColor, String selectColor){
        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed},
                new int[] { android.R.attr.state_focused},// pressed
                new int[] {}  // pressed
        };

        int[] colors = new int[] {
                Color.parseColor(selectColor),
                Color.parseColor(selectColor),
                Color.parseColor(selectColor),
                Color.parseColor(normalColor)
        };

        ColorStateList stateList = new ColorStateList(states, colors);
        return stateList;
    }


    public static StateListDrawable getStateDrawable(String normalColor, String selectColor,
                                                   float radius){
        ShapeDrawable normal = getShapeDrawable(normalColor, radius);
        ShapeDrawable select = getShapeDrawable(selectColor, radius);
        StateListDrawable states = new StateListDrawable();
        states.addState (new int[]{ android.R.attr.state_pressed }, select);
        states.addState (new int[]{ android.R.attr.state_selected }, select);
        states.addState (new int[]{ }, normal);
        return states;
    }


    public static ShapeDrawable getShapeDrawable(String color, float radius){
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{
                radius, radius, radius,radius,radius, radius, radius,radius
        }, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(Color.parseColor(color));
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        return  shapeDrawable;
    }


}
