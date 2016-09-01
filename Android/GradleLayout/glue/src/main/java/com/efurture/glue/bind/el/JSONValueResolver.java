package com.efurture.glue.bind.el;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by furture on 16/6/22.
 */
public class JSONValueResolver implements  ValueResolver {
    @Override
    public boolean canResolve(Object target, Class<?> cls, String name) {
        if (target instanceof JSONObject
                || target instanceof JSONArray){
            return  true;
        }
        return false;
    }

    @Override
    public Object resolve(Object target, Class<?> cls, String name) {
        if(target instanceof  JSONObject){
            JSONObject json = (JSONObject) target;
            return  json.opt(name);
        }
        if(target instanceof  JSONArray){
            JSONArray array = (JSONArray) target;
            int index = Integer.parseInt(name);
            return   array.opt(index);
        }
        return null;
    }
}
