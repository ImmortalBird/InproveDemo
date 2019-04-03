package com.example.custom.view.lession_1_2_5;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
//import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.custom.R;

import androidx.annotation.Nullable;

/**
 * QQ气泡效果
 */
public class DragBubbleView extends View {

    /**
     * 气泡默认状态--静止
     */
    private final int BUBBLE_STATE_DEFAULT = 0;
    /**
     * 气泡相连
     */
    private final int BUBBLE_STATE_CONNECT = 1;
    /**
     * 气泡分离
     */
    private final int BUBBLE_STATE_APART = 2;
    /**
     * 气泡消失
     */
    private final int BUBBLE_STATE_DISMISS = 3;

    /**
     * 气泡半径
     */
    private float mBubbleRadius = 50F;
    /**
     * 气泡颜色
     */
    private int mBubbleColor = Color.RED;
    /**
     * 气泡消息文字
     */
    private String mTextStr = "";
    /**
     * 气泡消息文字颜色
     */
    private int mTextColor = Color.WHITE;
    /**
     * 气泡消息文字大小
     */
    private float mTextSize = 16f;
    /**
     * 不动气泡的半径
     */
    private float mBubFixedRadius;
    /**
     * 可动气泡的半径
     */
    private float mBubMovableRadius;
    /**
     * 不动气泡的圆心
     */
    private PointF mBubFixedCenter;
    /**
     * 可动气泡的圆心
     */
    private PointF mBubMovableCenter;
    /**
     * 气泡的画笔
     */
    private Paint mBubblePaint;
    /**
     * 贝塞尔曲线path
     */
    private Path mBezierPath;

    private Paint mTextPaint;

    //文本绘制区域
    private Rect mTextRect;

    private Paint mBurstPaint;

    //爆炸绘制区域
    private Rect mBurstRect;

    /**
     * 气泡状态标志
     */
    private int mBubbleState = BUBBLE_STATE_DEFAULT;
    /**
     * 两气泡圆心距离
     */
    private float mDist;
    /**
     * 气泡相连状态最大圆心距离
     */
    private float mMaxDist;
    /**
     * 手指触摸偏移量
     */
//    private final float MOVE_OFFSET;

    /**
     * 气泡爆炸的bitmap数组
     */
    private Bitmap[] mBurstBitmapsArray;
    /**
     * 是否在执行气泡爆炸动画
     */
    private boolean mIsBurstAnimStart = false;

    /**
     * 当前气泡爆炸图片index
     */
    private int mCurDrawableIndex;

    /**
     * 气泡爆炸的图片id数组
     */
    private int[] mBurstDrawablesArray = {
            R.mipmap.burst_1, R.mipmap.burst_2, R.mipmap.burst_3, R.mipmap.burst_4, R.mipmap.burst_5
    };

    public DragBubbleView(Context context) {
        this(context, null);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DragBubbleView, defStyleAttr, 0);
        mBubbleRadius = array.getDimension(R.styleable.DragBubbleView_bubble_radius,mBubbleRadius);
        mBubbleColor = array.getColor(R.styleable.DragBubbleView_bubble_color,mBubbleColor);
        mTextStr = array.getString(R.styleable.DragBubbleView_bubble_text) == null ?
                mTextStr:array.getString(R.styleable.DragBubbleView_bubble_text);
        mTextSize = array.getDimension(R.styleable.DragBubbleView_bubble_textSize,mTextSize);
        mTextColor = array.getColor(R.styleable.DragBubbleView_bubble_textColor,mTextColor);
        // TypedArray对象在使用后必须 recycle()
        array.recycle();
        mBubFixedRadius = mBubbleRadius;
        mBubMovableRadius = mBubFixedRadius;

        mBubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBubblePaint.setColor(mBubbleColor);
        mBubblePaint.setStyle(Paint.Style.FILL_AND_STROKE);







    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        init(w, h);
    }

    private void init(int w, int h) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (mBubbleState != BUBBLE_STATE_DISMISS) {
//                    mDist = (float) Math.hypot(event.getX() - mBubFixedCenter.x, event.getY() - mBubFixedCenter.y);
//                    if (mDist < mBubbleRadius + MOVE_OFFSET) {
//                        //加上MOVE_OFFSET是为了方便拖拽
//                        mBubbleState = BUBBLE_STATE_CONNECT;
//                    } else {
//                        mBubbleState = BUBBLE_STATE_DEFAULT;
//                    }
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (mBubbleState != BUBBLE_STATE_DEFAULT) {
//                    mDist = (float) Math.hypot(event.getX() - mBubFixedCenter.x, event.getY() - mBubFixedCenter.y);
//                    mBubMovableCenter.x = event.getX();
//                    mBubMovableCenter.y = event.getY();
//                    if (mBubbleState == BUBBLE_STATE_CONNECT) {
//                        if (mDist < mMaxDist - MOVE_OFFSET) {
//                            mBubFixedRadius = mBubbleRadius - mDist / 8;
//                        } else {
//                            mBubbleState = BUBBLE_STATE_APART;
//                        }
//                    }
//                    invalidate();
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                if (mBubbleState == BUBBLE_STATE_CONNECT) {
//                    // 橡皮筋动画
//                    startBubbleRestAnim();
//                } else if (mBubbleState == BUBBLE_STATE_APART) {
//                    if (mDist < 2 * mBubbleRadius){
//                        //反弹动画
//                        startBubbleRestAnim();
//                    }else{
//                        // 爆炸动画
//                        startBubbleBurstAnim();
//                    }
//                }
//                break;
//        }
        return true;
    }

    /**
     * 连接状态下松开手指，执行类似橡皮筋动画
     */
    private void startBubbleRestAnim() {
    }

    /**
     * 爆炸动画
     */
    private void startBubbleBurstAnim() {
    }

    public void init(){
    }


}
