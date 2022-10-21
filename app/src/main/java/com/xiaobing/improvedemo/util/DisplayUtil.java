package com.xiaobing.improvedemo.util;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.xiaobing.improvedemo.main.IDApplication;

import java.lang.reflect.Method;

/**
 * Created by Joker. 2019/4/11 11:37
 *
 * @E-mail: xiaobing.joker@qq.com
 * @Description: 屏幕尺寸相关的工具类
 */
public class DisplayUtil {

    public static  void  init(Context context){
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }
    }

    private volatile static DisplayMetrics dm;

    public synchronized static DisplayMetrics getDisplayMetrics() {
//        if (dm == null) {
//            synchronized (DisplayUtil.class) {
//                if (dm == null)
//                    dm = IDApplication.getInstance().getResources().getDisplayMetrics();
//            }
//        }
        return dm;
    }

    /**
     * 取得屏幕宽度(像素)
     *
     * @return 屏幕的宽度(像素)
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 取得屏幕高度(像素)
     *
     * @return 屏幕高度(像素)
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * 取得屏幕尺寸(单位：英寸)
     *
     * @return 屏幕的英寸尺寸
     */
    public static float getScreenSize() {
        DisplayMetrics dm = getDisplayMetrics();
        return dm.heightPixels / dm.ydpi;
    }


    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param dpValue dp 值
     * @return px
     */
    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDisplayMetrics().density + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp 值
     * @return px
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param pxValue px
     * @return dp
     */
    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDisplayMetrics().density + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue px
     * @return dp
     */

    public static int px2sp(float pxValue) {
        return (int) (pxValue / getDisplayMetrics().scaledDensity + 0.5f);
    }

    /**
     * 获取虚拟功能键高度
     * 如果获取失败返回0
     * @return 返回虚拟功能键的高度
     *
     */
    public static int getVirtualBarHeight(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null)
            return vh;
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

}
