package com.xiaobing.custom.view.lession_1_2_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.xiaobing.custom.R;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * 交流讨论滤镜问题
 */
public class ColorFilterViewQ extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private ColorMatrixColorFilter mColorMatrixColorFilter;
    private LightingColorFilter filter;

    public ColorFilterViewQ(Context context) {
        this(context, null);
    }

    public ColorFilterViewQ(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterViewQ(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        // 初始化一个颜色过滤器
        filter = new LightingColorFilter(0x00ffff, 0x000000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 100, 0, mPaint);
        mPaint.setColorFilter(filter);
        canvas.drawBitmap(mBitmap, 100, 600, mPaint);
    }
}

