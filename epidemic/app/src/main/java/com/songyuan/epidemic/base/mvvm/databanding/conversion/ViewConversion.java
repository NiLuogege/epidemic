package com.songyuan.epidemic.base.mvvm.databanding.conversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;

import androidx.databinding.BindingConversion;

import com.songyuan.epidemic.App;


/**
 * Created by LuoChen on 2017/11/15.
 */

public class ViewConversion {

    /**
     * int color 转换为 ColorDrawable
     *
     * @param color
     * @return
     */
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }


    /**
     * 暂时不支持mipmap 但是drawable可以用
     */
    @BindingConversion
    public static Bitmap convertIntToDrawable(int drawableResouse) {
        return BitmapFactory.decodeResource(App.context.getResources(), drawableResouse);
    }

}
