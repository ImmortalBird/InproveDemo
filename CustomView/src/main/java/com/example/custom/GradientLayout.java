package com.example.custom;

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
    private Bitmap mBitmap;
    private Shader mShader;
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
        setBackgroundColor(Color.parseColor("#000000"));
        // 初始化画笔
        mPaint = new Paint();
       // 设置画笔线条宽度
        mPaint.setStrokeWidth(50F);
        // 设置抗锯齿
        mPaint.setAntiAlias(true);
//        mPaint2.setAntiAlias(true);

        // 设置填充方式为填充
//        mPaint.setStyle(Paint.Style.FILL);
        // 设置填充方式为描边
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置填充方式为 填充加描边
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        // 设置画笔颜色
//        mPaint.setColor(Color.RED);
        mPaint.setColor(Color.parseColor("#ACEDDD"));
        // 设置画笔颜色 argb
//        mPaint.setARGB(100,100,100,100);
        // 设置 alpha 不透明度 0-255， 0：完全透明；255：完全不透明；默认255
//        mPaint.setAlpha(10);
        ///  2019/3/27 没有出现圆角效果
        // 设置画笔线条两端的样式
//        // 圆形
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        // 常规 默认
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        // 方形
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        // TODO: 2019/3/27 三种拐角风格各自的表现样式 是什么？
        // 两线交汇样式为锐角
        mPaint.setStrokeJoin(Paint.Join.MITER);
        // 两线交汇样式为平角
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        // 两线交汇样式为圆角
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        // 设置环形渲染器
//          mPaint.setShader(new SweepGradient(200,200,Color.BLUE,Color.GREEN));
        // TODO: 设置图层混合模式
//          mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        // TODO: 2019/3/27  设置颜色过滤器
//        mPaint.setColorFilter(new LightingColorFilter(0x00ffff, 0x000000));
        // TODO: 2019/3/27  设置双线性过滤?
//        mPaint.setFilterBitmap(true);
        //
//        mPaint.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.OUTER));
//        mPaint2.setMaskFilter(new BlurMaskFilter(30,BlurMaskFilter.Blur.SOLID));

        // 解析一个map对象
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tutu);
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

        mShader = new LinearGradient(0, 0, 500, 500,
                new int[]{Color.RED, Color.BLUE, Color.GREEN},
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
                250, 250,
                250,
                new int[]{Color.RED, Color.BLUE, Color.GREEN},
                new float[]{0, 0.8f, 1f},
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
                250, 250,
                new int[]{Color.RED, Color.BLUE, Color.GREEN},
                new float[]{0.1f, 0.8f, 1f});

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
                new LinearGradient(0, 0, 500, 500,
                        new int[]{Color.RED, Color.BLUE, Color.GREEN},
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


//        canvas.drawRect(600, 600, 800, 800, mPaint);
        // 设置滤镜
//        mPaint.setShader(mShader);
//        canvas.drawRect(0, 0, 500, 500, mPaint);


        canvas.drawLine(100,100,200,100,mPaint);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100,200,200,200,mPaint);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100,300,200,300,mPaint);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100,400,200,400,mPaint);
        // 回收掉
//        mBitmap.recycle();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
