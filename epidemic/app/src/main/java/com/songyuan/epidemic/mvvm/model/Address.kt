package com.songyuan.epidemic.mvvm.model

/**
 * Created by niluogege on 2020/2/10.
 *
 * https://gitee.com/BaLangZiTuanDui/doc-api/blob/master/%E5%89%8D%E7%AB%AF/%E6%9F%A5%E5%AF%BB%E9%87%87%E9%9B%86%E7%82%B9%E6%A0%B9%E6%8D%AE%E9%87%87%E9%9B%86%E4%BA%BAid.md
 *
 */
class Address {
    var csId: String? = "" //所属采集点id
    var csURL: String? = "" //采集点二维码
    var csName: String? = "" //采集点名称
    var selected: Boolean = false //是否被选中
    override fun toString(): String {
        return "Address(csId='$csId', csURL='$csURL', csName='$csName')"
    }


}