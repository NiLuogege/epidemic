package com.songyuan.epidemic.base.mvvm.databanding.viewadapter;


import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding3.view.RxView;
import com.songyuan.epidemic.R;
import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import kotlin.Unit;


public class BindingUtils {
    //防重复点击间隔(毫秒)
    public static final long CLICK_INTERVAL = 1000;


    @BindingAdapter({"imageUrlNoPlace"})
    public static void imageUrlNoPlace(ImageView imageView, String url) {
        if (URLUtil.isNetworkUrl(url) && imageView != null) {
            if (url.endsWith(".gif")) {
                Glide.with(imageView.getContext())
                        .load(url)
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
            } else {
                Glide.with(imageView.getContext())
                        .load(url)
                        .into(imageView);
            }
        }
    }


    @BindingAdapter({"imageUrl"})
    public static void nimageLoader(ImageView imageView, String url) {
        if (URLUtil.isNetworkUrl(url)) {
            if (url.endsWith(".gif")) {
                Glide.with(imageView.getContext())
                        .load(url)
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.mipmap.placeholder)
                        .into(imageView);
            } else {

                Glide.with(imageView.getContext())
                        .load(url)
                        .placeholder(R.mipmap.placeholder)
                        .into(imageView);
            }
        }

    }

    @BindingAdapter({"imageUrl"})
    public static void nimageLoader(ImageView imageView, int drawableId) {
        if (drawableId > 0) {
            Glide.with(imageView.getContext())
                    .load(drawableId)
                    .placeholder(R.mipmap.placeholder)
                    .into(imageView);
        }

    }

    /**
     * 普通防重复点击
     * <p>
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击  false是开启,true不开启,默认是开启
     */
    @BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
    public static void onClickCommand(View view, final BindingCommand clickCommand, final boolean isThrottleFirst) {
        if (isThrottleFirst) {
            RxView.clicks(view)
                    .subscribe(new Consumer<Unit>() {
                        @Override
                        public void accept(Unit aVoid) {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        } else {
            RxView.clicks(view)
                    .throttleFirst(CLICK_INTERVAL, TimeUnit.MILLISECONDS)//500毫秒钟内只允许点击1次
                    .subscribe(new Consumer<Unit>() {
                        @Override
                        public void accept(Unit aVoid) {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        }
    }

    /**
     * 防重复点击 并传递view
     * <p>
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击  false是开启,true不开启,默认是开启
     */
    @BindingAdapter(value = {"onClickCommand1", "isThrottleFirst"}, requireAll = false)
    public static void onClickCommand1(View view, final BindingCommand clickCommand, final boolean isThrottleFirst) {
        if (isThrottleFirst) {
            RxView.clicks(view)
                    .subscribe(new Consumer<Unit>() {
                        @Override
                        public void accept(Unit aVoid) {
                            if (clickCommand != null) {
                                clickCommand.execute(view);
                            }
                        }
                    });
        } else {
            RxView.clicks(view)
                    .throttleFirst(CLICK_INTERVAL, TimeUnit.MILLISECONDS)//500毫秒钟内只允许点击1次
                    .subscribe(new Consumer<Unit>() {
                        @Override
                        public void accept(Unit aVoid) {
                            if (clickCommand != null) {
                                clickCommand.execute(view);
                            }
                        }
                    });
        }
    }


    /**
     * 防重复点击 并自定义时间
     * <p>
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击  false是开启,true不开启,默认是开启
     * intervalTime 延时时间
     */
    @BindingAdapter(value = {"onClickCommand2", "isThrottleFirst", "intervalTime"}, requireAll = false)
    public static void onClickCommand2(View view, final BindingCommand clickCommand, final boolean isThrottleFirst, int intervalTime) {
        if (isThrottleFirst) {
            RxView.clicks(view)
                    .subscribe(new Consumer<Unit>() {
                        @Override
                        public void accept(Unit aVoid) {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        } else {
            RxView.clicks(view)
                    .throttleFirst(intervalTime, TimeUnit.MILLISECONDS)//500毫秒钟内只允许点击1次
                    .subscribe(new Consumer<Unit>() {
                        @Override
                        public void accept(Unit aVoid) {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        }
    }


    /**
     * 防重复点击 并传递view
     * <p>
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击  false是开启,true不开启,默认是开启
     */
    @BindingAdapter(value = {"onLiveDataClick", "isThrottleFirst"}, requireAll = false)
    public static void onLiveDataClick(View view, final MutableLiveData<View> liveData, final boolean isThrottleFirst) {
        if (isThrottleFirst) {
            RxView.clicks(view)
                    .subscribe(aVoid -> {
                        if (liveData != null) {
                            liveData.setValue(view);
                        }
                    });
        } else {
            RxView.clicks(view)
                    .throttleFirst(CLICK_INTERVAL, TimeUnit.MILLISECONDS)//500毫秒钟内只允许点击1次
                    .subscribe(aVoid -> {
                        if (liveData != null) {
                            liveData.setValue(view);
                        }
                    });
        }
    }


    @BindingAdapter("layout_marginTop")
    public static void setTopMargin(View view, int topMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, topMargin,
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

}
