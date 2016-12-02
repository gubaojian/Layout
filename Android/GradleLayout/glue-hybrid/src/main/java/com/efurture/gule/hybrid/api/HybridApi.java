package com.efurture.gule.hybrid.api;

import com.efurture.gule.hybrid.api.globals.ConsoleApi;
import com.efurture.gule.hybrid.api.globals.HttpApi;
import com.efurture.gule.hybrid.api.globals.LocalStorageApi;
import com.efurture.gule.hybrid.api.globals.NetworkApi;
import com.efurture.gule.hybrid.api.globals.OSApi;
import com.efurture.gule.hybrid.api.local.MediaApi;
import com.efurture.gule.hybrid.api.local.NavApi;
import com.efurture.gule.hybrid.api.local.TimerApi;
import com.efurture.gule.hybrid.api.local.UIApi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by furture on 16/6/7.
 *  api定义,每个api包含默认构造参数 或者 包含一个context参数
 */
public class HybridApi {

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
        HybridApi.registerApplicationApi("http", HttpApi.class);
        HybridApi.registerApplicationApi("network", NetworkApi.class);
        HybridApi.registerApplicationApi("localStorage", LocalStorageApi.class);
        HybridApi.registerApplicationApi("os", OSApi.class);
        HybridApi.registerApplicationApi("console", ConsoleApi.class);


        //页面api
        HybridApi.registerActivityApi("media", MediaApi.class);
        HybridApi.registerActivityApi("ui", UIApi.class);
        HybridApi.registerActivityApi("timer", TimerApi.class);
        HybridApi.registerActivityApi("nav", NavApi.class);
    }


    /**
     * 注册全局api
     * */
    public static void registerApplicationApi(String module, Class<?> targetClass) {
          applicationApiMap.put(module, targetClass);
    }

    /**
     * 注册activity生命周期的页面api
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
