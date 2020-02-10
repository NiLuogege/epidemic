package com.songyuan.epidemic.utils

import android.net.Uri
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by niluogege on 2018/12/5.
 * Arouter 工具类
 */
object ArouterUtils {

    /**
     * 获取路由
     */
    fun getRouter(): ARouter {
        return ARouter.getInstance()
    }

    /**
     * 使用路由跳转
     *
     * @param uri
     */
    fun routerTo(uri: Uri?) {
        if (null != uri)
            getRouter().build(uri).navigation()
    }

    /**
     * 使用路由跳转
     *
     * @param path
     */
    fun routerTo(path: String?) {
        if (StringUtils.isNotEmpty(path))
            getRouter().build(path).navigation()
    }



    /**
     * 通过path,返回一个fragment
     */
    fun getFragment(path: String): Any? {
        if (StringUtils.isNotEmpty(path)) {
            return (getRouter().build(path).navigation())
        }
        return null
    }
}