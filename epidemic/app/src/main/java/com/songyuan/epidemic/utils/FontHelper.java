package com.songyuan.epidemic.utils;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 类名称：FontHelper
 * 创建者：Create by liujc
 * 创建时间：Create on 2017/8/14
 * 描述：iconfont工具类
 */
public class FontHelper {
    public static final String DEF_FONT = "iconfont/iconfont.ttf";
    public static final String OLD_FONT = "iconfont/iconfontOld.ttf";
    public static final String TYPE_FACE = "fonts/DINPro-Medium.otf";

    /**
     * 设置icon
     * @param rootView
     */
    public static final void injectFontNew(View rootView) {
        injectFont(rootView, Typeface.createFromAsset(rootView.getContext().getAssets(), DEF_FONT));
    }

    /**
     * 设置三方字体
     * @param textView
     */
    public static final void injectFontTypeFace(TextView textView){
        Typeface textFont = Typeface.createFromAsset(textView.getContext().getAssets(), TYPE_FACE);
        textView.setTypeface(textFont);
    }

    /**
     * 2018.8.30号开始弃用
     *
     * @param rootView
     */
    @Deprecated
    public static final void injectFont(View rootView) {
        injectFont(rootView, Typeface.createFromAsset(rootView.getContext().getAssets(), OLD_FONT));
    }

    public static final void injectFont(TextView textView, String textLong) {
        injectFont(textView, Typeface.createFromAsset(textView.getContext().getAssets(),
                DEF_FONT));
        textView.setText(Character.toString((char) Long.parseLong(textLong, 16)));
    }

    private static void injectFont(View rootView, Typeface typeface) {
        if (rootView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) rootView;
            int childViewCount = viewGroup.getChildCount();
            for (int i = 0; i < childViewCount; i++) {
                injectFont(viewGroup.getChildAt(i), typeface);
            }
        } else if (rootView instanceof TextView) {
            ((TextView) rootView).setTypeface(typeface);
        }
    }
}
