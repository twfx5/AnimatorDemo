package com.wzh.animatordemo.fragments;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.Utils;

/**
 * 贝塞尔曲线
 */
public class Demo6 extends Fragment {

    private ImageView imageView;
    // 曲线当前位置
    private float[] mCurrentPositions = new float[2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo6,container,false);
        imageView = view.findViewById(R.id.imageView);
        Path path = new Path();
        path.moveTo(0, 0);
        // path.quadTo 贝塞尔曲线，前两个参数是控制点，后两个参数是结束点
        path.quadTo(Utils.dp2px(120), 0 , Utils.dp2px(460), Utils.dp2px(460));
        // 创建PathMeasure来测量曲线的长度
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        // 从0开始做动画到曲线长度
        ValueAnimator animator = ValueAnimator.ofFloat(0,  pathMeasure.getLength());
        animator.setStartDelay(2000);
        animator.setDuration(3000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (Float) valueAnimator.getAnimatedValue();
                // 获取指定长度的位置坐标及该点切线值， 求tan值
                pathMeasure.getPosTan(value, mCurrentPositions, null);
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                imageView.setTranslationX(mCurrentPositions[0]);
                imageView.setTranslationY(mCurrentPositions[1]);
            }
        });
        return view;

    }
}
