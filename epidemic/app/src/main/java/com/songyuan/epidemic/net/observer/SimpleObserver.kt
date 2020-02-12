package com.songyuan.epidemic.net.observer

import com.songyuan.epidemic.utils.StringUtils
import com.songyuan.epidemic.utils.ToastUtils

/**
 * Created by niluogege on 2019/10/12.
 */
abstract class SimpleObserver<T> : DefaultObserver<T>() {
    override fun onFail(error: Throwable?) {
        if (error != null && StringUtils.isNotEmpty(error.message))
            ToastUtils.show(error.message!!)
    }
}