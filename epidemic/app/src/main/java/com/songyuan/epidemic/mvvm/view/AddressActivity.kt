package com.songyuan.epidemic.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.songyuan.epidemic.R
import com.songyuan.epidemic.base.mvvm.adapter.BaseDatabindingQuickAdapter
import com.songyuan.epidemic.databinding.ActivityAddressBinding
import com.songyuan.epidemic.mvvm.model.Address
import com.songyuan.epidemic.mvvm.vm.AddressActivityViewModel
import com.songyuan.epidemic.utils.ArouterUtils
import com.songyuan.epidemic.utils.Routes
import com.songyuan.epidemic.utils.UserUtil

/**
 * Created by niluogege on 2020/2/14.
 */
@Route(path = Routes.A_ADDRESS)
class AddressActivity : MvvmBaseActivity<ActivityAddressBinding>() {
    lateinit var quickAdapter: BaseDatabindingQuickAdapter<Address>

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
        initRv()
        viewModel.onOkBtnClick.observe(this, Observer {
            val data = quickAdapter.data
            for (address in data) {
                if (address.selected) {
                    UserUtil.setAddress(address)
                    break
                }
            }

            ArouterUtils.routerTo(Routes.A_MAIN)
        })

    }

    fun initRv() {
        binding.rv.layoutManager = LinearLayoutManager(this)

        quickAdapter =
            object :
                BaseDatabindingQuickAdapter<Address>(R.layout.item_address, ArrayList()) {}.apply {
                setOnItemClickListener { adapter, view, position ->
                    val addressList = adapter.data as List<Address>
                    val address = addressList[position]
                    if (address.selected) {
                        address.selected = false
                    } else {
                        for (add in addressList) {
                            add.selected = false
                        }
                        address.selected = true
                    }
                    adapter.notifyDataSetChanged()
                }
            }



        binding.rv.adapter = quickAdapter

        handleData(viewModel.getAddress()) {
            quickAdapter.setNewData(it as MutableList<Address>)
        }
    }


}