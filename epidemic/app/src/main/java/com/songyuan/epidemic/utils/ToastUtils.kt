package com.songyuan.epidemic.utils

import android.widget.Toast
import com.songyuan.epidemic.App

/**
 * Created by niluogege on 2020/2/10.
 */
object ToastUtils {

    fun show(text: String) {
        Toast.makeText(App.context, text, Toast.LENGTH_LONG).show()
    }

}