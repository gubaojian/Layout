package com.efurture.gule.hybrid.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.efurture.glue.utils.LangUtils;
import com.furture.react.JSRef;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by furture on 16/6/13.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ItemViewHoder> {

    private Map<String,Integer> viewTypesMap;  // 字符串到type映射
    private SparseArray<String> viewTypesSparseArray;  //type到字符串映射
    private int nextType;
    protected JSRef jsRef;


    public RecycleAdapter(JSRef jsRef) {
        this.jsRef = jsRef;
        viewTypesMap = new HashMap<>();
        viewTypesSparseArray = new SparseArray<>();
        nextType = 0;
    }

    @Override
    public ItemViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        Object type = viewTypesSparseArray.get(viewType);
        if(type == null){
            type = viewType;
        }
        View itemView = (View) jsRef.getEngine().call(jsRef, "onCreateView", parent, type);
        return new ItemViewHoder(itemView);
    }




    @Override
    public void onBindViewHolder(ItemViewHoder holder, int position) {
        View  view = holder.itemView;
        jsRef.getEngine().call(jsRef, "onBindView",  view, position);
    }


    @Override
    public int getItemCount() {
        return LangUtils.toInt(jsRef.getEngine().call(jsRef, "getItemCount"), 0);
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
            viewTypesSparseArray.put(viewType, type.toString());
            nextType ++;
        }
        return viewType.intValue();
    }


    public  static  class  ItemViewHoder extends  RecyclerView.ViewHolder{
        public ItemViewHoder(View itemView) {
            super(itemView);
        }
    }
}
