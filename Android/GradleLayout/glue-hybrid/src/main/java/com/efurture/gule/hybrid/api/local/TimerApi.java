package com.efurture.gule.hybrid.api.local;

import android.app.Activity;
import android.os.Handler;
import android.util.SparseArray;

import com.efurture.gule.hybrid.api.ApiLifecycle;
import com.furture.react.JSRef;

/**
 *
 *
 *
 * Created by furture on 16/6/7.
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/WindowTimers/clearInterval
 *
 */
public class TimerApi extends ApiLifecycle {

    private Handler handler;
    private Activity activity;
    private SparseArray tasks;

    private boolean isPaused = false;

    public TimerApi(Activity activity) {
        this.activity = activity;
        this.handler = new Handler();
        this.tasks = new SparseArray();
    }

    public int setInterval(final JSRef jsRef, final int interval){
        if(jsRef == null){
            return -1;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(!isPaused){
                    jsRef.getEngine().call(jsRef, "run");
                }
                handler.postDelayed(this, interval);
            }
        };
        handler.postDelayed(runnable, interval);
        int index = tasks.size();
        tasks.put(index, runnable);
        return  index;
    }


    public  void clearInterval(int interval){
        Runnable runnable = (Runnable) tasks.get(interval);
        if(runnable != null){
            handler.removeCallbacks(runnable);
        }
    }

    public  int setTimeout(final JSRef jsRef, int interval){
        if(jsRef == null){
            return -1;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                jsRef.getEngine().call(jsRef, "run");
            }
        };
        handler.postDelayed(runnable, interval);
        int index = tasks.size();
        tasks.put(index, runnable);
        return  index;
    }

    public  void  clearTimeout(int interval){
        Runnable runnable = (Runnable) tasks.get(interval);
        if(runnable != null){
            handler.removeCallbacks(runnable);
        }
    }



    /**
     * Called when the system is about to start resuming a previous activity.
     *
     */
    public void onPause() {
        isPaused = true;
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     */
    public void onResume() {
        isPaused = false;
    }


    @Override
    public void onDestroy() {
        isPaused = true;
        while (tasks.size() > 0){
            int key = tasks.keyAt(0);
            Runnable runnable = (Runnable) tasks.get(key);
            if(runnable != null){
                handler.removeCallbacks(runnable);
            }
            tasks.remove(key);
        }
        super.onDestroy();
    }
}
