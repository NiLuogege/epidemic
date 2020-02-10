package com.songyuan.epidemic.base.mvvm.databanding.viewadapter.view;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;

import com.jakewharton.rxbinding3.view.RxView;
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand;

import io.reactivex.functions.Consumer;
import kotlin.Unit;


/**
 * Created by goldze on 2017/6/16.
 */

public class ViewAdapter {
    /**
     * view的onLongClick事件绑定
     */
    @BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    public static void onLongClickCommand(View view, final BindingCommand clickCommand) {
        RxView.longClicks(view)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {
                        if (clickCommand != null) {
                            clickCommand.execute();
                        }
                    }
                });
    }

    /**
     * 回调控件本身
     *
     * @param currentView
     * @param bindingCommand
     */
    @BindingAdapter(value = {"currentView"}, requireAll = false)
    public static void replyCurrentView(View currentView, BindingCommand bindingCommand) {
        if (bindingCommand != null) {
            bindingCommand.execute(currentView);
        }
    }

    /**
     * view是否需要获取焦点
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    /**
     * view的焦点发生变化的事件绑定
     */
    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }


    /**
     * view设置颜色圆角
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @BindingAdapter(value = {"roundRadius"})
    public static void setRoundBackground(View view, float radius) {
        if (view != null) {
            Drawable background = view.getBackground();
            if (background instanceof ColorDrawable) {
                ColorDrawable cd = (ColorDrawable) background;
                float[] radiusArray = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
                RoundRectShape roundRect = new RoundRectShape(radiusArray, null, null);
                ShapeDrawable bgDrawable = new ShapeDrawable(roundRect);
                bgDrawable.getPaint().setColor(cd.getColor());
                view.setBackground(bgDrawable);
            } else {
                throw new RuntimeException("xml中 view 需要设置background ,并且需要引用 color");
            }
        }
    }

}
