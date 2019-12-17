package com.xiaobing.custom.custom.vp;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/3/29
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1.0f;
    public static final float MIN_SCALE = 0.6f;

    /**
     * 当处于最中间的view往左边滑动时，它的position值是小于0的，并且是越来越小,它右边的view的position是从1逐渐减小到0的。
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {
        // 适配低版本
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
        Log.e("transformPage", "position = " + position);

        float slope = (MAX_SCALE - MIN_SCALE) / 1.5F;

        float scaleValue = MAX_SCALE - Math.abs(position) * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
        page.setAlpha(scaleValue);
    }
}