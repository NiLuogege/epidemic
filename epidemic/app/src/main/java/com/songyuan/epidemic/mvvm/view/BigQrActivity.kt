package com.songyuan.epidemic.mvvm.view

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aihuishou.commonlib.base.mvvm.MvvmBaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.songyuan.epidemic.R
import com.songyuan.epidemic.databinding.ActivityBigQrBinding
import com.songyuan.epidemic.mvvm.vm.BigQrActivityViewModel
import com.songyuan.epidemic.mvvm.vm.MainActivityViewModel
import com.songyuan.epidemic.utils.*
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

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
        binding.viewModel = viewModel
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.run {
            qrImage.set(UserUtil.qrUrl)
            csName.set("采集地点：${UserUtil.csName}")
            onLoginBtnClicked.observe(this@BigQrActivity, Observer {
                saveImage()
            })
        }
    }

    @SuppressLint("CheckResult")
    private fun saveImage() {
        RxPermissions(this).request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe { granted ->
            if (granted) {

                LogUtil.e("保存图片")

                Schedulers.newThread().createWorker().schedule {
                    val bitmap = Glide.with(this@BigQrActivity)
                        .load(UserUtil.qrUrl)
                        .asBitmap()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get()

                    if (bitmap != null) {
//                        // 在这里执行图片保存方法
//                        val watermarkBitmap = BitmapUtil.addTextWatermark(
//                            bitmap,
//                            UserUtil.csName,
//                            14,
//                            resources.getColor(R.color.text_default),
//                            20f,
//                            18f,
//                            true
//                        )
                        BitmapUtil.saveImageToGallery(this@BigQrActivity, bitmap)
                        runOnUiThread {
                            ToastUtils.show("保存成功")
                        }
                    } else {
                        runOnUiThread {
                            ToastUtils.show("保存失败")
                        }
                    }
                }

            } else {
                ToastUtils.show("请同意权限")
            }
        }
    }
}