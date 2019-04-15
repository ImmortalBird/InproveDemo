package com.netease.screenadapter.density.my;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;


/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * @date Created on 2019/4/15
 * <p>
 * 更改设备的Density来达到适配的效果
 */
public class Density {
    //  2019/4/15 1. 设置一个基准宽度 单位是px
    private static final float WIDTH = 360f;
    // 屏幕密度比
    private static float appDensity;
    // 屏幕缩放密度比
    private static float appScaleDensity;

    public static void setDensity(Application application, Activity activity) {
        /*
         * TODO: 2019/4/15 2. 获取屏幕信息，得到初始density
         */
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0){
            // 给屏幕密度比赋初始值
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
            // TODO: 2019/4/15 添加字体变化回调
        }
        /*
         * TODO: 2019/4/15 3. 修改设备 density
         */
        // 计算目标设备的屏幕密度比
        float targetDensity = displayMetrics.widthPixels / WIDTH;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);





        /*
         * TODO: 2019/4/15 4. 替换Activity的density scaleDensity densityDpi
         */
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }


}
