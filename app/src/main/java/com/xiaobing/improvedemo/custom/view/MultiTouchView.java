package com.xiaobing.improvedemo.custom.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.xiaobing.improvedemo.R;


/**
 * 多指触控实现图片跟随最后一个接触屏幕的手指滑动
 */
public class MultiTouchView extends View {

    Bitmap bitmap;
    Paint paint;

    float downX;
    float downY;
    private float offsetX, offsetY, lastOffsetX, lastOffsetY;


    /**
     * 当前手指的id
     */
    int currentPointerId;

    public MultiTouchView(Context context) {
        this(context, null);
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 初始化
    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tutu);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
            //  单指触控
//         switch (event.getAction()) {
//             case MotionEvent.ACTION_DOWN:
//                 // 按下位置
//                 downX = event.getX();
//                 downY = event.getY();
//
//                 break;
//             case MotionEvent.ACTION_MOVE:
//                 // 偏移量
//                 offsetX = lastOffsetX + event.getX() - downX;
//                 offsetY = lastOffsetY + event.getY() - downY;
//
//                 if (offsetX + bitmap.getWidth() > getWidth()) offsetX = getWidth() - bitmap.getWidth();
//                 else if (offsetX < 0) offsetX = 0;
//                 if (offsetY + bitmap.getHeight() > getHeight()) offsetY = getHeight() - bitmap.getHeight();
//                 else if (offsetY < 0) offsetY = 0;
//
//                 invalidate();
//                 break;
//             case MotionEvent.ACTION_UP:
//                 lastOffsetX = offsetX;
//                 lastOffsetY = offsetY;
//                 break;
//         }
        switch (event.getActionMasked()) {
            // 第一根手指按下
            case MotionEvent.ACTION_DOWN:
                // 按下位置
                downX = event.getX();
                downY = event.getY();
                currentPointerId = 0;
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
                break;
            // 非第一根手指按下
            case MotionEvent.ACTION_POINTER_DOWN:
                // 按下位置
                int actionIndex = event.getActionIndex();
                currentPointerId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
                break;
            //所有 手指移动都会触发
            case MotionEvent.ACTION_MOVE:
                // 根据id 找到对应的index， 因为index 是会变化的，id是不变的，必须通过这个方法找，而不能记录下来，
                int index = event.findPointerIndex(currentPointerId);
//                int index = event.getActionIndex();


                // 偏移量
                offsetX = lastOffsetX + event.getX(index) - downX;
                offsetY = lastOffsetY + event.getY(index) - downY;

                if (offsetX + bitmap.getWidth() > getWidth())
                    offsetX = getWidth() - bitmap.getWidth();
                else if (offsetX < 0) offsetX = 0;
                if (offsetY + bitmap.getHeight() > getHeight())
                    offsetY = getHeight() - bitmap.getHeight();
                else if (offsetY < 0) offsetY = 0;

                invalidate();
                break;
            // 第一根手指抬起
            case MotionEvent.ACTION_UP:
                lastOffsetX = offsetX;
                lastOffsetY = offsetY;
                performClick();
                break;
            // 非第一根手指抬起
            case MotionEvent.ACTION_POINTER_UP:
                int upIndex = event.getActionIndex();
                int upId = event.getPointerId(upIndex);
                if (upId == currentPointerId) {
//                    currentPointerId = event.getPointerId(event.getPointerCount() - 1);
                    if (upIndex == event.getPointerCount() - 1) {
                        upIndex--;
                    } else {
                        upIndex++;
                    }
                    currentPointerId = event.getPointerId(upIndex);
                    downX = event.getX(upIndex);
                    downY = event.getY(upIndex);
                    lastOffsetX = offsetX;
                    lastOffsetY = offsetY;
                }
                break;
        }
        return true;
    }
}
