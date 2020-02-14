package com.songyuan.epidemic.mvvm.vm

import androidx.lifecycle.MutableLiveData
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel
import com.rxjava.rxlife.lifeOnMain
import com.songyuan.epidemic.base.mvvm.RequestState
import com.songyuan.epidemic.mvvm.model.Address
import com.songyuan.epidemic.mvvm.model.LoginInfo
import com.songyuan.epidemic.net.observer.DefaultObserver
import com.songyuan.epidemic.net.observer.LiveDataObserver
import com.songyuan.epidemic.utils.ToastUtils
import com.songyuan.epidemic.utils.UserUtil
import io.reactivex.functions.Consumer
import rxhttp.wrapper.param.RxHttp

/**
 * Created by niluogege on 2020/2/14.
 */
class AddressActivityViewModel : MvvmBaseViewModel() {


    fun getAddress(): MutableLiveData<RequestState<List<Address>>> {

        val liveData = getLiveData<List<Address>>()

        RxHttp.get("find-cs-by-id")
            .add("cp_id", UserUtil.userId)
            .asResponseList(Address::class.java)
            .lifeOnMain(this)
            .subscribe(LiveDataObserver(liveData))


        return liveData
    }


}