package com.songyuan.epidemic.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.songyuan.epidemic.App;

import java.io.File;

/**
 * Created by niluogege on 2020/2/13.
 */
public class AppUtils {

    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            return App.context.getCacheDir().getAbsolutePath();
        }
    }


    public static String getAppSDPath() {
        String sdCardPath = getSDPath();
        return sdCardPath + File.separator + "epidemic";

    }

    public static String getAppImagePath() {
        String path = getAppSDPath() + File.separator + "image";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }


}
