package com.xiaobing.improvedemo.animation.anim;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ContentScaleAnimationNew extends Animation {
    /**
     * 缩放点的 x y 坐标
     */
    private PointF point;

    /**
     * 要播放动画的控件左上角 X Y 坐标
     */
    private float viewHeadX;
    private float viewHeadY;
    private final float duration;
    /**
     * 是否翻转动画
     */
    private boolean mReverse;

    /**
     * 构造方法
     *
     * @param viewHeadX 控件左上角 X 坐标
     * @param viewHeadY 控件左上角 Y 坐标
     * @param duration  动画时长
     * @param mReverse  是否反转动画
     */
    public ContentScaleAnimationNew(float viewHeadX, float viewHeadY, float duration, boolean mReverse) {

        this.viewHeadX = viewHeadX;
        this.viewHeadY = viewHeadY;
        this.duration = duration;
        this.mReverse = mReverse;
    }

    /**
     * 开始执行动画
     * @param interpolatedTime  xx
     * @param t                 x
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //缩放方法
        Matrix matrix = t.getMatrix();
        // 对矩阵进行后乘 详见线性代数
        if (mReverse) {
            matrix.postScale(1 + (duration - 1) * (1.0f - interpolatedTime), 1 + (duration - 1) * (1.0f - interpolatedTime), point.x - viewHeadX, point.y - viewHeadY);
        } else {
            matrix.postScale(1 + (duration - 1) * interpolatedTime, 1 + (duration - 1) * interpolatedTime, point.x - viewHeadX, point.y - viewHeadY);
        }
    }

    /**
     * 初始化一些参数
     * @param width         控件宽
     * @param height        控件高
     * @param parentWidth   父控件宽
     * @param parentHeight  父控件高
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        point = resolvePoint(viewHeadX, viewHeadY, parentWidth, parentHeight, width, height);
    }
    //缩放点坐标值   缩放点到自身左边距离/缩放点到父控件左边的距离=缩放点自身右侧距离/缩放点到父控件右边的距离

    /**
     * 计算坐标点
     *
     * @param x            控件的左上角 X  坐标
     * @param y            控件的左上角 Y 坐标
     * @param parentWidth  父控件的宽（如果是全屏的可以填写屏幕宽）
     * @param parentHeight 父控件的高（如果是全屏的可以填写屏幕高）
     * @param viewWidth    控件的宽
     * @param viewHeight   控件的高
     * @return 坐标点
     */
    private PointF resolvePoint(float x, float y, int parentWidth, int parentHeight, int viewWidth, int viewHeight) {
        return new PointF((x * parentWidth) / (parentWidth - viewWidth), (y * parentHeight) / (parentHeight - viewHeight));
    }

    /**
     * 反转动画
     */
    public void reverse() {
        mReverse = !mReverse;
    }

    /**
     * 获取动画的是否为反转状态
     * @return true or false
     */
    public boolean getMReverse() {
        return mReverse;
    }
}
