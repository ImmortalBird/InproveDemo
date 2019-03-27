package com.example.custom.view.lession_1_2_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
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
        mPaint.setStrokeWidth(5F);
        // 设置画笔类型为描边
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(50f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制一个矩形
        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
        canvas.drawRect(50,50,200,200,mPaint);
        canvas.drawText("SaveCount = " + canvas.getSaveCount(),200,1000,mPaint);
        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
//        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
//        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
//        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
//        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
        canvas.save();
        canvas.translate(50,50);
        Log.d("onDraw","SaveCount = " + canvas.getSaveCount());
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(50,50,200,200,mPaint);
        canvas.drawText("SaveCount = " + canvas.getSaveCount(),200,900,mPaint);
        canvas.save();
        mPaint.setColor(Color.GREEN);
        canvas.scale(0.8f,0.8f);
        canvas.drawText("SaveCount = " + canvas.getSaveCount(),200,800,mPaint);
        canvas.drawRect(50,50,200,200,mPaint);


        canvas.restore();
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(50,50,150,200,mPaint);
        canvas.drawText("SaveCount = " + canvas.getSaveCount(),200,700,mPaint);

        canvas.restore();
        mPaint.setColor(Color.BLACK);
        canvas.drawText("SaveCount = " + canvas.getSaveCount(),200,600,mPaint);
        canvas.drawRect(50,50,150,200,mPaint);


    }
}
