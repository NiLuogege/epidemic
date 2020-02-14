package com.songyuan.epidemic.mvvm.view

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.songyuan.epidemic.R
import com.songyuan.epidemic.databinding.ActivityAddressBinding
import com.songyuan.epidemic.mvvm.vm.AddressActivityViewModel
import com.songyuan.epidemic.utils.LogUtil
import com.songyuan.epidemic.utils.Routes

/**
 * Created by niluogege on 2020/2/14.
 */
@Route(path = Routes.A_ADDRESS)
class AddressActivity : MvvmBaseActivity<ActivityAddressBinding>() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return AddressActivityViewModel() as T
            }
        }).get(AddressActivityViewModel::class.java)
    }

    override fun initContentView(): Int {
        return R.layout.activity_address
    }

    override fun bindViewModels(binding: ActivityAddressBinding) {
        binding.viewModel = viewModel
    }

    override fun initView(savedInstanceState: Bundle?) {
        handleData(viewModel.getAddress()) {
            LogUtil.e(it.toString())
        }
    }
}