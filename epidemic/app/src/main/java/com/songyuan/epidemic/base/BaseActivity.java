package com.songyuan.epidemic.base;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by niluogege on 2020/2/10.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 隐藏输入框
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (this.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
