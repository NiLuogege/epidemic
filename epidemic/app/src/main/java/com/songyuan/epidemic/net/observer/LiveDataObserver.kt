package com.songyuan.epidemic.net.observer

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.songyuan.epidemic.App
import com.songyuan.epidemic.base.mvvm.RequestState

/**
 * Created by niluogege on 2019/10/11.
 */
class LiveDataObserver<T>(var liveData: MutableLiveData<RequestState<T>>) : DefaultObserver<T>() {
    override fun onsuccess(data: T) {
        liveData.value = RequestState.success(data)
    }

    override fun onFail(error: Throwable?) {
        liveData.value = RequestState.error(error)
        if (error != null)
            Toast.makeText(App.context, error.message, Toast.LENGTH_LONG).show()
    }

}