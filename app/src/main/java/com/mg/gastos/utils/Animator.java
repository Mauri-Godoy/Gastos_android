package com.mg.gastos.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.mg.gastos.R;

public class Animator {

    public static void shake(View v){
        Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
        v.startAnimation(shake);
    }
}
