package com.songyuan.epidemic.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.songyuan.epidemic.R
import com.songyuan.epidemic.databinding.ActivityMainBinding
import com.songyuan.epidemic.mvvm.vm.LoginActivityViewModel
import com.songyuan.epidemic.mvvm.vm.MainActivityViewModel
import com.songyuan.epidemic.utils.Routes
import com.songyuan.epidemic.utils.SPUtil
import com.songyuan.epidemic.utils.UserUtil

@Route(path = Routes.A_MAIN)
class MainActivity : MvvmBaseActivity<ActivityMainBinding>() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModel() as T
            }
        }).get(MainActivityViewModel::class.java)
    }


    override fun initContentView(): Int {
        return R.layout.activity_main
    }

    override fun bindViewModels(binding: ActivityMainBinding) {
        binding.viewModel = viewModel
    }


    override fun initView(savedInstanceState: Bundle?) {

        viewModel.run {
            qrImage.set(UserUtil.qrUrl)
        }
    }
}
