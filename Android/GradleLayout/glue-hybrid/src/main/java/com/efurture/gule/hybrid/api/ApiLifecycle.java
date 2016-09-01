package com.efurture.gule.hybrid.api;

import android.content.Intent;

/**
 * Created by furture on 16/6/7.
 */
public class ApiLifecycle {

    /**
     * Called when the system is about to start resuming a previous activity.
     *
     */
    public void onPause() {
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     */
    public void onResume() {
    }


    /**
     * Called when the activity receives a new intent.
     */
    public void onNewIntent(Intent intent) {
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     *
     * @param requestCode   The request code originally supplied to startActivityForResult(),
     *                      allowing you to identify who this result came from.
     * @param resultCode    The integer result code returned by the child activity through its setResult().
     * @param intent        An Intent, which can return result data to the caller (various data can be
     *                      attached to Intent "extras").
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    }



    /**
     * The final call you receive before your activity is destroyed.
     */
    public void onDestroy() {
    }
}
