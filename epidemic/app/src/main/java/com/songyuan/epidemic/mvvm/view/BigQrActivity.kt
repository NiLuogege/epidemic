package com.songyuan.epidemic.mvvm.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.songyuan.epidemic.R
import com.songyuan.epidemic.databinding.ActivityBigQrBinding
import com.songyuan.epidemic.mvvm.vm.BigQrActivityViewModel
import com.songyuan.epidemic.mvvm.vm.MainActivityViewModel
import com.songyuan.epidemic.utils.Routes
import com.songyuan.epidemic.utils.UserUtil

/**
 * Created by niluogege on 2020/2/11.
 */
@Route(path = Routes.A_BIG_QR)
class BigQrActivity : MvvmBaseActivity<ActivityBigQrBinding>() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return BigQrActivityViewModel() as T
            }
        }).get(BigQrActivityViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.activity_big_qr
    }

    override fun bindViewModels(binding: ActivityBigQrBinding) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.run {
            qrImage.set(UserUtil.qrUrl)
            onLoginBtnClicked.observe(this@BigQrActivity, Observer {
                finish()
            })
        }
    }
}