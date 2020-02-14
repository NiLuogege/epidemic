package com.songyuan.epidemic.mvvm.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.niluogege.yunmaiocr.idcard.CameraActivity
import com.songyuan.epidemic.R
import com.songyuan.epidemic.databinding.ActivityLoginBinding
import com.songyuan.epidemic.mvvm.vm.LoginActivityViewModel
import com.songyuan.epidemic.utils.*
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by niluogege on 2020/2/10.
 */
@Route(path = Routes.A_LOGIN)
class LoginActivity : MvvmBaseActivity<ActivityLoginBinding>() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginActivityViewModel() as T
            }
        }).get(LoginActivityViewModel::class.java)
    }


    override fun initContentView(): Int {
        return R.layout.activity_login
    }

    override fun bindViewModels(binding: ActivityLoginBinding) {
        binding.viewModel = viewModel
    }


    @SuppressLint("CheckResult")
    override fun initView(savedInstanceState: Bundle?) {

        val name = SPUtil.get(SPUtil.EPIDEMIC_APP, Constants.SP_USER_NAME)
        if (name != null)
            viewModel.name.set(name as String)

        val pass = SPUtil.get(SPUtil.EPIDEMIC_APP, Constants.SP_PASSWORD)
        if (pass != null)
            viewModel.pass.set(pass as String)

        viewModel.onLoginBtnClicked.observe(this@LoginActivity, Observer {
            handleData(viewModel.login()) {
                UserUtil.setLoginInfo(it)
                ArouterUtils.routerTo(Routes.A_ADDRESS)
                SPUtil.save(SPUtil.EPIDEMIC_APP, Constants.SP_USER_NAME, viewModel.name.get())
                SPUtil.save(SPUtil.EPIDEMIC_APP, Constants.SP_PASSWORD, viewModel.pass.get())
                finish()
            }
        })


        val rxPermissions = RxPermissions(this)
        rxPermissions
            .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe {
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rxPermissions
                .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe {
                }
        }


    }

}