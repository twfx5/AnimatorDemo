package com.wzh.animatordemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.animatordemo.Utils;

/**
 * 仿写小米运动，StepView的加强版
 */
public class MoveCircleView extends View {
    private static final int ANGLE = 60; // 初始角度
    private static final float RADIUS = Utils.dp2px(150); // 圆弧半径
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private SweepGradient sweepGradient;
    private int[] colors = {
            Color.parseColor("#00FFFF"),
            Color.parseColor("#00FA9A"),
            Color.parseColor("#008000"),
            Color.parseColor("#FF4500")
    };
    private float[] positions = {0.5f, 0.5f, 1f, 1f};
    private Shader mShader;
    private int transparentWhite;
    private int sweepAngle; // 圆弧扫过的角度
    private Path sweepPath;

    public MoveCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        transparentWhite = Color.parseColor("#00ffffff");
        mShader = new SweepGradient(getWidth() / 2, getHeight() / 2, transparentWhite, Color.WHITE);
        paint.setStrokeWidth(Utils.dp2px(2));

        sweepPath = new Path();
    }

    public int getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(int sweepAngle) {
        this.sweepAngle = sweepAngle;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(mShader);
        for (int i = 0; i < 20; i ++) {
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            int currentRadius = (int) (Utils.dp2px((float)(Math.random()*20)) + RADIUS);
            canvas.drawArc(getWidth() / 2 - currentRadius, getHeight() / 2 - currentRadius, getWidth() /2 + currentRadius, getHeight() / 2 + currentRadius,
                    90 + ANGLE / 2, sweepAngle, false, paint);


//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.RED);
//            paint.setStrokeWidth(Utils.dp2px(4));
//            sweepPath.addCircle(getWidth() / 2 + currentRadius + Utils.dp2px(2), getHeight() / 2 + currentRadius + Utils.dp2px(4), Utils.dp2px(4), Path.Direction.CW);
//            canvas.drawPath(sweepPath, paint);

        }
//        canvas.drawPath(sweepPath, paint);
        for (int i = 0; i < 10; i ++) {
//            canvas.drawCircle();
        }
        paint.setShader(null);

    }
}
