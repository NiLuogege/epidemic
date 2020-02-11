package com.niluogege.yunmaiocr.idcard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by RuiFight-3 on 2018/7/23.
 */

public class BaseIdCard extends AppCompatActivity {

    public static final int REQUEST_CODE_RECOG = 113;			//	识别
    /**
     * 识别成功
     */
    public static final int RESULT_RECOG_SUCCESS = 103;

    /**
     * 识别失败
     */
    public static final int RESULT_RECOG_FAILED = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
