package com.wzh.animatordemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.Utils;

/**
 * 可以变色的TextView
 * canvas裁剪画布，左边用一个画布绘制，右边用另一个颜色的画笔绘制
 * 通过改变中间截断点值来改变颜色
 *
 * 计算TextView宽度的方法
 * Paint.getTextBounds() 可以获得TextView的bounds, bounds.width比实际的宽度要小， bounds.width() + bounds.left 才是实际的宽度
 * Paint.measureText() 则只是返回宽度信息，返回的就是实际的宽度
 *
 * measureText() 返回结果会略微大于 getTextBounds() 所得到的宽度信息
 * measureText() 会在文本的左右两侧加上一些额外的宽度，这部分额外的宽度叫做 Glyph's AdvanceX （具体应该是属于字型方面的范畴，我猜测这部分宽度是类似字间距之类的东西）
 *
 */
public class ColorTrackTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint originColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 原始颜色
    private Paint changeColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 改变的颜色
    private String text;
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private Rect bounds = new Rect(); // 文字的bounds，通过paint.getTextBounds()来获得
    private float fraction; // 变化

    public ColorTrackTextView(Context context) {
        super(context);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int originColor = typedArray.getColor(R.styleable.ColorTrackTextView_originColor, getResources().getColor(R.color.colorAccent));
        int changeColor = typedArray.getColor(R.styleable.ColorTrackTextView_changeColor, getResources().getColor(R.color.colorAccent));
        text = getText().toString();
        float textSize = getTextSize();
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
        originColorPaint.getFontMetrics(fontMetrics);
        // 计算文字偏移
        float offSet = (fontMetrics.ascent + fontMetrics.descent) / 2;
        originColorPaint.getTextBounds(text, 0, text.length(), bounds);
        // measureText 返回的就是实际的宽度
        float textWidth = originColorPaint.measureText(text);
        // 文字绘制的起点 = 屏幕宽度一半 - bounds宽度的一半
        int startX = getWidth() / 2 - bounds.width() / 2;
        // 绘制原始部分
        canvas.drawText(text, startX, getHeight() / 2f - offSet, originColorPaint);

        // 绘制变色部分 (bounds.width() + bounds.left) 就是TextView的宽度
        canvas.clipRect(startX, 0, startX + fraction * textWidth, getHeight() / 2f + getHeight() / 2f - bounds.height() / 2f);
        canvas.drawText(text, startX, getHeight() / 2f - offSet, changeColorPaint);
    }
}
