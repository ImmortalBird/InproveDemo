package com.example.custom.view.lession_1_2_4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Sampler;
import android.service.autofill.FillCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.custom.R;

public class SplashView extends View {

    //旋转圆的画笔
    private Paint mPaint;
    //扩散圆的画笔
    private Paint mHolePaint;
    //属性动画
    private ValueAnimator mValueAnimator;

    //背景色
    private int mBackgroundColor = Color.WHITE;
    private int[] mCircleColors;

    //表示旋转圆的中心坐标
    private float mCenterX;
    private float mCenterY;
    //表示斜对角线长度的一半,扩散圆最大半径
    private float mDistance;

    //6个小球的半径
    private float mCircleRadius = 18;
    //旋转大圆的半径
    private float mRotateRadius = 90;

    //当前大圆的旋转角度
    private float mCurrentRotateAngle = 0F;
    //当前大圆的半径
    private float mCurrentRotateRadius = mRotateRadius;
    //扩散圆的半径
    private float mCurrentHoleRadius = 0F;
    //表示旋转动画的时长
    private int mRotateDuration = 1200;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        // 初始化变量
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);
        // 小球颜色数组
        mCircleColors = context.getResources().getIntArray(R.array.splash_circle_colors);

        mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
        mValueAnimator.setRepeatCount(2);
        mValueAnimator.setDuration(mRotateDuration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 更新大圆旋转角度
                mCurrentRotateAngle = (float) animation.getAnimatedValue();
                invalidate();

            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 动画结束，执行扩散聚合动画
                merge();
            }


        });

        mValueAnimator.start();


    }

    private void merge() {
        mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);
//            mValueAnimator.setRepeatCount(2);
        mValueAnimator.setDuration(mRotateDuration);
        mValueAnimator.setInterpolator(new OvershootInterpolator(10f));
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentRotateRadius = (float) animation.getAnimatedValue();
                Log.e("onAnimationUpdate", "mCurrentRotateRadius = " + mCurrentRotateRadius);
                invalidate();
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                expand();
            }
        });
        mValueAnimator.reverse();
    }

    private void expand() {
        mValueAnimator = ValueAnimator.ofFloat(mCircleRadius,mDistance);
        mValueAnimator.setDuration(mRotateDuration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentHoleRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (getParent() != null) {
                    ((ViewGroup)getParent()).removeView(SplashView.this);
                }
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制小球
        drawBackground(canvas);
        if (mCurrentHoleRadius == 0f)
            drawBalls(canvas);
    }

    private void drawBackground(Canvas canvas) {
        if (mCurrentHoleRadius > 0) {
            // 绘制水波纹扩散圆
            float strokeWidth = mDistance - mCurrentHoleRadius;
            float radius = strokeWidth / 2 + mCurrentHoleRadius;
            mHolePaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle(mCenterX, mCenterY, radius, mHolePaint);
        } else {
            canvas.drawColor(mBackgroundColor);
        }
    }

    private void drawBalls(Canvas canvas) {
        // 将画布平移到中心点
        float degree = (float) (Math.PI * 2f / mCircleColors.length);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        for (int i = 0; i < mCircleColors.length; i++) {
            mPaint.setColor(mCircleColors[i]);
            // 加上 mCurrentRotateRadius（大圆初始旋转角度）
            float angle = degree * i + mCurrentRotateAngle;
            // 计算小球的中心点坐标
            float cx = (float) (Math.cos(angle) * mCurrentRotateRadius + mCenterX);
            float cy = (float) (Math.sin(angle) * mCurrentRotateRadius + mCenterY);

//            mCenterX = (float) Math.cos(angle) * mCurrentRotateRadius  + mCenterX;
//            mCenterY = (float) Math.sin(angle) * mCurrentRotateRadius + mCenterY;
            canvas.drawCircle(cx, cy, mCircleRadius, mPaint);

        }
    }


    private void drawBalls2(Canvas canvas) {
        // 将画布平移到中心点

        canvas.translate(getWidth() / 2f, getHeight() / 2f);
        float degree = (float) (360f / mCircleColors.length);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0; i < mCircleColors.length; i++) {
            canvas.save();
            canvas.rotate(degree * i);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(mRotateRadius, 0, mCircleRadius, mPaint);
            canvas.restore();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("onSizeChanged", "w = " + w + " -- h = " + h +
                " -- oldW = " + oldw + " -- oldH = " + oldh);
        mCenterX = w * 1f / 2;
        mCenterY = h * 1f / 2;
        mDistance = (float) (Math.hypot(w, h) / 2);
    }


}
