package com.songyuan.epidemic.net;

import com.songyuan.epidemic.BuildConfig;

import java.net.URL;

import rxhttp.wrapper.annotation.DefaultDomain;

/**
 * Created by niluogege on 2020/2/10.
 */
public class Config {

    @DefaultDomain() //设置为默认域名
    public static String BASE_URL = Config.BASE_URL_OFFICIAL;


    //生产环境
    private static final String BASE_URL_OFFICIAL = "http://yqfk.bdcarlife.com/api/";
    //盐山生产环境
    private static final String BASE_URL_YANSHAN = "http://yanshan.bdcarlife.com/api/";
    //开发环境
    private static final String BASE_URL_DEV = "http://dev.bdcarlife.com/api/";

    public static void setBaseUrl() {
        switch (BuildConfig.environment) {
            case 2://开发
                BASE_URL = BASE_URL_DEV;
                break;
            case 3://开发
                BASE_URL = BASE_URL_YANSHAN;
                break;
            default://默认生产
                BASE_URL = BASE_URL_OFFICIAL;
                break;
        }
    }

    public static String getBaseHost() {
        return BASE_URL.replace("api/", "");
    }
}
