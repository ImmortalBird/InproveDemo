package com.xiaobing.custom.view.lession_1_2_6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.xiaobing.custom.R;

import androidx.annotation.Nullable;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/4/1
 */
public class PathMeasureView extends View {

    private Paint mPaint, mLinePaint, mDestPaint;
    private Path path;
    private PathMeasure pathMeasure, pathMeasure2;
    private Path dst;
    private float[] pos;
    private float[] tan;
    private float mFloat;
    private Matrix mMatrix = new Matrix();
    private Bitmap mBitmap;

    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5F);
        mPaint.setColor(Color.RED);
        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(8F);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mDestPaint = new Paint();
        mDestPaint.setStrokeWidth(8F);
        mDestPaint.setColor(Color.CYAN);
        mDestPaint.setStyle(Paint.Style.STROKE);

        path = new Path();
        dst = new Path();

        pathMeasure = new PathMeasure();
        pathMeasure2 = new PathMeasure();

        pos = new float[2];
        tan = new float[2];

        // 处理图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, options);

//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1f);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setDuration(2000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Log.e("onAnimationUpdate","animation.getAnimatedValue() = "+animation.getAnimatedValue());
//                mFloat = (float) animation.getAnimatedValue();
//                invalidate();
//            }
//        });
//        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制坐标系
        float halfY = getHeight() / 2f;
        float halfX = getWidth() / 2f;
        canvas.drawLine(0, halfY, getWidth(), halfY, mPaint);
        canvas.drawLine(halfX, 0, halfX, getHeight(), mPaint);
        /* ************************** PathMeasure构造和setPath() ****************************** */
//        canvas.save();
//        canvas.translate(halfX, halfY);
//
//        path.lineTo(0,200);
//        path.lineTo(200,200);
//        path.lineTo(200,0);
//
//        // 等同于 PathMeasure pathMeasure = new PathMeasure(path, true);
//        pathMeasure.setPath(path,true);
//
//        Log.e("onDraw","pathMeasure forceClosed = true   " + pathMeasure.getLength());
//        // E/onDraw: pathMeasure forceClosed = true   800.0
//
//
//        pathMeasure2.setPath(path,false);
//
//        Log.e("onDraw","pathMeasure2 forceClosed = false   " + pathMeasure2.getLength());
//        // E/onDraw: pathMeasure forceClosed = true   800.0
//        path.close();
//
//        Log.e("onDraw","pathMeasure forceClosed = true   " + pathMeasure.getLength());
//        // E/onDraw: pathMeasure forceClosed = true   800.0
//        Log.e("onDraw","pathMeasure2 forceClosed = false   " + pathMeasure2.getLength());
//        // E/onDraw: pathMeasure2 forceClosed = false   600.0
//
//
//        pathMeasure.setPath(path,true);
//        Log.e("onDraw","pathMeasure forceClosed = true   " + pathMeasure.getLength());
//        // E/onDraw: pathMeasure forceClosed = true   800.0
//
//
//        pathMeasure2.setPath(path,false);
//        Log.e("onDraw","pathMeasure2 forceClosed = false   " + pathMeasure2.getLength());
//        // E/onDraw: pathMeasure2 forceClosed = false   800.0
//
//
//        /*
//         * 1. pathMeasure 测量出来的长度和 path 是否已经绘制出来无关
//         * 2. forceClosed
//         *          为 true 表示如果path开始点和结束点没有闭合，则连接结束点和开始点添加到测量结果中
//         *          为 false 表示只测量原有Path
//         * 3. 如果path的值改变了，需要对 PathMeasure 重新赋值才可以得到最新的测量结果
//         */
//
//        canvas.drawPath(path, mLinePaint);
//        canvas.restore();
        /* ************************** PathMeasure.getSegment() ****************************** */
