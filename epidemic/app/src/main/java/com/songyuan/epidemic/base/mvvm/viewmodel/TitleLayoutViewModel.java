package com.songyuan.epidemic.base.mvvm.viewmodel;

import android.app.Activity;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.songyuan.epidemic.R;


/**
 * Created by LuoChen on 2017/9/29.
 */

public class TitleLayoutViewModel extends BaseObservable {

    private final Activity activity;

    public ObservableField<String> titleObserver = new ObservableField<>();
    public ObservableInt titleColor = new ObservableInt(1);
    public ObservableField<String> rightText = new ObservableField<>("搜索");
    public ObservableInt showArrow_nomal = new ObservableInt(View.GONE);
    public ObservableInt showArrow_id = new ObservableInt(View.GONE);
    public ObservableInt isShowRightText = new ObservableInt(View.GONE);
    public ObservableInt isShowLine = new ObservableInt(View.VISIBLE);
    public ObservableInt bgColor = new ObservableInt(1);
    public ObservableInt backArrowColor = new ObservableInt(1);

    public TitleLayoutViewModel(Activity activity) {
        this.activity = activity;
        bgColor.set(activity.getResources().getColor(R.color.white));
        titleColor.set(activity.getResources().getColor(R.color.text_default_2));
        backArrowColor.set(activity.getResources().getColor(R.color.text_default_2));
    }

    /**
     * @param view
     */
    public void onBackImageClick(View view) {
        activity.onBackPressed();
    }


}
