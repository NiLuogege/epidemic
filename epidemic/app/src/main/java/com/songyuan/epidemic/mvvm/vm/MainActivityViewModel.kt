package com.songyuan.epidemic.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel
import com.rxjava.rxlife.lifeOnMain
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand
import com.songyuan.epidemic.mvvm.model.CheckIdCardInfo
import com.songyuan.epidemic.mvvm.model.LoginInfo
import com.songyuan.epidemic.net.observer.DefaultObserver
import com.songyuan.epidemic.net.observer.LiveDataObserver
import com.songyuan.epidemic.net.observer.SimpleObserver
import com.songyuan.epidemic.utils.*
import com.songyuan.epidemic.utils.regex.RegexUtils
import io.reactivex.functions.Consumer
import rxhttp.wrapper.param.RxHttp

/**
 * Created by niluogege on 2020/2/11.
 */
class MainActivityViewModel : MvvmBaseViewModel() {

    val idCard = ObservableField<String>("")
    val qrImage = ObservableField<String>("")


    val onOkBtnClicked = BindingCommand<View>(Consumer {
        if (RegexUtils.isIDCard18(idCard.get())) {


            RxHttp.get("find-by-id-card")
                .add("IDCard", idCard.get())
                .asBaseResponse(CheckIdCardInfo::class.java)
                .lifeOnMain(this)
                .subscribe(object : SimpleObserver<CheckIdCardInfo>() {
                    override fun onsuccess(data: CheckIdCardInfo?) {
                        if (data != null) {
                            ArouterUtils.getRouter()
                                .build(Routes.A_BROWSER)
                                .withString(
                                    "url",
                                    "http://yqfk.bdcarlife.com/info/firstinputforapp?cpId=${UserUtil.userId}&status=${data.status}"
                                )
                                .withString("idCardNum", idCard.get())
                                .navigation()
                        }
                    }
                })


        } else {
            ToastUtils.show("身份证有误，请重新输入")
        }
    })

    val onQrClicked = BindingCommand<View>(Consumer {
        ArouterUtils.routerTo(Routes.A_BIG_QR)
    })
}