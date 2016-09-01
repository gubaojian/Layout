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
    private static Map<String, Class<?>> globalClassMap = new HashMap();

    /**
     * activity生命周期的api, 每个页面相互独立,不共享实例, 页面内实例
     * 可继承ApiLifecycle获取, 在activity被销毁时销毁
     * */
    private static Map<String, Class<?>> localApiClassMap = new HashMap();


    /**
     * 内置api初始化
     * */
    static {

        //全局api
        HybridApi.registerGlobalClass("http", HttpApi.class);
        HybridApi.registerGlobalClass("network", NetworkApi.class);
        HybridApi.registerGlobalClass("localStorage", LocalStorageApi.class);
        HybridApi.registerGlobalClass("os", OSApi.class);
        HybridApi.registerGlobalClass("console", ConsoleApi.class);


        //页面api
        HybridApi.registerLocalClass("media", MediaApi.class);
        HybridApi.registerLocalClass("ui", UIApi.class);
        HybridApi.registerLocalClass("timer", TimerApi.class);
        HybridApi.registerLocalClass("nav", NavApi.class);
    }


    /**
     * 注册全局api
     * */
    public static void registerGlobalClass(String module, Class<?> targetClass) {
          globalClassMap.put(module, targetClass);
    }

    /**
     * 注册activity生命周期的页面api
     * */
    public static void registerLocalClass(String module, Class<?> targetClass) {
        localApiClassMap.put(module, targetClass);
    }


    public static Map<String, Class<?>> getGlobalClassMap() {
        return  globalClassMap;
    }


    public static Map<String, Class<?>> getLocalClassMap() {
        return localApiClassMap;
    }




}
