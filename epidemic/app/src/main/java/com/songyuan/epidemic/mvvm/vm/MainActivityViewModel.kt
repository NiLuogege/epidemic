package com.songyuan.epidemic.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand
import com.songyuan.epidemic.utils.*
import com.songyuan.epidemic.utils.regex.RegexUtils
import io.reactivex.functions.Consumer

/**
 * Created by niluogege on 2020/2/11.
 */
class MainActivityViewModel : MvvmBaseViewModel() {

    val idCard = ObservableField<String>("")
    val qrImage = ObservableField<String>("")


    val onOkBtnClicked = BindingCommand<View>(Consumer {
        if (RegexUtils.isIDCard18(idCard.get())) {
            ArouterUtils.getRouter()
                .build(Routes.A_BROWSER)
//                .withString("url", "file:///android_asset/demo.html")
//                .withString("url", "https://www.baidu.com/")
                .withString("url", "http://yqfk.bdcarlife.com/info/firstinputforapp?cpId=" + UserUtil.userId)
                .withString("idCardNum", idCard.get())
                .navigation()
        } else {
            ToastUtils.show("身份证有误，请重新输入")
        }
    })

    val onQrClicked = BindingCommand<View>(Consumer {
        ArouterUtils.routerTo(Routes.A_BIG_QR)
    })
}