package com.wzh.animatordemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.Utils;

/**
 * 可以变色的TextView
 * canvas裁剪画布，左边用一个画布绘制，右边用另一个颜色的画笔绘制
 * 通过改变中间截断点值来改变颜色
 */
public class ColorTrackTextView extends View {

    private Paint originColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 原始颜色
    private Paint changeColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 改变的颜色
    private String text;
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private Rect bounds = new Rect(); // 文字的bounds
    private float fraction; // 变化

    public ColorTrackTextView(Context context) {
        super(context);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int originColor = typedArray.getColor(R.styleable.ColorTrackTextView_originColor, getResources().getColor(R.color.colorAccent));
        int changeColor = typedArray.getColor(R.styleable.ColorTrackTextView_changeColor, getResources().getColor(R.color.colorAccent));
        text = typedArray.getString(R.styleable.ColorTrackTextView_textName);
        int textSize = typedArray.getInt(R.styleable.ColorTrackTextView_textSize, 15);
        originColorPaint.setTextSize(Utils.dp2px(textSize));
        originColorPaint.setColor(originColor);
        originColorPaint.setStyle(Paint.Style.FILL);
        changeColorPaint.setTextSize(Utils.dp2px(textSize));
        changeColorPaint.setColor(changeColor);
        changeColorPaint.setStyle(Paint.Style.FILL);
        typedArray.recycle();
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        originColorPaint.getFontMetrics(fontMetrics);
        // 计算文字偏移
        float offSet = (fontMetrics.ascent + fontMetrics.descent) / 2;
        originColorPaint.getTextBounds(text, 0, text.length(), bounds);
        // 文字绘制的起点 = 屏幕宽度一半 - bounds宽度的一半
        int startX = getWidth() / 2 - bounds.width() / 2;
        // 绘制原始部分
        canvas.clipRect(startX, 0, getWidth() / 2 + getWidth() / 2 - bounds.width() / 2, getHeight() / 2 + getHeight() / 2 - bounds.height() / 2);
        canvas.drawText(text, startX, getHeight() / 2f - offSet, originColorPaint);

        // 绘制变色部分
        canvas.clipRect(startX, 0, startX + fraction * bounds.width(), getHeight() / 2f + getHeight() / 2f - bounds.height() / 2f);
        canvas.drawText(text, startX, getHeight() / 2f - offSet, changeColorPaint);
    }
}
