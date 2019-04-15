package com.netease.screenadapter.percentlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/4/15
 */
public class PercentLinearLayout extends LinearLayout {


    public PercentLinearLayout(Context context) {
        super(context);
    }

    public PercentLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取父容器的尺寸
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            if (checkLayoutParams(layoutParams)) {
                LayoutParams lp = (LayoutParams) layoutParams;
                float widthPercent = lp.widthPercent;
                float heightPercent = lp.heightPercent;
                float marginLeftPercent = lp.marginLeftPercent;
                float marginTopPercent = lp.marginTopPercent;
                float marginRightPercent = lp.marginRightPercent;
                float marginBottomPercent = lp.marginBottomPercent;
                if (widthPercent > 0){
                    layoutParams.width = (int) (widthSize * widthPercent);
                }
                if (heightPercent > 0){
                    layoutParams.height = (int) (heightSize * heightPercent);
                }
                if (marginLeftPercent > 0){
                    ((LayoutParams) layoutParams).leftMargin = (int) (widthSize * marginLeftPercent);
                }
                if (marginTopPercent > 0){
                    ((LayoutParams) layoutParams).topMargin = (int) (heightSize * marginTopPercent);
                }
                if (marginRightPercent > 0){
                    ((LayoutParams) layoutParams).rightMargin = (int) (widthSize * marginRightPercent);
                }
                if (marginBottomPercent > 0){
                    ((LayoutParams) layoutParams).bottomMargin = (int) (heightSize * marginBottomPercent);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }
    public static class LayoutParams extends LinearLayout.LayoutParams {

        private float heightPercent;
        private float widthPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;


        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.PercentLinearLayout);
            widthPercent = array.getFloat(R.styleable.PercentLinearLayout_widthPercent, 0f);
            heightPercent = array.getFloat(R.styleable.PercentLinearLayout_heightPercent, 0f);
            marginLeftPercent = array.getFloat(R.styleable.PercentLinearLayout_marginLeftPercent, 0f);
            marginTopPercent = array.getFloat(R.styleable.PercentLinearLayout_marginTopPercent, 0f);
            marginRightPercent = array.getFloat(R.styleable.PercentLinearLayout_marginRightPercent, 0f);
            marginBottomPercent = array.getFloat(R.styleable.PercentLinearLayout_marginBottomPercent, 0f);
            array.recycle();

        }
    }
}
