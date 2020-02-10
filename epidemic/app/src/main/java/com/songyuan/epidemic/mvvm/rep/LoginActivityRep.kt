package com.songyuan.epidemic.mvvm.rep

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.aihuishou.commonlib.base.mvvm.BaseRepository
import com.rxjava.rxlife.RxLife
import com.rxjava.rxlife.lifeOnMain
import com.songyuan.epidemic.base.mvvm.RequestState
import com.songyuan.epidemic.mvvm.model.LoginInfo
import com.songyuan.epidemic.utils.LogUtil
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.param.RxHttp

/**
 * Created by niluogege on 2020/2/10.
 */
class LoginActivityRep : BaseRepository() {


    fun login(): MutableLiveData<RequestState<LoginInfo>> {

        val liveData = getLiveData<LoginInfo>()

        RxHttp.postForm("maintenance/pic")
            .asBaseResponse(LoginInfo::class.java)
            .subscribe({ s ->
                {
                    LogUtil.e("s=" + s)
                }
            }, { error -> {} })

        return liveData
    }

}