package com.xiaobing.improvedemo.util;

import android.content.Context;
import android.provider.Settings;

import java.util.Locale;

/**
 * 获取系统信息和设备硬件信息的工具类
 * <p>
 * <p>
 * <p>
 * 获取当前系统语言
 *
 * @see #getSystemLanguage
 * <p>
 * 获取当前系统的语言列表
 * @see #getSystemLanguageList
 * <p>
 * 获取当前手机系统版本号
 * @see #getSystemVersion()
 * <p>
 * 获取手机型号
 * @see #getSystemModel()
 * <p>
 * 获取手机品牌
 * @see #getDeviceBrand()
 *
 * 更多设备信息 见末行注释{@link android.os.Build}
 *
 */
public class SystemInfoUtil {
    /**
     * 获取安卓唯一标识
     * 系统初次启动时会生成一个16位的字符串，刷机或者升级系统时会改变
     * 例如：4c4516563665addc
     * 有个很常见的Bug会导致设备产生相同的Android ID: 9774d56d682e549c {@link package.MyClass.SubClass}
     * {@link package.MyClass.SubClass}
     * @param context 上下文 可以使用Application来代替，
     *                 变成 Settings.System.getString(YourApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
     *                这样就不需要传递上下文对象了
     * @return  AndroidId
     */
    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     * 返回值如 : Nexus 5
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     * 结果如下（可能会大小写不一致）
     * 华为：Huawei
     * 荣耀：HONOR
     * 小米：Xiaomi
     * 一加：
     * OPPO：
     * VIVO：
     * 魅族：Meizu
     * 三星：samsung
     * 锤子：
     * 联想：Lenovo
     * 中兴：zte
     * 手机模拟器：generic
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /*
     * android.os.Build.MANUFACTURER; //硬件制造商
     * android.os.Build.BRAND; //手机品牌
     * android.os.Build.DEVICE; //设备名
     * android.os.Build.MODEL; //用户可见的手机型号
     * android.os.Build.DISPLAY; //显示屏参数
     * android.os.Build.PRODUCT; //产品名称（手机厂商）
     *
     * 并不能作为唯一设备标识的设备指纹
     * android.os.Build.FINGERPRINT;//设备唯一识别码
     *
     * android.os.Build.CPU_ABI;//CPU的指令集
     * android.os.Build.CPU_ABI2;//CPU的指令集2
     * android.os.Build.ID;//修订版本列表
     * android.os.Build.TAGS;//描述Build的标签
     */
}