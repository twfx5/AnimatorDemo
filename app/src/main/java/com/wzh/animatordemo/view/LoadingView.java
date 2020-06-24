package com.wzh.animatordemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.animatordemo.R;
import com.wzh.animatordemo.Utils;

public class LoadingView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int WIDTH = (int) Utils.dp2px(16);
    private Bitmap bitmapRed, bitmapBlue, bitmapYellow;

    // 每次改变的份数
    private float fractionRed, fractionYellow, fractionBlue;
    // 移动的距离
    private static final int distance = (int) Utils.dp2px(40);

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmapRed = Utils.getBitmap(getResources(), WIDTH, R.drawable.circle_red);
        bitmapBlue = Utils.getBitmap(getResources(), WIDTH, R.drawable.circle_blue);
        bitmapYellow = Utils.getBitmap(getResources(), WIDTH, R.drawable.circle_yellow);
    }


    public float getFractionRed() {
        return fractionRed;
    }

    public void setFractionRed(float fractionRed) {
        this.fractionRed = fractionRed;
        invalidate();
    }

    public float getFractionYellow() {
        return fractionYellow;
    }

    public void setFractionYellow(float fractionYellow) {
        this.fractionYellow = fractionYellow;
        invalidate();
    }

    public float getFractionBlue() {
        return fractionBlue;
    }

    public void setFractionBlue(float fractionBlue) {
        this.fractionBlue = fractionBlue;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmapRed, getWidth() / 2f + fractionYellow * distance - fractionBlue * distance, getHeight() / 2f, paint);

        canvas.drawBitmap(bitmapBlue, getWidth() / 2f - fractionRed * distance - fractionYellow * distance, getHeight() / 2f, paint);

        canvas.drawBitmap(bitmapYellow, getWidth() / 2f + fractionRed * distance + fractionBlue * distance, getHeight() / 2f, paint);
    }
}
