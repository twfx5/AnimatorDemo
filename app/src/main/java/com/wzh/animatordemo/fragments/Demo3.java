package com.wzh.animatordemo.fragments;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.Utils;
import com.wzh.animatordemo.view.PointView;

/**
 * 自定义Point的TypeEvaluator
 *
 * TypeEvaluator 估计值：用来改变动画的属性值
 */
public class Demo3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo3,container,false);

        PointView pointView = view.findViewById(R.id.view);
        // 目标targetPoint
        Point targetPoint = new Point((int) Utils.dp2px(300), (int)Utils.dp2px(300));
        // 从原point做属性动画到targetPoint，按照PointEvaluator
        ObjectAnimator pointAnimator = ObjectAnimator.ofObject(pointView, "point", new PointEvaluator(), targetPoint);
        pointAnimator.setStartDelay(1500);
        pointAnimator.setDuration(2000);
        pointAnimator.start();

        return view;
    }

    // 自定义TypeEvaluator
    class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float v, Point point, Point t1) {
            // 计算出每个时间点属性的值 point 开始位置， t1目标位置
            float x = point.x + v * (t1.x - point.x);
            float y = point.y + v * (t1.y - point.y);
            return new Point((int)x, (int)y);
        }
    }
}
