package com.wzh.animatordemo.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.view.FlipboardView;


/**
 * 仿写FlipboardView
 */

public class Demo1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo1,container,false);

        FlipboardView flipboardView = view.findViewById(R.id.view);

        // 底部动画：向上翻45度
        ObjectAnimator bottomFlipAnim = ObjectAnimator.ofFloat(flipboardView, "bottomFlip", 45);
        bottomFlipAnim.setDuration(1500);

        // 旋转画布，做整体动画
        // 旋转270度后，原来的下半部分变成了左半部分，上半部分变成了右半部分
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(flipboardView, "flipRotate", 270);
        rotateAnim.setDuration(1500);

        // 顶部动画：右半部分翻45度
        ObjectAnimator topFlipAnim = ObjectAnimator.ofFloat(flipboardView, "topFlip", -45);
        topFlipAnim.setDuration(1500);

        ObjectAnimator bottomFlipAnim2 = ObjectAnimator.ofFloat(flipboardView, "bottomFlip", 0);
        bottomFlipAnim.setDuration(1500);

        ObjectAnimator topFlipAnim2 = ObjectAnimator.ofFloat(flipboardView, "topFlip", 0);
        topFlipAnim.setDuration(1500);

        // playSequentially 依次执行
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnim, rotateAnim, topFlipAnim, bottomFlipAnim2, topFlipAnim2);
        animatorSet.setStartDelay(1500);
        animatorSet.start();

        return view;
    }
}
