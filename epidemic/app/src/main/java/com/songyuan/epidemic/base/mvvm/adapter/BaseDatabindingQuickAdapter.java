package com.songyuan.epidemic.base.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.songyuan.epidemic.BR;
import com.songyuan.epidemic.R;

import java.util.List;

/**
 * Created by niluogege on 2019/3/29.
 * 通过databinding绑定item
 */

public class BaseDatabindingQuickAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseDatabindingQuickAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        Object tag = helper.itemView.getTag(R.id.binding);
        if (tag != null && tag instanceof ViewDataBinding) {
            ViewDataBinding binding = (ViewDataBinding) tag;
            customConvert(this, binding, helper, item);
            binding.setVariable(BR.item, item);
        }
    }

    @Override
    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResId, parent, false);
        View root = binding.getRoot();
        root.setTag(R.id.binding, binding);
        return super.createBaseViewHolder(root);
    }

    protected void customConvert(BaseQuickAdapter<T,BaseViewHolder> adapter, ViewDataBinding binding, BaseViewHolder helper, T item) {

    }
}
