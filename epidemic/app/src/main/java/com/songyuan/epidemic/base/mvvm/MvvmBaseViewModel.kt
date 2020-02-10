package com.aihuishou.commonlib.base.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rxjava.rxlife.ScopeViewModel
import com.songyuan.epidemic.App
import com.songyuan.epidemic.base.mvvm.RequestState


/**
 * Created by niluogege on 2019/10/9.
 */
open class MvvmBaseViewModel : ScopeViewModel(App.context) {

    protected fun <T> getLiveData(loading: Boolean = true): MutableLiveData<RequestState<T>> {
        val liveData = MutableLiveData<RequestState<T>>()
        if (loading)
            liveData.value = RequestState.loading()
        return liveData
    }

}