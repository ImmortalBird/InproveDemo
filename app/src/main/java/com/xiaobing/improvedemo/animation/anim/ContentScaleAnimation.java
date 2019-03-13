package com.xiaobing.improvedemo.animation.anim;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ContentScaleAnimation extends Animation {
    private float mPivotX;
    private float mPivotY;
    /**
     * 控件左上角X
     */
    private float mPivotYValue;
    private float mPivotXValue;
    private final float scaleTimes;
    private boolean mReverse;

    public ContentScaleAnimation(float mPivotXValue, float mPivotYValue, float scaleTimes, boolean mReverse) {

        this.mPivotXValue = mPivotXValue;
        this.mPivotYValue = mPivotYValue;
        this.scaleTimes = scaleTimes;
        this.mReverse = mReverse;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //缩放方法
        Matrix matrix=t.getMatrix();
        if (mReverse) {
            matrix.postScale(1 + (scaleTimes - 1) * (1.0f - interpolatedTime), 1 + (scaleTimes - 1) * (1.0f - interpolatedTime), mPivotX - mPivotXValue, mPivotY - mPivotYValue);
        } else {
            matrix.postScale(1 + (scaleTimes - 1) * interpolatedTime, 1 + (scaleTimes - 1) * interpolatedTime, mPivotX - mPivotXValue , mPivotY - mPivotYValue );
        }
    }
    //缩放点坐标值
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mPivotX = resolvePivotX(mPivotXValue, parentWidth, width);
        mPivotY = resolvePivoY(mPivotYValue, parentHeight, height);
    }
    //缩放点坐标值   缩放点到自身左边距离/缩放点到父控件左边的距离=缩放点自身右侧距离/缩放点到父控件右边的距离
    private float resolvePivotX(float margingLeft, int parentWidth, int width) {
        return (margingLeft * parentWidth) / (parentWidth - width);
    }

    private float resolvePivoY(float marginTop, int parentHeight, int height) {
        return (marginTop * parentHeight) / (parentHeight - height);
    }

    public void reverse() {
        mReverse = !mReverse;
    }

    public boolean getMReverse() {
        return mReverse;
    }
}
