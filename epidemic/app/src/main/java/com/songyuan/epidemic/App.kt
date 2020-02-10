package com.songyuan.epidemic

import android.app.Application
import android.content.Context
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
    }

}