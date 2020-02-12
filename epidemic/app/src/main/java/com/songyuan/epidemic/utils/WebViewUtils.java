package com.songyuan.epidemic.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.net.URLEncoder;

/**
 * Created by niluogege on 2019/1/10.
 */

public class WebViewUtils {

    private static final String COOKIE_USER_ID = "user_id";
    public static final String ORIGIN_IDCARD_NUM = "origin_idcard_num";

    /**
     * @param url
     * @param cookieKey
     * @param cookieValue
     * @param context
     */
    public static void setCookie(String url, String cookieKey, String cookieValue, Context context) {
        try {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager.createInstance(context);
            }
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);

            String c = cookieManager.getCookie(url);
            LogUtil.d("host= " + url + "种cookie之前= " + c);


            cookieManager.setCookie(url, cookieKey + "=" + cookieValue);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }

            String newCookie = cookieManager.getCookie(url);
            LogUtil.d("host= " + url + "种cookie之后= " + newCookie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 设置user_id
     */
    public static void setLoginCookie(String url, Context context) {
        String userId = UserUtil.getUserId();
        setCookie(url, COOKIE_USER_ID, URLEncoder.encode(userId), context);
    }

    /**
     * 擦除 user_id
     */
    public static void cleanLoginCookie(String url, Context context) {
        setCookie(url, COOKIE_USER_ID, "", context);
    }


    /**
     * 删除所有cookie
     *
     * @param context
     */
    public static void cleanAllCookie(Context context) {
        try {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager.createInstance(context);
            }
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
