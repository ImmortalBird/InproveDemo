package com.example.custom.view.lession_1_2_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.custom.R;

public class GradientLayout extends View {

    private Paint mPaint;
    private Shader mShader;
    private Bitmap mBitmap;

    public GradientLayout(Context context) {
        this(context, null);
    }

    public GradientLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setStrokeWidth(2F);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);




        // 解析一个map对象
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
        /*
         * 线性渲染
         *
         * @param x0           渐变起始点的 X 坐标
         * @param y0           渐变起始点的 Y 坐标
         * @param x1           渐变结束点的 X 坐标
         * @param y1           渐变结束点的 Y 坐标
         * @param colors       渐变色数组
         * @param positions    颜色渐变的位置数组 范围[0-1]，
         *                     可以为null，渐变为线性变化
         *                     如果不为null，顺序从小到大，长度必须和颜色数组相同
         * @param tile         Shader.TileMode.CLAMP    绘制区域超过渲染区域的部分，会以最后一个像素拉伸排版
         *                                              绘制区域小于渲染区域，会截取一部分渲染区域
         *                     Shader.TileMode.REPEAT   绘制区域超过渲染区域的部分，会重复排版
         *                                              绘制区域小于渲染区域，会截取一部分渲染区域
         *                     Shader.TileMode.MIRROR   绘制区域超过渲染区域的部分，会镜像排版
         *                                              绘制区域小于渲染区域，会截取一部分渲染区域
         */

        mShader = new LinearGradient(0,0,500,500,
                new int[]{Color.RED,Color.BLUE,Color.GREEN},
                null,
                Shader.TileMode.MIRROR);

        /*
         * Create a shader that draws a radial gradient given the center and radius.
         * 声明一个环形渲染
         * @param centerX  中心点 X 坐标
         * @param centerY  中心点 Y 坐标
         * @param radius   半径，必须是正的
         * @param colors   渐变色数组
         * @param stops    颜色渐变的位置数组 范围[0-1]，
         *                 可以为null，渐变为线性变化
         *                 如果不为null，顺序从小到大，长度必须和颜色数组相同
         * @param tileMode The Shader tiling mode
         */
        mShader = new RadialGradient(
                250,250,
                250,
                new int[]{Color.RED,Color.BLUE,Color.GREEN},
                new float[]{0,0.8f,1f},
                Shader.TileMode.MIRROR
        );
        /*
         * TODO:为什么不能设置起始角度？改变方向需要旋转画布？
         *
         * 扫描渲染
         * A Shader that draws a sweep gradient around a center point.
         * @param cx       中心点 X 坐标
         * @param cy       中心点 Y 坐标
         * @param colors   渐变色数组
         * @param stops    颜色渐变的位置数组 范围[0-1]，
         *                  可以为null，渐变为线性变化
         *                  如果不为null，顺序从小到大，长度必须和颜色数组相同
         */
        mShader = new SweepGradient(
                250,250,
        new int[]{Color.RED,Color.BLUE,Color.GREEN},
                new float[]{0.1f,0.8f,1f});

        /*
         * 位图渲染 *
         * Call this to create a new shader that will draw with a bitmap.
         *
         * @param bitmap bitmap对象
         * @param tileX  X轴方向的渲染类型
         * @param tileY  Y轴方向的渲染类型
         */
        mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);


        /*
         * 组合渲染  *
         * Create a new compose shader, given shaders A, B, and a combining mode.
         * When the mode is applied, it will be given the result from shader A as its
         * "dst", and the result from shader B as its "src".
         *
         * @param shaderA  The colors from this shader are seen as the "dst" by the mode
         * @param shaderB  The colors from this shader are seen as the "src" by the mode
         * @param mode     The mode that combines the colors from the two shaders. If mode
         *                 is null, then SRC_OVER is assumed.
         */
        mShader = new ComposeShader(
                new LinearGradient(0,0,500,500,
                        new int[]{Color.RED,Color.BLUE,Color.GREEN},
                        null,
                        Shader.TileMode.MIRROR),
                new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP),
                PorterDuff.Mode.DARKEN
//                PorterDuff.Mode.DST
//                PorterDuff.Mode.CLEAR
//                PorterDuff.Mode.MULTIPLY
//                PorterDuff.Mode.DST_ATOP
//                PorterDuff.Mode.DST_IN
//                PorterDuff.Mode.DST_OUT
//                PorterDuff.Mode.DST_OVER
//                PorterDuff.Mode.LIGHTEN
//                PorterDuff.Mode.OVERLAY
        );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawRect(500,500,800,800,mPaint);
        mPaint.setShader(mShader);
        canvas.drawRect(0,0,500,500,mPaint);




        // 回收掉
//        mBitmap.recycle();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
