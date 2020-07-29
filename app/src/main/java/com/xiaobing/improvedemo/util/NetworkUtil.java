package com.xiaobing.improvedemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * 获取设备 SIM 卡 和 移动网络信息
 * 此工具类需要的功能：
 * todo : 1. 判断网络是否通畅
 * todo : 2. 判断网络类型 wifi还是移动网络
 * todo : 3. 检查是否开启移动网络
 * 4. 判断移动网络运营商
 * todo : 5. 判断移动网络类型（2G 3G 4G 5G ）
 * todo : 6. 检查手机是否有sim卡
 */
public class NetworkUtil {


    /**
     * 获取设备拨号运营商
     * 有效
     *
     * @return ["中国电信CTCC":3]["中国联通CUCC:2]["中国移动CMCC":1]["other":0]["无sim卡":-1]
     */
    public static int getSubscriptionOperatorType(Context context) {
        int opeType = -1;
        // No sim
        if (hasNoSim(context)) {
            return opeType;
        }

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return -1;
        }
        String operator = tm.getNetworkOperator();
        // 中国联通
        if ("46001".equals(operator) || "46006".equals(operator) || "46009".equals(operator)) {
            opeType = 2;
            // 中国移动
        } else if ("46000".equals(operator) || "46002".equals(operator) || "46004".equals(operator) || "46007".equals(operator)) {
            opeType = 1;
            // 中国电信
        } else if ("46003".equals(operator) || "46005".equals(operator) || "46011".equals(operator)) {
            opeType = 3;
        } else {
            opeType = 0;
        }
        return opeType;
    }

    /**
     * 获取设备蜂窝网络运营商
     * 有效
     *
     * @return ["中国电信CTCC":3]["中国联通CUCC:2]["中国移动CMCC":1]["other":0]["无sim卡":-1]["数据流量未打开":-2]
     */
    public static int getCellularOperatorType(Context context) {
        int opeType = -1;
        // No sim
        if (hasNoSim(context)) {
            return opeType;
        }
        // Check cellular operator
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return -1;
        }
        String operator = tm.getSimOperator();
        // 中国联通
        if ("46001".equals(operator) || "46006".equals(operator) || "46009".equals(operator)) {
            opeType = 2;
            // 中国移动
        } else if ("46000".equals(operator) || "46002".equals(operator) || "46004".equals(operator) || "46007".equals(operator)) {
            opeType = 1;
            // 中国电信
        } else if ("46003".equals(operator) || "46005".equals(operator) || "46011".equals(operator)) {
            opeType = 3;
        } else {
            opeType = 0;
        }
        return opeType;
    }

    /*
     * 判断数据流量开关是否打开
     * 已失效
     * api
     * {@link ConnectivityManager#getMobileDataEnabled()}
     * @param context 上下文
     * @return 是否开启移动网络
     */
//    public static boolean isMobileDataEnabled(Context context) {
//        try {
//            Method method = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
//            method.setAccessible(true);
//            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            return (Boolean) method.invoke(connectivityManager);
//        } catch (Throwable t) {
//            Log.d("isMobileDataEnabled", "Check mobile data encountered exception");
//            return false;
//        }
//    }

    /**
     * 检查手机是否有sim卡
     * 有效
     * 检查是否有Sim卡
     *      返回 true 代表没有sim
     *      返回 false 代表有sim
     *
     */
    private static boolean hasNoSim(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm == null || TextUtils.isEmpty(tm.getSimOperator());
    }

}
