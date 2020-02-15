package com.songyuan.epidemic.mvvm.adapter

import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.songyuan.epidemic.R
import com.songyuan.epidemic.mvvm.model.Address

/**
 * Created by niluogege on 2020/2/15.
 */
class AddressAdapter(layoutId: Int, data: MutableList<Address>) :
    BaseQuickAdapter<Address, BaseViewHolder>(layoutId, data) {
    override fun convert(helper: BaseViewHolder, item: Address?) {
        helper.setText(R.id.csName, item?.csName)
        helper.getView<CheckBox>(R.id.cb).isChecked = item?.selected!!
    }
}