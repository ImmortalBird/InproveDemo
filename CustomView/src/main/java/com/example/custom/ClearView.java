package com.example.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.custom.base.BaseView;

import androidx.annotation.Nullable;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/4/10
 */
public class ClearView extends BaseView {

    private Paint mBottomPaint;
    private Paint mTopPaint;
    private Bitmap bottom;
    private Bitmap top;
    private Path path;
    private int height;
    private int width;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    public ClearView(Context context) {
        super(context);
    }

    public ClearView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomPaint.setStrokeWidth(10f);
        mBottomPaint.setStyle(Paint.Style.STROKE);
        mBottomPaint.setColor(Color.RED);
        mBottomPaint.setStrokeCap(Paint.Cap.ROUND);
        mBottomPaint.setStrokeJoin(Paint.Join.ROUND);
        mBottomPaint.setDither(true);
        mTopPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopPaint.setStrokeWidth(40f);
        mTopPaint.setStyle(Paint.Style.STROKE);
        mTopPaint.setColor(Color.RED);
        mTopPaint.setStrokeCap(Paint.Cap.ROUND);
        mTopPaint.setStrokeJoin(Paint.Join.ROUND);
        mTopPaint.setDither(true);
//        setLayerType(View.LAYER_TYPE_SOFTWARE,mTopPaint);
        mTopPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        bottom = BitmapFactory.decodeResource(getResources(), R.drawable.bottom);
        top = BitmapFactory.decodeResource(getResources(), R.drawable.top);
        path = new Path();


//        mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mBitmap = Bitmap.createBitmap(bottom.getWidth(), bottom.getHeight() , Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
//        mCanvas.drawColor(Color.parseColor("#c0c0c0"));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("ClearView", "onMeasure--getHeight() = " + getHeight());
        Log.e("ClearView", "onMeasure--getMeasuredHeight() = " + getMeasuredHeight());
        Log.e("ClearView", "onMeasure--getWidth() = " + getWidth());
        Log.e("ClearView", "onMeasure--getMeasuredWidth() = " + getMeasuredWidth());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("ClearView", "onLayout--getHeight() = " + getHeight());
        Log.e("ClearView", "onLayout--getMeasuredHeight() = " + getMeasuredHeight());
        Log.e("ClearView", "onLayout--getWidth() = " + getWidth());
        Log.e("ClearView", "onLayout--getMeasuredWidth() = " + getMeasuredWidth());
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("ClearView", "onDraw--getHeight() = " + getHeight());
        Log.e("ClearView", "onDraw--getMeasuredHeight() = " + getMeasuredHeight());
        Log.e("ClearView", "onDraw--getWidth() = " + getWidth());
        Log.e("ClearView", "onDraw--getMeasuredWidth() = " + getMeasuredWidth());
        super.onDraw(canvas);
//        canvas.translate(getWidth() / 2f, getHeight() / 2f);
//        canvas.scale(2f, 2f, 0, 0);
//        mCanvas.translate(getWidth() / 2f, getHeight() / 2f);
//        mCanvas.scale(2f,2f,0,0);
        canvas.drawBitmap(bottom, 0,0, null);


        mCanvas.drawBitmap(top, 0,0,null);
        mCanvas.drawPath(path, mTopPaint);
        canvas.drawBitmap(mBitmap, 0,0,null);
    }

    private int mLastX;
    private int mLastY;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
//                path.lineTo(event.getX(),event.getY());
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);

                if (dx > 3 || dy > 3)
                    path.lineTo(x, y);

                mLastX = x;
                mLastY = y;
                break;
            default:
                path.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();
        return true;
    }
}
