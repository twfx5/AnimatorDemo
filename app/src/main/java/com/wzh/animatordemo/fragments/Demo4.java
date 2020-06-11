package com.wzh.animatordemo.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.view.MoveCircleView;

/**
 * 计步器v2.0
 *
 */
public class Demo4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo4,container,false);

        MoveCircleView moveCircleView = view.findViewById(R.id.view);

        ObjectAnimator sweepCircleAnim = ObjectAnimator.ofInt(moveCircleView, "sweepAngle", 360);
        sweepCircleAnim.setStartDelay(2000);
        sweepCircleAnim.setDuration(2500);
        sweepCircleAnim.start();
        return view;

    }
}
