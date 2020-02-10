package com.aihuishou.commonlib.base.mvvm

import androidx.lifecycle.ViewModel
import com.rxjava.rxlife.ScopeViewModel
import com.songyuan.epidemic.App


/**
 * Created by niluogege on 2019/10/9.
 */
open class MvvmBaseViewModel : ScopeViewModel(App.context) {
}