package com.songyuan.epidemic.base.mvvm.databanding.viewadapter.image;


import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;


/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext()).load(url).placeholder(placeholderRes)
                    .into(imageView);
        }
    }

    @BindingAdapter(value = {"resId", "placeholderRes"}, requireAll = false)
    public static void setImageRes(ImageView imageView, int resId, int placeholderRes) {
        if (resId > 0) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(resId)
                    .placeholder(placeholderRes)
                    .into(imageView);
        }
    }
}

