package com.songyuan.epidemic.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel
import com.rxjava.rxlife.lifeOnMain
import com.songyuan.epidemic.base.mvvm.RequestState
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand
import com.songyuan.epidemic.mvvm.model.LoginInfo
import com.songyuan.epidemic.mvvm.view.LoginActivity
import com.songyuan.epidemic.net.observer.LiveDataObserver
import com.songyuan.epidemic.utils.StringUtils
import com.songyuan.epidemic.utils.ToastUtils
import io.reactivex.functions.Consumer
import rxhttp.wrapper.param.RxHttp

/**
 * Created by niluogege on 2020/2/10.
 */
class LoginActivityViewModel() : MvvmBaseViewModel() {


    val name = ObservableField<String>("")
    val pass = ObservableField<String>("")


    val onLoginBtnClicked = MutableLiveData<View>()

    fun login(): MutableLiveData<RequestState<LoginInfo>> {
        var name = name.get()
        var pass = pass.get()
        if (StringUtils.isEmpty(name)) {
            ToastUtils.show("请输入用户名")
            return MutableLiveData()
        }

        if (StringUtils.isEmpty(pass)) {
            ToastUtils.show("请输入密码")
            return MutableLiveData()
        }


        val liveData = getLiveData<LoginInfo>()

        RxHttp.postForm("login")
            .add("phone", name)
            .add("password", pass)
            .asBaseResponse(LoginInfo::class.java)
            .lifeOnMain(this)
            .subscribe(LiveDataObserver(liveData))

        return liveData
    }
}