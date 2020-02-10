package com.aihuishou.commonlib.base.mvvm

import androidx.lifecycle.MutableLiveData
import com.songyuan.epidemic.base.mvvm.RequestState

/**
 * Created by niluogege on 2019/10/9.
 */
open class BaseRepository {


    protected fun <T> getLiveData(loading: Boolean = true): MutableLiveData<RequestState<T>> {
        val liveData = MutableLiveData<RequestState<T>>()
        if (loading)
            liveData.value = RequestState.loading()
        return liveData
    }

}