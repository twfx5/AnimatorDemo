package com.wzh.animatordemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 仿写Flipboard的动画
 */
public class FlipboardView extends View {
    private static final int LENGTH = (int) Utils.dp2px(100); // 图片移动距离
    private static final int WIDTH = (int) Utils.dp2px(200); // 图片宽度
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private Camera camera = new Camera();
    private float flipRotate = 0; // 画布裁切旋转的角度
    private float topFlip = 0; // 顶部翻页效果
    private float bottomFlip = 0; // 底部翻页效果

    public FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
//        camera.rotateX(45);
        camera.setLocation(0, 0, Utils.getZForCamera());
        bitmap = Utils.getAvatar(getResources(), WIDTH);
    }

    public float getFlipRotate() {
        return flipRotate;
    }

    public void setFlipRotate(float flipRotate) {
        this.flipRotate = flipRotate;
        invalidate();
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 上半部分
        canvas.save();
        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        canvas.rotate(- flipRotate);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(- WIDTH, - WIDTH, WIDTH, 0);
        canvas.rotate(flipRotate);
        canvas.translate(- (WIDTH / 2 + LENGTH), - (WIDTH / 2 + LENGTH));
        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint); // 为了便于思考，这里复制了下面的逻辑，从LENGTH、LENGTH处开始绘制
        canvas.restore();


        // 下半部分
        canvas.save();
        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        canvas.rotate(-flipRotate);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-WIDTH, 0, WIDTH, WIDTH);
        canvas.rotate(flipRotate);
        canvas.translate(- (WIDTH / 2 + LENGTH), - (WIDTH / 2 + LENGTH));
        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint);
        canvas.restore();

    }
}
