package com.songyuan.epidemic.mvvm.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.songyuan.epidemic.R
import com.songyuan.epidemic.databinding.ActivityLoginBinding
import com.songyuan.epidemic.mvvm.vm.LoginActivityViewModel
import com.songyuan.epidemic.utils.*

/**
 * Created by niluogege on 2020/2/10.
 */
@Route(path = Routes.A_LOGIN)
class LoginActivity : MvvmBaseActivity<ActivityLoginBinding>() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginActivityViewModel(this@LoginActivity) as T
            }
        }).get(LoginActivityViewModel::class.java)
    }


    override fun initContentView(): Int {
        return R.layout.activity_login
    }

    override fun bindViewModels(binding: ActivityLoginBinding) {
        binding.viewModel = viewModel
    }


    override fun initView(savedInstanceState: Bundle?) {

        viewModel.onLoginBtnClicked.observe(this@LoginActivity, Observer {
            handleData(viewModel.login()) {
                UserUtil.setLoginInfo(it)
                ArouterUtils.routerTo(Routes.A_MAIN)
                finish()
            }
        })


    }

}