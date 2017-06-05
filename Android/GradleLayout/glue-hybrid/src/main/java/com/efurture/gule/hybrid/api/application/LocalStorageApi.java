package com.efurture.gule.hybrid.api.application;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by furture on 16/6/6.
 * https://developer.mozilla.org/en-US/docs/Web/API/Storage/LocalStorage
 * 通过SharePreference存储标记
 */
public class LocalStorageApi {

    private Context context;

    private SharedPreferences sharedPreferences;

    public LocalStorageApi(Context context) {
        this("localStorage", context);
    }

    private LocalStorageApi(String fileName, Context context) {
        this.context = context.getApplicationContext();
        this.sharedPreferences = this.context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public LocalStorageApi newFile(String fileName){
        return  new LocalStorageApi(fileName, context);
    }

    public void setItem(String key, String value){
        set(key, value);
    }

    public String getItem(String key){
        return  get(key);
    }

    public void removeItem(String key){
        remove(key);
    }

    public int length(){
       return  size();
    }

    public void set(String key, String value){
        sharedPreferences.edit().putString(key, value).commit();
    }

    public String get(String key){
        return  sharedPreferences.getString(key, "");
    }

    public void remove(String key){
        sharedPreferences.edit().remove(key);
    }

    public int size(){
        return  sharedPreferences.getAll().size();
    }
}
