package com.wzh.animatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    private CircleView view;
//    private PointView view;
//    private StepView view;
    private FlipboardView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "radius", Utils.dp2px(150));
//        objectAnimator.setStartDelay(1500);
//        objectAnimator.start();

//        Point targetPoint = new Point((int)Utils.dp2px(300), (int)Utils.dp2px(300));
//        ObjectAnimator pointAnimator = ObjectAnimator.ofObject(view, "point", new PointEvaluator(), targetPoint);
//        pointAnimator.setStartDelay(1500);
//        pointAnimator.setDuration(2000);
//        pointAnimator.start();

        // 记步器
//        ObjectAnimator stepRadiusAnimator = ObjectAnimator.ofInt(view, "stepRadius", 180);
//        stepRadiusAnimator.setStartDelay(2000);
//        stepRadiusAnimator.setDuration(1500);
//        stepRadiusAnimator.start();

        ObjectAnimator bottomFlipAnim = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
        bottomFlipAnim.setDuration(1500);

        // 旋转270度后，原来的下半部分变成了左半部分，上半部分变成了右半部分
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(view, "flipRotate", 270);
        rotateAnim.setDuration(1500);

        ObjectAnimator topFlipAnim = ObjectAnimator.ofFloat(view, "topFlip", -45);
        topFlipAnim.setDuration(1500);

        ObjectAnimator bottomFlipAnim2 = ObjectAnimator.ofFloat(view, "bottomFlip", 0);
        bottomFlipAnim.setDuration(1500);

        ObjectAnimator topFlipAnim2 = ObjectAnimator.ofFloat(view, "topFlip", 0);
        topFlipAnim.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnim, rotateAnim, topFlipAnim, bottomFlipAnim2, topFlipAnim2);
        animatorSet.setStartDelay(1500);
        animatorSet.start();

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
