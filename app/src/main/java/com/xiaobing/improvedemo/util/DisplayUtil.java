package com.xiaobing.improvedemo.util;


import com.xiaobing.improvedemo.main.IDApplication;

/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/4/11
 */
public class DisplayUtil {

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param dpValue dp 值
     * @return px
     */
    public static int dip2px(float dpValue) {
        float scale = IDApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp 值
     * @return px
     */
    public static int sp2px(float spValue) {
        final float fontScale = IDApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param pxValue px
     * @return dp
     */
    public static int px2dip(float pxValue) {
        float scale = IDApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue px
     * @return dp
     */

    public static int px2sp(float pxValue) {
        final float fontScale = IDApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}
