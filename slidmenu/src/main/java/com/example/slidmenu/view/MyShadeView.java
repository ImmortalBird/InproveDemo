package com.example.slidmenu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyShadeView extends View {

    /**
     * Joker: 2021/3/3
     * 混合模式
     */
    private PorterDuffXfermode porterDuffXfermode;
    /**
     * Joker: 2021/3/3
     * 控制虚线样式
     */
    private DashPathEffect effect;

    public MyShadeView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyShadeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyShadeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;
    private int mX, mY, mRadius;

    private void init() {
//        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        setLayerType(View.LAYER_TYPE_HARDWARE,null);
//        setBackgroundColor(Color.parseColor("#EF000000"));
        mX = 150;
        mY = 100;
        mRadius = 80;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        mPaint.setAntiAlias(true);
        effect = new DashPathEffect(new float[]{20, 20}, 0);
    }

    public void setCircleLocation(int x, int y, int radius) {
        this.mX = x;
        this.mY = y;
        this.mRadius = radius;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Joker: 2021/3/3 保存图层
        int layerId = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), null, Canvas.ALL_SAVE_FLAG);

        // Joker: 2021/3/3 绘制背景
        mPaint.setColor(0x99000000);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        // Joker: 2021/3/3 设置混合模式
        mPaint.setXfermode(porterDuffXfermode);
        // Joker: 2021/3/3 绘制圆
        canvas.drawCircle(mX, mY, mRadius, mPaint);
        // Joker: 2021/3/3 清除
        mPaint.setXfermode(null);

        // Joker: 2021/3/3 恢复图层
        canvas.restoreToCount(layerId);
        // Joker: 2021/3/3 绘制外围虚线
        mPaint.setPathEffect(effect);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xffffffff);
        canvas.drawCircle(mX, mY, mRadius +10, mPaint);
    }

}
