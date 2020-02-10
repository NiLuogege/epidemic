package com.songyuan.epidemic.mvvm.model

/**
 * Created by niluogege on 2020/2/10.
 */
class LoginInfo {
    var cpId: String = ""
    var csId: String = ""
    var csURL: String = ""
    override fun toString(): String {
        return "LoginInfo(cpId='$cpId', csId='$csId', csURL='$csURL')"
    }


}