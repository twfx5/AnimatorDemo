package com.wzh.animatordemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.animatordemo.Utils;


/**
 * 仿写QQ计步效果
 */
public class StepView extends View {

    private static final float LENGTH= Utils.dp2px(100);
    private static final float RADIUS = Utils.dp2px(200); // 圆弧半径
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int stepRadius = 0; // 步数的圆弧
    private int progress = 4; // 转一度走的步数
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public StepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int getStepRadius() {
        return stepRadius;
    }

    public void setStepRadius(int stepRadius) {
        this.stepRadius = stepRadius;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制整个圆弧框架
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(10));
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2f- LENGTH, getHeight() / 2f - LENGTH, getWidth() / 2f + LENGTH, getHeight() / 2f + LENGTH,
                150, 240, false, paint);

        // 绘制动态的圆弧
        paint.setColor(Color.GREEN);
        canvas.drawArc(getWidth() / 2f- LENGTH, getHeight() / 2f - LENGTH, getWidth() / 2f + LENGTH, getHeight() / 2f + LENGTH,
                150, stepRadius, false, paint);

        // 绘制步数文字
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Utils.dp2px(30));
        paint.setColor(Color.RED);
        paint.getFontMetrics(fontMetrics);
        float offSet = (fontMetrics.ascent + fontMetrics.descent) / 2;
        canvas.drawText(String.valueOf(stepRadius * progress), getWidth() / 2f, getHeight() / 2f - offSet, paint);
    }
}
