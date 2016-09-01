package com.efurture.gule.hybrid.adapter;

import com.efurture.glue.utils.LangUtils;
import com.furture.react.JSRef;
import com.furture.react.ext.JSBaseAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by furture on 16/6/8.
 */
public class MultiTypeAdapter extends JSBaseAdapter{

    private Map<String,Integer> viewTypesMap;
    private int nextType;

    public MultiTypeAdapter(JSRef jsRef) {
        super(jsRef);
        viewTypesMap = new HashMap<>();
        nextType = 0;
    }


    @Override
    public int getItemViewType(int position) {
        Object type = jsRef.getEngine().call(jsRef, "getItemViewType", position);
        if(type instanceof  Number){
            return  ((Number) type).intValue();
        }
        if(type == null){
            return  0;
        }
        Integer viewType = viewTypesMap.get(type.toString());
        if(viewType == null){
            viewType = nextType;
            viewTypesMap.put(type.toString(), viewType);
            nextType ++;
        }
        return viewType.intValue();
    }



    @Override
    public int getViewTypeCount() {
        return LangUtils.toInt(jsRef.getEngine().call(jsRef, "getViewTypeCount"), 1);
    }



}
