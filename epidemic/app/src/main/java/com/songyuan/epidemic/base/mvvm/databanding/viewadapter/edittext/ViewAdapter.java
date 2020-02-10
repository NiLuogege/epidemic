package com.songyuan.epidemic.base.mvvm.databanding.viewadapter.edittext;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

/**
 * Created by goldze on 2017/6/16.
 */

public class ViewAdapter {
    /**
     * EditText重新获取焦点的事件绑定
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            editText.setFocusableInTouchMode(true);
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        } else {
            editText.setEnabled(false);
            editText.setEnabled(true);
        }
    }
}
