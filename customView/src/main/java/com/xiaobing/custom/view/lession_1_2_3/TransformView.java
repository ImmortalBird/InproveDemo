package com.xiaobing.custom.view.lession_1_2_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/26
 */
public class TransformView extends View {
    private Paint mPaint;
    private Matrix matrix;

    public TransformView(Context context) {
        this(context, null);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransformView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setStrokeWidth(2F);
        // 设置画笔填充风格
        mPaint.setStyle(Paint.Style.STROKE);

        // 初始化 Matrix 对象
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        // 画布平移操作
        canvas.drawRect(5, 5, 100, 100, mPaint);
        canvas.translate(50, 50);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(50, 50, 150, 150, mPaint);

        canvas.translate(-50,-50);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0,0,200,200,mPaint);
        */


        int left = 0, top = left, right = 200, bottom = right, half = (left + right) / 2;

/*

        // 画布缩放操作
        canvas.drawRect(left, top, right, bottom, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPoint((left + right) / 2F,(top + bottom) / 2f + (top + bottom) / 4f,
                mPaint);
//        canvas.scale(0.9F,0.9F);
        for (int i = 0; i < 20; i++) {
            // 以指定坐标点为中心进行缩放操作
            */
        /*
         * 1、每次进行缩放是对 上次已经缩放过的画布 继续进行缩放，
         *      * 由于是对整个画布的操作，所以画笔的线条也会被缩放
         * 2、每次进行缩放的 中心点，如果中心点坐标值不变的话，那么点在屏幕上的位置也不会变化
         *      * 因为是同一个点
         *
         *//*

            canvas.scale(0.9F, 0.9F, (left + right) / 2F,
                    (top + bottom) / 2f + (top + bottom) / 4f);
            canvas.scale(0.9F, 0.7F, (left + right) / 2F,
                    (top + bottom) / 2f + (top + bottom) / 4f);
            canvas.drawPoint((left + right) / 2F,(top + bottom) / 2f + (top + bottom) / 4f,
                    mPaint);
            canvas.drawRect(left, top, right, bottom, mPaint);
        }

*/
        // 旋转操作
        canvas.translate(800, 800);
        canvas.drawRect(0, 0, right, bottom, mPaint);
        mPaint.setColor(Color.BLUE);

        for (int i = 0; i < 23; i++) {
//               正数为顺时针； 负数为逆时针
            canvas.rotate(15f);
            canvas.drawRect(0, 0, right, bottom, mPaint);
        }

/*

        // 倾斜操作
        canvas.translate(getWidth() / 2F, getHeight() / 2F);
        canvas.drawRect(left,top,right,bottom,mPaint);
//        canvas.skew(0.1F,0);
        canvas.skew(-0.5F,0);
//        canvas.skew(1,0);
//        canvas.skew(1.5f,0);
//        canvas.skew(4,0);
//        canvas.skew(1,-1);
//        canvas.skew(0,1);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(left,top,right,bottom,mPaint);

*/
/*

        // 切割操作
        canvas.translate(getWidth() / 2F, getHeight() / 2F);
        canvas.drawRect(left,top,right,bottom,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.clipRect(left,top,right,bottom);
        canvas.drawRect(50,50,right+50,bottom+50,mPaint);

        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(100,100,100,mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(300,300,200,mPaint);

*/

        // 矩阵
        canvas.translate(getWidth() / 2f, getHeight() / 2f);
        canvas.drawRect(left, top, right, bottom, mPaint);


        matrix.setRotate(15);
        matrix.setTranslate(getWidth() / 2f, getHeight() / 2f);
        canvas.setMatrix(matrix);

        mPaint.setColor(Color.GRAY);
        canvas.drawRect(left, top, right, bottom, mPaint);


    }
}
