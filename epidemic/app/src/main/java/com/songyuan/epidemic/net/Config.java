package com.songyuan.epidemic.net;

import com.songyuan.epidemic.BuildConfig;
import com.songyuan.epidemic.utils.LogUtil;

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
    // yingtan 生产环境
    private static final String BASE_URL_YINGTAN = "http://yingtan.bdcarlife.com/api/";
    // quyang 生产环境
    private static final String BASE_URL_QUYANG = "http://quyang.bdcarlife.com/api/";

    public static void setBaseUrl() {
        switch (BuildConfig.environment) {
            case 2://开发
                BASE_URL = BASE_URL_DEV;
                break;
            case 3://盐山
                BASE_URL = BASE_URL_YANSHAN;
                break;
            case 4://yingtan
                BASE_URL = BASE_URL_YINGTAN;
                break;
            case 5://quyang
                BASE_URL = BASE_URL_QUYANG;
                break;
            default://默认生产
                BASE_URL = BASE_URL_OFFICIAL;
                break;
        }

        LogUtil.e("BASE_URL= " + BASE_URL);
    }

    public static String getBaseHost() {
        return BASE_URL.replace("api/", "");
    }
}
