package com.songyuan.epidemic.base.mvvm.databanding.viewadapter.swiperefresh;


import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.songyuan.epidemic.base.mvvm.databanding.command.BindingCommand;

/**
 * Created by goldze on 2017/6/18.
 */
public class ViewAdapter {
    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }

}
