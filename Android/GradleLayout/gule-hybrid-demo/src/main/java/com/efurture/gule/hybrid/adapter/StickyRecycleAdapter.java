package com.efurture.gule.hybrid.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.efurture.glue.bind.el.ReflectUtils;
import com.furture.react.JSRef;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;

/**
 * Created by furture on 16/6/15.
 * superSLM质量太差了,暂时废弃,后续用 https://github.com/ShamylZakariya/StickyHeaders 替代
 *
 */
public class StickyRecycleAdapter extends  RecycleAdapter {


    private int headerDisplay = LayoutManager.LayoutParams.HEADER_INLINE | LayoutManager.LayoutParams.HEADER_STICKY;

    private int slm = LinearSLM.ID;

    private int columns = 2;

    int headerPosition = 9;



    public StickyRecycleAdapter(JSRef jsRef) {
        super(jsRef);
        headerDisplay = LayoutManager.LayoutParams.HEADER_INLINE | LayoutManager.LayoutParams.HEADER_STICKY;
        slm = LinearSLM.ID;
    }


    @Override
    public ItemViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewHoder itemViewHoder =  super.onCreateViewHolder(parent, viewType);
        View view = itemViewHoder.itemView;
        boolean isHeader = false;
        if("StickHeader".equals(itemViewHoder.itemView.getTag())){
            isHeader = true;
        }
        if(view.getLayoutParams() == null){
            view.setLayoutParams((ViewGroup.LayoutParams) ReflectUtils.invoke(parent, "generateDefaultLayoutParams"));
        }
        LayoutManager.LayoutParams params = (LayoutManager.LayoutParams) view.getLayoutParams();
        params.isHeader = isHeader;
        if(isHeader){
            params.headerDisplay = headerDisplay;
        }
        return  itemViewHoder;
    }

    @Override
    public void onBindViewHolder(ItemViewHoder holder, int position) {
        super.onBindViewHolder(holder, position);
        View itemView = holder.itemView;
        boolean isHeader =  (headerPosition  == position || position == 0);
        GridSLM.LayoutParams  params = GridSLM.LayoutParams.from(itemView.getLayoutParams());
        if(isHeader){
             params.headerDisplay = headerDisplay;
        }



        if(slm == GridSLM.ID){
            params.setNumColumns(columns);
        }else {
            params.setSlm(LinearSLM.ID);
        }
        if(position < headerPosition ){
            params.setFirstPosition(0);
        }else{
            params.setFirstPosition(headerPosition);
        }

        itemView.setLayoutParams(params);
    }

    public void setStyle(String style){
        if("grid".equals(style)){
            slm = GridSLM.ID;
        }
    }

    public void setColumns(Number columns){
        this.columns = columns.intValue();
    }




}
