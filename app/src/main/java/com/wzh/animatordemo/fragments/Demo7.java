package com.wzh.animatordemo.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.view.LoadingView;


/**
 * 网络加载loading
 *
 * 三个小球横向移动加载的动画
 */

public class Demo7 extends Fragment {

    private LoadingView loadingView;
    private ObjectAnimator redStartAnimator;
    private ObjectAnimator redEndAnimator;
    private ObjectAnimator yellowStartAnimator;
    private ObjectAnimator yellowEndAnimator;
    private ObjectAnimator blueStartAnimator;
    private ObjectAnimator blueEndAnimator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo7,container,false);

        loadingView = view.findViewById(R.id.view);
        redStartAnimator = getRedStartAnimator();
        redEndAnimator = getRedEndAnimator();
        yellowStartAnimator = getYellowStartAnimator();
        yellowEndAnimator = getYellowEndAnimator();
        blueStartAnimator = getBlueStartAnimator();
        blueEndAnimator = getBlueEndAnimator();
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(redStartAnimator, redEndAnimator, yellowStartAnimator, yellowEndAnimator, blueStartAnimator, blueEndAnimator);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });
        return view;
    }

    private ObjectAnimator getRedStartAnimator() {
        if (redStartAnimator == null) {
            redStartAnimator = ObjectAnimator.ofFloat(loadingView, "fractionRed", 0, 1);
            redStartAnimator.setStartDelay(1000);
            redStartAnimator.setDuration(600);
        }
        return redStartAnimator;
    }

    private ObjectAnimator getRedEndAnimator() {
        if (redEndAnimator == null) {
            redEndAnimator = ObjectAnimator.ofFloat(loadingView, "fractionRed", 1, 0);
            redEndAnimator.setDuration(600);
        }
        return redEndAnimator;
    }

    private ObjectAnimator getYellowStartAnimator() {
        if (yellowStartAnimator == null) {
            yellowStartAnimator = ObjectAnimator.ofFloat(loadingView, "fractionYellow", 0, 1);
            yellowStartAnimator.setDuration(600);
        }
        return yellowStartAnimator;
    }

    private ObjectAnimator getYellowEndAnimator() {
        if (yellowEndAnimator == null) {
            yellowEndAnimator = ObjectAnimator.ofFloat(loadingView, "fractionYellow", 1, 0);
            yellowEndAnimator.setDuration(600);
        }
        return yellowEndAnimator;
    }

    private ObjectAnimator getBlueStartAnimator() {
        if (blueStartAnimator == null) {
            blueStartAnimator = ObjectAnimator.ofFloat(loadingView, "fractionBlue", 0, 1);
            blueStartAnimator.setDuration(600);
        }
        return blueStartAnimator;
    }

    private ObjectAnimator getBlueEndAnimator() {
        if (blueEndAnimator == null) {
            blueEndAnimator = ObjectAnimator.ofFloat(loadingView, "fractionBlue", 1, 0);
            blueEndAnimator.setDuration(600);
        }
        return blueEndAnimator;
    }
}
