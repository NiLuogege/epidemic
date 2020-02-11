package com.songyuan.epidemic.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel

/**
 * Created by niluogege on 2020/2/11.
 */
class BigQrActivityViewModel:MvvmBaseViewModel() {

    val qrImage = ObservableField<String>("")

    val onLoginBtnClicked = MutableLiveData<View>()
}