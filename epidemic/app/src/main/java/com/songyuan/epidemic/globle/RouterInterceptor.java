package com.songyuan.epidemic.globle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.songyuan.epidemic.utils.ArouterUtils;
import com.songyuan.epidemic.utils.LogUtil;
import com.songyuan.epidemic.utils.Routes;
import com.songyuan.epidemic.utils.UserUtil;

/**
 * Created by niluogege on 2018/5/16.
 */
@Interceptor(priority = 1)
public class RouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard != null) {
            LogUtil.e(postcard.getPath());
            switch (postcard.getPath()) {
                case Routes.A_MAIN://首页
                    if (UserUtil.isUserLogin()) {
                        callback.onContinue(postcard);
                    } else {

                        ArouterUtils.INSTANCE.routerTo(Routes.A_LOGIN);
                        callback.onInterrupt(new RuntimeException("没有登陆"));//中断路由操作
                    }
                    break;


                default:
                    callback.onContinue(postcard);
                    break;
            }
        }
    }

    @Override
    public void init(Context context) {
        LogUtil.e("init");
    }
}
