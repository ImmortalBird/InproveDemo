package com.xiaobing.custom.view.lession_1_2_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import com.xiaobing.custom.R;


public class ColorFilterView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private ColorMatrixColorFilter mColorMatrixColorFilter;
    private LightingColorFilter filter;
    private PorterDuffColorFilter pdcFilter;
    private ColorMatrix cm;
    private ColorMatrixColorFilter cmcFilter;

    public ColorFilterView(Context context) {
        this(context, null);
    }

    public ColorFilterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    float[] colorMatrix = {
            2,0,0,0,0,   //red
            0,1,0,0,0,   //green
            0,0,1,0,0,   //blue
            0,0,0,1,0    //alpha
    };


    private void init() {
        mPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        // 初始化一个颜色过滤器
        // 过滤掉红色
//        filter = new LightingColorFilter(0x00ffff, 0x000000);
        // 过滤掉绿色
//        filter = new LightingColorFilter(0xff00ff, 0x000000);
        // 过滤掉蓝色
//        filter = new LightingColorFilter(0xffff00, 0x000000);
        // 红色加强一点
//        filter = new LightingColorFilter(0xffffff, 0x200000);
        // 蓝色加强一点
        filter = new LightingColorFilter(0xffffff, 0x100020);

        // todo PorterDuff.Mode.DARKEN 颜色加深？
        pdcFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN);

        // 颜色矩阵

        cm = new ColorMatrix();
//        cm = new ColorMatrix(colorMatrix);
        // TODO: 色彩调节，具体用法尚不明确
//        cm.setRotate(0,45);
        //饱和度调节0-无色彩， 1- 默认效果， >1饱和度加强
//        cm.setSaturation(1.2f);
//        //亮度调节
        cm.setScale(1,1,1,1);

        // 矩阵颜色过滤器
        cmcFilter = new ColorMatrixColorFilter(cm);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 100, 0, mPaint);
        mPaint.setColorFilter(cmcFilter);
        canvas.drawBitmap(mBitmap, 100, 600, mPaint);
    }

}
