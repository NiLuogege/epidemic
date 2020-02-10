package com.songyuan.epidemic.mvvm.vm

import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand
import com.songyuan.epidemic.utils.LogUtil
import io.reactivex.functions.Consumer

/**
 * Created by niluogege on 2020/2/10.
 */
class LoginActivityViewModel : MvvmBaseViewModel() {

    val name = ObservableField<String>("")
    val pass = ObservableField<String>("")


    val onLoginBtnClicked = BindingCommand<View>(Consumer {
        LogUtil.e("hhhh")
    })
}