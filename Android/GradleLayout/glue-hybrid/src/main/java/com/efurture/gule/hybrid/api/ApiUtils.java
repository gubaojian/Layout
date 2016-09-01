package com.efurture.gule.hybrid.api;

import android.content.Context;

import com.efurture.glue.GLog;
import com.furture.react.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * Created by furture on 16/6/7.
 */
public class ApiUtils {



    public static Object newApi(Class targetClass, Context context) {

        Constructor[] classConstructors =  targetClass.getConstructors();
        Constructor<?> constructor = null;

        for(Constructor<?> classConstructor : classConstructors){
            Class<?>[] types = classConstructor.getParameterTypes();
             if(types.length == 0){
                 constructor = classConstructor;
             }else if (types.length == 1 && types[0].isAssignableFrom(context.getClass())){
                 constructor = classConstructor;
             }
        }

        if(constructor == null){
            throw  new RuntimeException(targetClass.getName() + " is Illegal Api");
        }
        try{
            if(constructor.getParameterTypes().length == 0){
                return  constructor.newInstance();
            }else{
                return  constructor.newInstance(context);
            }
        }catch (Exception e){
            GLog.e("new api error, api class " + targetClass.getName(), e);
            return  null;
        }
    }


}
