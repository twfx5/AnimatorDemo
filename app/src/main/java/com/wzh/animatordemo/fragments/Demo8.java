package com.wzh.animatordemo.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.view.ColorTrackTextView;


/**
 * 字体动态改变颜色
 */

public class Demo8 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo8,container,false);

        ColorTrackTextView trackTextView = view.findViewById(R.id.view);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(trackTextView, "fraction", 1.1f);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
        return view;
    }

}
