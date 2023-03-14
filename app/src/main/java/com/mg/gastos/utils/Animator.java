package com.mg.gastos.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mg.gastos.R;

public class Animator {

    public static void shake(View v) {
        Animation shake = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake);
        v.startAnimation(shake);
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
                    .replace(fragmentId, fragment)
                    .addToBackStack("null")
                    .commit();
        }
    }
}
