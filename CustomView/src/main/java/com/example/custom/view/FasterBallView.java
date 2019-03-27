package com.example.custom.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/27
 *
 * todo 想实现 腾讯电脑管家 加速球样式
 */
public class FasterBallView extends View {

    private Paint mPaint;
    private Paint mPaint2;
    private Paint mPaint3;
    private Random random;
    private ValueAnimator mAnimator;
    public FasterBallView(Context context) {
        this(context, null);
    }

    public FasterBallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FasterBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.parseColor("#000000"));
        mPaint = new Paint();
        mPaint2 = new Paint();
        // 设置画笔线条宽度
        mPaint.setStrokeWidth(10F);
        mPaint2.setStrokeWidth(5F);
        // 设置抗锯齿
        mPaint.setAntiAlias(true);

        // 设置填充方式为描边
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint2.setStyle(Paint.Style.STROKE);
        // 设置填充方式为 填充加描边
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // 设置画笔颜色
//        mPaint.setColor(Color.RED);
        mPaint.setColor(Color.parseColor("#ACEDDD"));
        mPaint2.setColor(Color.parseColor("#EDFDFD"));
        mPaint3 = new Paint();
        mPaint3.setColor(Color.BLACK);
        mPaint3.setStyle(Paint.Style.FILL);
        random = new Random();
        mAnimator = ValueAnimator.ofFloat(0,1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                invalidate();
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int s= 72;
        canvas.translate(500,500);
        int radius = 200;
        canvas.drawCircle(0,0, radius,mPaint);

        int bound = 20;
        for (int i = 0; i < 4; i++) {
            canvas.save();
            canvas.rotate(s * i + s);
            canvas.drawOval(-(random.nextInt(bound) + radius + 10),
                    -(random.nextInt(bound) + radius - 10),
                    (random.nextInt(bound) + radius - 10),
                    (random.nextInt(bound) + radius + 10),
                    mPaint2);
            canvas.restore();
        }
        canvas.drawCircle(0,0,196,mPaint3);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (performClick()){
            return performClick();
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //执行动画
            mAnimator.start();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
