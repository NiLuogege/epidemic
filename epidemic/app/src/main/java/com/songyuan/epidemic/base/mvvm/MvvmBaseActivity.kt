package com.aihuishou.commonlib.base.mvvm

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.songyuan.epidemic.base.mvvm.RequestState
import com.songyuan.epidemic.base.mvvm.viewmodel.TitleLayoutViewModel
import com.songyuan.epidemic.utils.LogUtil
import com.songyuan.epidemic.BR


/**
 * Created by niluogege on 2019/10/9.
 *
 * mvvm 架构 activity 的父类
 */
abstract class MvvmBaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: V

    protected var progress: Dialog? = null

    //loading是否可以点击返回键去掉
    protected var progressCancelable: Boolean = true

    protected var useTitleViewModel = false//默认初始化 titleViewModel, 如果不适用titleviewModel需要置位false

    protected lateinit var titleLayoutViewModel: TitleLayoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initParams(savedInstanceState)

        initDataBinding()

        bindViewModels(binding)

        bindTitleViewModel()

        initView(savedInstanceState)
    }


    /**
     * 初始化View
     */
    protected abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract fun initContentView(): Int

    /**
     * 设置其他的ViewModel,可能不止一个ViewModel
     *
     * @param binding
     */
    protected abstract fun bindViewModels(binding: V)


    /**
     * 初始化参数操作
     *
     * @param savedInstanceState
     */
    protected open fun initParams(savedInstanceState: Bundle?) {

    }

    /**
     * 初始化DataBinding
     */
    private fun initDataBinding() {

        val layoutResID = initContentView()
        if (layoutResID == 0) {
            return
        }

        binding = DataBindingUtil.setContentView<V>(this, layoutResID)
    }


    private fun bindTitleViewModel() {
        if (useTitleViewModel) {
            titleLayoutViewModel = TitleLayoutViewModel(this)
            binding.setVariable(BR.titleViewModel, titleLayoutViewModel)
        }
    }

    /**
     * 设置title
     *
     * @param title
     */
    protected fun setTitle(title: String?) {
        titleLayoutViewModel.titleObserver.set(title)
    }


    /**
     *  handleData(payPageInfo) {}
     *  handleData(payPageInfo,false, success = {})
     *  handleData(payPageInfo,false,error = {},success = {})
     */
    protected fun <T> handleData(liveData: LiveData<RequestState<T>>, showLoading: Boolean = true, error: (error: Throwable?) -> Unit = {}, success: (T) -> Unit) {
        liveData.observe(this, Observer { result ->
            if (result != null) {
                when {
                    result.isLoading() ->
                        if (showLoading) {
                            showProgress()
                        }
                    result.isSuccess() -> {
                        success(result.data!!)
                        dismissProgress()
                    }
                    result.isError() -> {
                        dismissProgress()
                        if (error != null) {
                            error(result.error)
                        }
                    }
                }
            } else {
                LogUtil.e("数据有问题 result==null")
            }
        })
    }

    protected fun showProgress() {
//        try {
//            if (progress == null) {
//                progress = ProgressHelper.createProgress(this)
//                progress?.setCancelable(progressCancelable)
//                progress?.setCanceledOnTouchOutside(false)
//            }
//            if (progress?.isShowing!!) {
//                progress?.dismiss()
//            }
//            progress?.show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    protected fun dismissProgress() {
//        try {
//            if (progress != null) {
//                progress?.dismiss()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }


}