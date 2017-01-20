package com.efurture.gule.hybrid.api;

import com.efurture.gule.hybrid.api.application.ConsoleApi;
import com.efurture.gule.hybrid.api.application.HttpApi;
import com.efurture.gule.hybrid.api.application.LocalStorageApi;
import com.efurture.gule.hybrid.api.application.NetworkApi;
import com.efurture.gule.hybrid.api.application.OSApi;
import com.efurture.gule.hybrid.api.activity.MediaApi;
import com.efurture.gule.hybrid.api.activity.NavApi;
import com.efurture.gule.hybrid.api.activity.TimerApi;
import com.efurture.gule.hybrid.api.activity.UIApi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by furture on 16/6/7.
 *  api定义,每个api包含默认构造参数 或者 包含一个context参数
 */
public class JSApi {

    /**
     * application全局api, 单利,无生命周期
     * */
    private static Map<String, Class<?>> applicationApiMap = new HashMap();

    /**
     * activity生命周期的api, 每个页面相互独立,不共享实例, 页面内实例
     * 可继承ApiLifecycle获取, 在activity被销毁时销毁
     * */
    private static Map<String, Class<?>> activityApiMap = new HashMap();


    /**
     * 内置api初始化
     * */
    static {
        //全局api
        JSApi.registerApplicationApi("http", HttpApi.class);
        JSApi.registerApplicationApi("network", NetworkApi.class);
        JSApi.registerApplicationApi("localStorage", LocalStorageApi.class);
        JSApi.registerApplicationApi("os", OSApi.class);
        JSApi.registerApplicationApi("console", ConsoleApi.class);


        //页面api
        JSApi.registerActivityApi("media", MediaApi.class);
        JSApi.registerActivityApi("ui", UIApi.class);
        JSApi.registerActivityApi("timer", TimerApi.class);
        JSApi.registerActivityApi("nav", NavApi.class);
    }


    /**
     * 注册全局api, 仅有一个实例，无生命周期
     * */
    public static void registerApplicationApi(String module, Class<?> targetClass) {
          applicationApiMap.put(module, targetClass);
    }

    /**
     * 注册activity生命周期的页面api，有activity的生命周期的回调
     * */
    public static void registerActivityApi(String module, Class<?> targetClass) {
        activityApiMap.put(module, targetClass);
    }


    public static Map<String, Class<?>> getApplicationApiMap() {
        return applicationApiMap;
    }


    public static Map<String, Class<?>> getActivityApiMap() {
        return activityApiMap;
    }




}
