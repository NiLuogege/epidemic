package com.songyuan.epidemic

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.songyuan.epidemic.net.RxHttpManager

/**
 * Created by niluogege on 2020/2/10.
 */
class App : Application() {

    companion object {
        lateinit var context: Application
    }


    override fun onCreate() {
        super.onCreate()
        context = this
        RxHttpManager.init(this)

        initARouter()
    }


    /**
     * 初始化路由
     */
    private fun initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
        }
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }
}