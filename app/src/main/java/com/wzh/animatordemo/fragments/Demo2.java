package com.wzh.animatordemo.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.view.StepView;


/**
 * 计步器v1.0
 *
 * 动态画圆弧和改变中间的文字
 */

public class Demo2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo2,container,false);

        StepView stepView = view.findViewById(R.id.view);

        // 对弧线的角度做属性动画
        ObjectAnimator stepRadiusAnimator = ObjectAnimator.ofInt(stepView, "stepRadius", 180);
        stepRadiusAnimator.setStartDelay(2000);
        stepRadiusAnimator.setDuration(1500);
        stepRadiusAnimator.start();
        return view;

    }
}