//        canvas.save();
//        canvas.translate(halfX, halfY);
//
//        path.addRect(-200,-200, 200,200, Path.Direction.CW);
//
//        dst.lineTo(-300,-300);
//
//        pathMeasure.setPath(path,true);
//        //截取一部分存入dst中，并且使用moveTo保持截取得到的Path第一个点位置不变。
//        /*
//         * 截取一段path，成功后返回true并将结果存放在dst中，失败返回fasle
//         * 不会改变原path
//         * @param startD    截取开始点距离原path开始点的长度
//         * @param stopD     截取结束点距离远path开始点的长度
//         * @param dst       用来存放结果的path对象
//         * @param startWithMoveTo   是否需要改变 截取得到的path的起始点
//         *                          true：无论 dst 之前有没有添加 path，绘制截取得到的path时，
//         *                      以截取得到的path起始点为起始点进行绘制
//         *                          false：如果dst之前添加过path，绘制截取得到的path时，先将
//         *                      截取得到的path的起始点 moveTo dst的结束点上，再进行绘制
//         * @return  是否成功 true 成功；false 失败。
//         */
//        pathMeasure.getSegment(200,1000,dst,true);
//
//
//
//        canvas.drawPath(path, mLinePaint);
//        canvas.drawPath(dst,mDestPaint);
//        canvas.restore();
        /* ************************** PathMeasure.nextContour() ****************************** */
//        // 跳转到下一条path
//        canvas.save();
//        canvas.translate(halfX, halfY);
//
//        path.addRect(-200,-200,0,0, Path.Direction.CW);
//        path.addCircle(100,100,100, Path.Direction.CW);
//        pathMeasure.setPath(path,true);
//        Log.e("onDraw","pathMeasure forceClosed = true   " + pathMeasure.getLength());
////        E/onDraw: pathMeasure forceClosed = true   800.0
//        /*
//         * 跳转到下一条path
//         */
//        pathMeasure.nextContour();
//        Log.e("onDraw","pathMeasure forceClosed = true   " + pathMeasure.getLength());
////        E/onDraw: pathMeasure forceClosed = true   627.30347
//        canvas.drawPath(path, mLinePaint);
//        canvas.restore();
        /* ************************** PathMeasure.getPosTan() ****************************** */
//        // 获取pos tan
//        canvas.save();
//        canvas.translate(halfX, halfY);
//        mFloat = 0.5f;
//
//        path.addCircle(0, 0, 200, Path.Direction.CW);
//
//        pathMeasure.setPath(path,false);
//        /**
//         * distance 取值范围 0 - getLength()
//         * 根据 distance 来确定目标点点p，然后计算点p在路径上的位置和切线，
//         *
//         *
//         * @param distance 路径开始点到目标点的距离
//         * @param pos 目标点坐标
//         * @param tan
//         * @return 如果pathMeasure关联的路径为空或者产孤单为0，返回false
//         */
//        pathMeasure.getPosTan(pathMeasure.getLength() * mFloat,pos,tan);
//
//        Log.e("TAG", "onDraw: pos[0]=" + pos[0] + ";pos[1]=" + pos[1]);
//        // E/TAG: onDraw: pos[0]=-200.0;pos[1]=0.0
//        Log.e("TAG", "onDraw: tan[0]=" + tan[0] + ";tan[1]=" + tan[1]);
//        // E/TAG: onDraw: tan[0]=0.0;tan[1]=-1.0
//
//        canvas.drawPath(path, mLinePaint);
//        canvas.restore();
        /* ************************** PathMeasure.getMatrix() ****************************** */
//        canvas.save();
//        canvas.translate(getWidth()/2f,getHeight()/2f);
//        path.reset();
//        path.addCircle(0,0,200, Path.Direction.CW);
//        canvas.drawPath(path, mLinePaint);
//
//        // 将pos信息和tan信息保存到Matrix中
//        pathMeasure.setPath(path,false);
//
////        mFloat += 0.01f;
////        if (mFloat >= 1){
////            mFloat = 0;
////        }
//
//
//        // 将pos信息和tan信息保存在mMatrix中
//        pathMeasure.getMatrix(pathMeasure.getLength() * mFloat, mMatrix,
//                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
//        // 将图片的旋转坐标调整到图片中心位置
//        mMatrix.preTranslate( -mBitmap.getWidth() /2f,-mBitmap.getHeight() /2f);
//
//        canvas.drawBitmap(mBitmap,mMatrix,mLinePaint);
//        canvas.restore();
////        invalidate();

    }
}
