package com.efurture.gule.hybrid.api.application;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.util.TimeZone;

/**
 * Created by furture on 16/6/6.
 * 系统的API的封装
 */
public class OSApi {


    private Context context;

    
    public OSApi(Context context) {
        this.context = context;
    }

    /**
     * Get the OS name.
     *
     * @return
     */
    public String getPlatform() {
        String platform = "Android";
        return platform;
    }

    /**
     * Get the device's Universally Unique Identifier (UUID).
     *
     * @return
     */
    public String getUuid() {
        String uuid = Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        return uuid;
    }

    /**
     * 获取手机型号
     * */
    public String getModel() {
        String model = android.os.Build.MODEL;
        return model;
    }

    /**
     * 获取手机产品
     * */
    public String getProductName() {
        String productname = android.os.Build.PRODUCT;
        return productname;
    }

    /**
     * 获取手机产品
     * */
    public String getManufacturer() {
        String manufacturer = android.os.Build.MANUFACTURER;
        return manufacturer;
    }

    /**
     * 获取手机序列号
     * */
    public String getSerialNumber() {
        String serial = android.os.Build.SERIAL;
        return serial;
    }

    /**
     * Get the OS version.
     * @return
     */
    public String getOSVersion() {
        String osversion = android.os.Build.VERSION.RELEASE;
        return osversion;
    }

    /**
     * 获取版本号
     * */
    public String getSDKVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /**
     * 获取时区
     * */
    public String getTimeZoneID() {
        TimeZone tz = TimeZone.getDefault();
        return (tz.getID());
    }

}
