package com.songyuan.epidemic.base.mvvm.databanding.viewadapter.textview;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.songyuan.epidemic.utils.FontHelper;


/**
 * Created by LuoChen on 2017/11/23.
 */

public class ViewAdapter {

    /**
     * 设置字体icon  只需要给TextView设置  injectFont 不用设置Text属性,我们会在这里设置
     *
     * @param textView
     * @param text
     */
    @BindingAdapter("injectFont")
    public static void injectFont(TextView textView, String text) {
        if (text != null && !TextUtils.isEmpty(text.trim())) {
            FontHelper.injectFont(textView);
            textView.setText(text);
        }
    }

    /**
     * 设置字体icon  只需要给TextView设置  injectFont 不用设置Text属性,我们会在这里设置
     *
     * @param textView
     * @param text
     */
    @BindingAdapter("injectFontNew")
    public static void injectFontNew(TextView textView, String text) {
        if (text != null && !TextUtils.isEmpty(text.trim())) {
            FontHelper.injectFontNew(textView);
            textView.setText(text);
        }
    }

    /**
     * 设置html
     *
     * @param textView
     * @param text
     */
    @BindingAdapter("setHtml")
    public static void setHtml(TextView textView, CharSequence text) {
        if (text != null && !TextUtils.isEmpty(text.toString().trim())) {
            textView.setText(text);
        }
    }

    /**
     * 设置html
     *
     * @param textView
     * @param text
     */
    @BindingAdapter("setHtml2")
    public static void setHtml2(TextView textView, String text) {
        if (text != null && !TextUtils.isEmpty(text.trim())) {
            textView.setText(Html.fromHtml(text));
        }
    }


    /**
     * 设置为过期样式
     */
    @BindingAdapter("centerLine")
    public static void centerLine(TextView textView, boolean b) {
        if (b) {
            TextPaint paint = textView.getPaint();
            paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
    }

    /**
     * 文字颜色
     */
    @BindingAdapter("tColor")
    public static void tColor(TextView textView, int colorId) {
        if (textView != null && colorId > 0) {
            textView.setTextColor(textView.getResources().getColor(colorId));
        }
    }


    @BindingAdapter(value = {"textDrawableUrl", "textDrawableDirection", "drawableSize"}, requireAll = false)
    public static void textDrawable(TextView textView, String url, String direction, float drawableSize) {
        int size = (int) drawableSize;
        if (URLUtil.isNetworkUrl(url)) {
            switch (direction) {
                case "left":
                    Glide.with(textView.getContext())
                            .load(url)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    if (size <= 0) {
                                        textView.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                                    } else {
                                        resource.setBounds(0, 0, size, size);
                                        textView.setCompoundDrawables(resource, null, null, null);
                                    }
                                }
                            });

                    break;

                case "top":
                    Glide.with(textView.getContext())
                            .load(url)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    if (size <= 0) {
                                        textView.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
                                    } else {
                                        resource.setBounds(0, 0, size, size);
                                        textView.setCompoundDrawables(null, resource, null, null);
                                    }
                                }
                            });
                    break;

                case "right":
                    Glide.with(textView.getContext())
                            .load(url)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    if (size <= 0) {
                                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
                                    } else {
                                        resource.setBounds(0, 0, size, size);
                                        textView.setCompoundDrawables(null, null, resource, null);
                                    }
                                }
                            });
                    break;

                case "bottom":
                    Glide.with(textView.getContext())
                            .load(url)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    if (size <= 0) {
                                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
                                    } else {
                                        resource.setBounds(0, 0, size, size);
                                        textView.setCompoundDrawables(null, null, null, resource);
                                    }
                                }
                            });
                    break;
                default:

                    break;
            }
        }
    }


    @BindingAdapter(value = {"textDrawableId", "textDrawableDirection", "drawableSize"}, requireAll = false)
    public static void textDrawable2(TextView textView, Drawable resource, String direction, float drawableSize) {
        int size = (int) drawableSize;
        if (resource != null) {
            switch (direction) {
                case "left":
                    if (size <= 0) {
                        textView.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                    } else {
                        resource.setBounds(0, 0, size, size);
                        textView.setCompoundDrawables(resource, null, null, null);
                    }

                    break;

                case "top":
                    if (size <= 0) {
                        textView.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
                    } else {
                        resource.setBounds(0, 0, size, size);
                        textView.setCompoundDrawables(null, resource, null, null);
                    }
                    break;

                case "right":
                    if (size <= 0) {
                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, resource, null);
                    } else {
                        resource.setBounds(0, 0, size, size);
                        textView.setCompoundDrawables(null, null, resource, null);
                    }
                    break;

                case "bottom":
                    if (size <= 0) {
                        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, resource);
                    } else {
                        resource.setBounds(0, 0, size, size);
                        textView.setCompoundDrawables(null, null, null, resource);
                    }
                    break;
                default:

                    break;
            }
        }
    }
}
