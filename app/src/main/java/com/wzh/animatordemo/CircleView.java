package com.wzh.animatordemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自定义属性动画
 * 1.需要对动画的属性，定义get、set方法
 * get是为了找到属性的初始值，set是动态的去改变属性的值
 * 这样系统在做动画时，会用反射来调用set方法，不断的改变属性的值
 *
 * 2.需要在set方法中，调用invalidate()，设置失效，这样系统在下次绘制时（16ms后）
 * 在onDraw()方法中会设置新的属性值
 */
public class CircleView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = Utils.dp2px(50);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(Color.RED);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }
}
