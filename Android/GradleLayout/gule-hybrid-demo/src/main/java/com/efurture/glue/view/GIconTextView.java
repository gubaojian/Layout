package com.efurture.glue.view;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by furture on 16/6/7.
 *
 *  http://ionicons.com/
 */
public class GIconTextView extends GTextView {

    private static Typeface iconfont;


    public GIconTextView(Context context) {
        super(context);
        initIconFont();

    }

    private void initIconFont(){
        if(!isInEditMode()){
            // read typeface only once!
            // yey performance :)
            if(iconfont == null){
                iconfont = Typeface.createFromAsset(getContext().getAssets(), "ionicons.ttf");
            }
            if(iconfont != null) {
                this.setTypeface(iconfont);
            }
        }
    }


    public void setText(CharSequence text, BufferType type) {
        if(text.length() > 2){
            if(text.charAt(0) == '0' && text.charAt(1) == 'f'){
                char ch = (char) Integer.parseInt(text.toString(), 16);
                super.setText(Character.toString(ch), type);
                return;
            }
        }
        super.setText(text, type);
    }

    /**
    public void setAnimation(int animation){
        switch(animation){
            case 0: // ROT_LEFT_NORMAL
                this.startAnimation( AnimationUtils.loadAnimation(getContext(), R.anim.rotate_left_1) );
                break;
            case 1: // ROT_LEFT_STEPS TODO
                this.startAnimation( AnimationUtils.loadAnimation(getContext(), R.anim.rotate_left_1) );
                break;
        }
    }

    public void stopAnimation(){
        this.clearAnimation();
    }
*/


}
