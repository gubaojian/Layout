/**
 * 
 */
package com.efurture.kingfisher.internal;

import android.util.Log;


public abstract class GLog {

    private static boolean logEnable = true;
    
    
    public static void setLogEnable(boolean enable){
        	logEnable  =  enable;
    }
    
   
    public static void v(String message){
       	if (logEnable) {
       	   Log.v("Kingfisher", message);
		}
    }
    
    public static void d(String message){
       	if (logEnable) {
       	   Log.d("Kingfisher", "Kingfisher debug " +   message);
		}
    }
    
    public static void i(String message){
       	if (logEnable) {
       	   Log.i("Kingfisher", "Kingfisher info " +   message);
		}
    }
    
    public static void w(String message){
       	if (logEnable) {
       	   Log.w("Kingfisher", "Kingfisher warn " +   message);
		}
    }
    
     public static void e(String message){
       	  Log.e("Kingfisher", "Kingfisher error " +  message);
    }
    
    public static void e(String message, Throwable e){
       	  Log.e("Kingfisher", "Kingfisher error " + message, e);
    }
}
