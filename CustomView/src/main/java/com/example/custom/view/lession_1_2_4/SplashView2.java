package com.example.custom.view.lession_1_2_4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.custom.R;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 *
 *
 * 手写开屏页联系
 *
 */
public class SplashView2 extends View {


    // 绘制小球的画笔
    private Paint mPaint;
    // 旋转圆圆心的 x,y 坐标
    private float mCenterX;
    private float mCenterY;
    // 小球的颜色数组
    private int[] mColors;
    private Paint mHoloPaint;
    private int mBackgroundColor = Color.WHITE;
    // 小球半径
    private float mBallRadius = 18F;
    // 旋转圆半径
    private float mRotateRadius = 90F;
    // 扩散圆最大半径
    private float mDistance;
    // 旋转圆的初始角度
    private float mCurrentRotateDegree = 0f;
    // 动画
    private ValueAnimator mAnimator;
    // 动画时长
    private long mDuration = 1200L;
    private float mExpandRadius;


    public SplashView2(Context context) {
        this(context, null);
    }

    public SplashView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        init(context);

    }

    private void init(Context context) {
        // 初始化绘制小球的画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 初始化绘制扩散圆的画笔
        mHoloPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHoloPaint.setStyle(Paint.Style.STROKE);
        mHoloPaint.setColor(mBackgroundColor);

        // 小球的颜色数组
        mColors = context.getResources().getIntArray(R.array.splash_circle_colors);

        mAnimator = ValueAnimator.ofFloat(0, (float) Math.PI * 2);

        mAnimator.setDuration(mDuration);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(2);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentRotateDegree = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                merge();
            }
        });
        mAnimator.start();


    }


    /**
     * 小球扩散聚合动画
     */
    private void merge() {
        mAnimator = ValueAnimator.ofFloat(0, mRotateRadius);
        // 过分插值器，传入过分值
        mAnimator.setInterpolator(new OvershootInterpolator(10f));
        mAnimator.setDuration(mDuration);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRotateRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                expand();
            }
        });
        mAnimator.reverse();
    }

    /**
     * 水波纹打开动画
     */
    private void expand() {
        mAnimator = ValueAnimator.ofFloat(0, mDistance);
        mAnimator.setDuration(mDuration);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mExpandRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        if (mExpandRadius == 0f)
            drawBalls(canvas);

    }

    /**
     * 绘制背景
     *
     * @param canvas 画布
     */
    private void drawBackground(Canvas canvas) {
        if (mExpandRadius > 0) {
            // 绘制空心圆
            float width = mDistance - mExpandRadius;
            float radius = width / 2 + mExpandRadius;
            mHoloPaint.setStrokeWidth(width);
            canvas.drawCircle(mCenterX, mCenterY, radius, mHoloPaint);
        } else {
            // 绘制背景
            canvas.drawColor(mBackgroundColor);
        }
    }

    /**
     * 绘制小球
     *
     * @param canvas 画布
     */
    private void drawBalls(Canvas canvas) {

        // 计算每个小球圆心和大圆圆心的夹角 （弧度）

        double v = Math.PI * 2 / mColors.length;
        for (int i = 0; i < mColors.length; i++) {
            // 计算每个小球的圆心坐标
            double x = Math.cos(v * i + mCurrentRotateDegree) * mRotateRadius + mCenterX;
            double y = Math.sin(v * i + mCurrentRotateDegree) * mRotateRadius + mCenterY;
            mPaint.setColor(mColors[i]);
            canvas.drawCircle((float) x, (float) y, mBallRadius, mPaint);
        }

    }


    /**
     * 绘制小球
     *
     * @param canvas 画布
     */
    private void drawBalls2(Canvas canvas) {

    }

    /**
     * 初始化控件的时候调用一次
     *
     * @param w    宽
     * @param h    高
     * @param oldw old 宽
     * @param oldh old 高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // view 中心点的 x，y 坐标
        mCenterX = w * 1f / 2;
        mCenterY = h * 1f / 2;
        // 水波纹扩散圆的最大扩散半径，view对角线 长度的一半，
        mDistance = (float) (Math.hypot(w, h) / 2f);
    }


}
