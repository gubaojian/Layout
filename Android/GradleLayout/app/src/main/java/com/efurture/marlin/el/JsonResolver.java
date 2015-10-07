package com.efurture.marlin.el;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by baobao on 15/10/6.
 */
public class JsonResolver  implements ValueResolver{

    @Override
    public boolean canResolve(Object target, Class<?> cls, String name) {
        return target instanceof JSONObject || target instanceof JSONArray;
    }

    @Override
    public Object resolve(Object target, Class<?> cls, String name) {
        if (target instanceof JSONObject) {
            return ((JSONObject) target).opt(name);
        }
        if (target instanceof JSONArray) {
            int index = Integer.parseInt(name);
            JSONArray array = (JSONArray) target;
            array.opt(index);
        }
        throw new RuntimeException("JsonResolver Get Wrong Object Type");
    }

}
