package com.efurture.glue.utils;

/**
 * Created by furture on 16/6/8.
 */
public class LangUtils {



    public static boolean isTrue(String value){
        if(value == null){
            return  false;
        }
        return "true".equals(value);
    }


    public static int toInt(Object object){
        return  toInt(object, 0);
    }

    public static int toInt(Object object, int defaultValue){
        if(object == null){
            return  defaultValue;
        }
        if (object instanceof  Number){
            return  ((Number) object).intValue();
        }
        return  Integer.parseInt(object.toString());
    }



}

