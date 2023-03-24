package com.mg.gastos.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mg.gastos.R;

public class Animator {

    public static void alpha(View v) {
        Animation alpha = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
        v.startAnimation(alpha);
    }

    public static void scale(View v) {
        Animation scale = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
        v.startAnimation(scale);
    }

    public static void show(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
            view.animate()
                    .alpha(0)
                    .setDuration(500)
                    .alpha(1f)
                    .setListener(null);
        }
    }

    public static void transition(FragmentManager fragmentManager, int fragmentId, Fragment fragment) {

        Fragment currentFragment = fragmentManager.findFragmentById(fragmentId);

        if (currentFragment == null || !currentFragment.getClass().toString().equals(fragment.getClass().toString())) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.animator.enter_from_right,  // enter
                            R.animator.exit_to_left,  // exit
                            R.animator.enter_from_right,   // popEnter
                            R.animator.exit_to_left  // popExit
                    )
                    .replace(fragmentId, fragment).addToBackStack(null)
                    .commit();
        }
    }
}
