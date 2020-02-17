package com.songyuan.epidemic.net;

import com.songyuan.epidemic.BuildConfig;

import rxhttp.wrapper.annotation.DefaultDomain;

/**
 * Created by niluogege on 2020/2/10.
 */
public class Config {

    @DefaultDomain() //设置为默认域名
    public static String BASE_URL = "http://" + BuildConfig.host + ".bdcarlife.com/api/";


    public static String getBaseHost() {
        return BASE_URL.replace("api/", "");
    }
}