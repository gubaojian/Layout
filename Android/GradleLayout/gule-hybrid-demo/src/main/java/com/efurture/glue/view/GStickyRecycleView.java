package com.efurture.glue.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.efurture.glue.engine.ViewInflater;
import com.efurture.gule.hybrid.adapter.RecycleAdapter;

import org.xml.sax.Attributes;

/**
 * Created by furture on 16/6/15.
 * superSLM质量太差了viewpager在里面有问题,
 * 暂时废弃, 后续用 https://github.com/ShamylZakariya/StickyHeaders 替代
 */
public class GStickyRecycleView extends RecyclerView {

    public GStickyRecycleView(Context context) {
        super(context);
    }

    public GStickyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GStickyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }




    /**
        public void initViewAtts(Attributes attrs, final ViewInflater inflater) {
        // super.initViewAtts(attrs, inflater);
         String layout = attrs.getValue("layout");
         if("sticky".equals(layout)){
             setLayoutManager(new com.tonicartos.superslim.LayoutManager(getContext()));
         }
    }*/
}
