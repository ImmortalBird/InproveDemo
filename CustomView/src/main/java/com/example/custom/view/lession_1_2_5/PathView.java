package com.example.custom.view.lession_1_2_5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

public class PathView extends View {

    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private PointF mPoint = new PointF();

    public PathView(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint.setColor(Color.RED);

        mPaint.setStrokeWidth(20f);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(getWidth() / 2f, 0);
        mPath.lineTo(getWidth() / 2f, getHeight());
        mPath.moveTo(0, getHeight() / 2.0f);
        mPath.lineTo(getWidth(), getHeight() / 2f);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        canvas.drawPath(mPath, mPaint);
        canvas.translate(getWidth() / 2f, getHeight() / 2.0f);
        mPath.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        // 绘制一阶贝塞尔曲线
//        mPaint.setStyle(Paint.Style.FILL);
//        mPath.lineTo(100, 500);

        // 绘制二阶贝塞尔曲线
//        mPath.quadTo(mPoint.x, mPoint.y, getWidth()-100, getHeight()/2.0f);

        // 绘制三阶贝塞尔曲线
//        mPath.cubicTo(500,100,600,1200,800,500);

        /*
         * forceMoveTo
         *          true：表示强制将点移动到这一段path的起点之后再开始绘制
         *          false：表示从上一段path的终点开始绘制
         */
//        mPath.arcTo(-200, -200, 300, 300, 30, 160, true);
        // 添加一段弧
        mPath.addArc(-100,-100,200,200,-255,255);
        // 添加一个矩形
        mPath.addRect(-200,-200,200,200, Path.Direction.CW);
        // 添加一个环 CW：沿顺时针方向绘制
        mPath.addCircle(0,0,200, Path.Direction.CW);
        //添加一个椭圆 CCW：沿逆时针方向绘制
        mPath.addOval(0,0,500,300, Path.Direction.CCW);


        canvas.drawPath(mPath, mPaint);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(mPoint.x - event.getX())>=100 ||
//                        Math.abs(mPoint.y - event.getY())>=100 ){
//                    mPoint.x = event.getX();
//                    mPoint.y = event.getY();
//                    invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
////        invalidate();
//        return true;
//    }
}
