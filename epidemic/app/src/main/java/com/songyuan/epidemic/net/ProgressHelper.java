package com.songyuan.epidemic.net;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.songyuan.epidemic.App;
import com.songyuan.epidemic.R;


/**
 * Created by niluogege on 2018/8/24.
 */

public class ProgressHelper {


    private static Dialog progress;

    public static Dialog createProgress(Activity activity) {
        return createLoadingDialog(activity);
    }

    public static Dialog createLoadingDialog(Context context) {

        LayoutInflater inflater = null;

        if (context != null) {
            inflater = LayoutInflater.from(context);
        } else {
            inflater = LayoutInflater.from(App.context);
        }

        View v = inflater.inflate(R.layout.dialog_loading_layout, null);
        FrameLayout layout = (FrameLayout) v.findViewById(R.id.dialog_view);
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return loadingDialog;
    }

}
