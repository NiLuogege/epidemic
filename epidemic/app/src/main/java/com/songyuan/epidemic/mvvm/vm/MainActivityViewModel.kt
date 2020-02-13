package com.songyuan.epidemic.mvvm.vm

import android.view.View
import androidx.databinding.ObservableField
import com.aihuishou.commonlib.base.mvvm.MvvmBaseViewModel
import com.rxjava.rxlife.lifeOnMain
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand
import com.songyuan.epidemic.mvvm.model.CheckIdCardInfo
import com.songyuan.epidemic.net.observer.SimpleObserver
import com.songyuan.epidemic.utils.*
import com.songyuan.epidemic.utils.regex.RegexUtils
import io.reactivex.functions.Consumer
import rxhttp.wrapper.param.RxHttp

/**
 * Created by niluogege on 2020/2/11.
 */
class MainActivityViewModel : MvvmBaseViewModel() {

    val phone = ObservableField<String>("")
    val qrImage = ObservableField<String>("")


    val onOkBtnClicked = BindingCommand<View>(Consumer {
        if (StringUtils.isNotEmpty(phone.get()) && phone.get()?.length == 11) {


            RxHttp.get("find-by-phone")
                .add("phone", phone.get())
                .asBaseResponse(CheckIdCardInfo::class.java)
                .lifeOnMain(this)
                .subscribe(object : SimpleObserver<CheckIdCardInfo>() {
                    override fun onsuccess(data: CheckIdCardInfo?) {
                        if (data != null) {

                            val url = if (data.status == "1") {
                                "http://yqfk.bdcarlife.com/info/firstinputforapp?cpId=${UserUtil.userId}&csId=${UserUtil.csId}&status=${data.status}&uid=${data.uid}&csName=${UserUtil.csName}"
                            } else {
                                "http://yqfk.bdcarlife.com/info/alreadyinputforapp?cpId=${UserUtil.userId}&csId=${UserUtil.csId}&status=${data.status}&uid=${data.uid}&csName=${UserUtil.csName}"
                            }

                            ArouterUtils.getRouter()
                                .build(Routes.A_BROWSER)
//                                .withString("url", "file:///android_asset/demo.html")
                                .withString("url", url)
                                .withString("idCardNum", phone.get())
                                .navigation()
                        }
                    }
                })


        } else {
            ToastUtils.show("电话号码有误，请重新输入!")
        }
    })

    val onQrClicked = BindingCommand<View>(Consumer {
        ArouterUtils.routerTo(Routes.A_BIG_QR)
    })
}