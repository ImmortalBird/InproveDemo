package com.example.custom.view.lession_1_2_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/26
 */
public class SaveRestoreView extends View {

    private Paint mPaint;

    public SaveRestoreView(Context context) {
        this(context, null);
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SaveRestoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始化画笔
        mPaint = new Paint();
        // 设置画笔颜色
        mPaint.setColor(Color.RED);
        // 设置抗锯齿
        mPaint.setAntiAlias(true);
        // 设置画笔线条宽度
        mPaint.setStrokeWidth(1F);
        // 设置画笔类型为描边
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制一个矩形
        canvas.drawRect(0, 0, 500, 500, mPaint);

//        canvas.save();
//        // 平移画布
//        canvas.translate(100, 100);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(0, 0, 500, 500, mPaint);
//        canvas.restore();
        canvas.scale(0.5f, 0.5f);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, 500, 500, mPaint);

    }
}
