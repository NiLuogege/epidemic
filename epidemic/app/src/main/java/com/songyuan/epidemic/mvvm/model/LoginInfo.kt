package com.songyuan.epidemic.mvvm.model

/**
 * Created by niluogege on 2020/2/10.
 *
 * https://github.com/lhx692135353/ncp-api/blob/master/%E5%89%8D%E7%AB%AF/%E9%87%87%E9%9B%86%E4%BA%BA%E5%91%98%E7%99%BB%E5%BD%95%E6%8E%A5%E5%8F%A3.md
 *
 */
class LoginInfo {
    var cpId: String = "" //录入人员唯一ID
    var csId: String = "" //所属采集点id
    var csURL: String = "" //采集点二维码
    override fun toString(): String {
        return "LoginInfo(cpId='$cpId', csId='$csId', csURL='$csURL')"
    }


}