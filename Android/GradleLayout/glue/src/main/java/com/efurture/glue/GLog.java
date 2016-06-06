/**
 * 
 */
package com.efurture.glue;

import android.util.Log;


public abstract class GLog {

    public static boolean logEnable = true;

    private static final String TAG = "Glue";


    public static void setLogEnable(boolean enable){
        	logEnable  =  enable;
    }
    
   
    public static void v(String message){
       	if (logEnable) {
       	   Log.v(TAG, message);
		}
    }
    
    public static void d(String message){
       	if (logEnable) {
       	   Log.d(TAG, "Glue debug " +   message);
		}
    }
    
    public static void i(String message){
       	if (logEnable) {
       	   Log.i(TAG, "Glue info " +   message);
		}
    }
    
    public static void w(String message){
       	if (logEnable) {
       	   Log.w(TAG, "Glue warn " +   message);
		}
    }
    
     public static void e(String message){
       	  Log.e(TAG, "Glue error " +  message);
    }
    
    public static void e(String message, Throwable e){
       	  Log.e(TAG, "Glue error " + message, e);
    }
}
