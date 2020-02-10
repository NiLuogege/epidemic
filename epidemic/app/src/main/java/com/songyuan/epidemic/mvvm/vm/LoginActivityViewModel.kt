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
import io.reactivex.functions.Consumer
import rxhttp.wrapper.param.RxHttp

/**
 * Created by niluogege on 2020/2/10.
 */
class LoginActivityViewModel(val activity: LoginActivity) : MvvmBaseViewModel() {


    val name = ObservableField<String>("")
    val pass = ObservableField<String>("")


    val onLoginBtnClicked = MutableLiveData<View>()

    fun login(): MutableLiveData<RequestState<LoginInfo>> {

        val liveData = getLiveData<LoginInfo>()

        RxHttp.postForm("login")
            .add("phone", name.get())
            .add("password", pass.get())
            .asBaseResponse(LoginInfo::class.java)
            .lifeOnMain(this)
            .subscribe(LiveDataObserver(liveData))

        return liveData
    }
}