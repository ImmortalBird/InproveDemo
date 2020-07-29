package com.xiaobing.improvedemo.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joker. 2020/7/29 11:37
 *
 * @E-mail: xiaobing.joker@qq.com
 * @Description: APP跳转应用商店的工具类
 */
public class MarketUtil {

    /**
     * 启动到应用商店app详情界面
     * 请求系统打开对应包名的APP在应用市场中的主页
     * <p>
     * 如果没有指定 {@param marketPackageName} 该方法只是发出请求，系统会列出所有符合条件的app 供用户选择
     *
     * @param marketPackageName 应用商店包名 ,如果为"" 则由系统弹出应用商店
     *                          列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     *                          商店 包名
     *                          <p>
     *                          Google Play
     *                          com.android.vending
     *                          <p>
     *                          应 用 宝
     *                          com.tencent.android.qqdownloader
     *                          <p>
     *                          360手机助手
     *                          com.qihoo.appstore
     *                          <p>
     *                          百度手机助手
     *                          com.baidu.appsearch
     *                          <p>
     *                          小米应用商店
     *                          com.xiaomi.market
     *                          <p>
     *                          豌 豆 荚
     *                          com.wandoujia.phoenix2
     *                          <p>
     *                          华为应用市场
     *                          com.huawei.appmarket
     *                          <p>
     *                          淘宝手机助手
     *                          com.taobao.appcenter
     *                          <p>
     *                          安卓市场
     *                          com.hiapk.marketpho
     *                          <p>
     *                          安智市场
     *                          cn.goapk.market
     */
    public static void launchAppDetailInMarket(Context context, String marketPackageName) {
        launchAppDetailInMarket(context, context.getPackageName(), marketPackageName);
    }

    /**
     * 跳转到应用市场app详情界面
     *
     * @param context           上下文
     * @param appPackageName    App的包名
     * @param marketPackageName 应用市场包名
     */
    public static void launchAppDetailInMarket(@NonNull Context context,
                                               @NonNull String appPackageName,
                                               @NonNull String marketPackageName) {
        try {
            Uri uri = Uri.parse("market://details?id=" + appPackageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPackageName))
                intent.setPackage(marketPackageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求系统打开对应包名的APP在 应用宝 中的主页
     * <p>
     * 如果没有安装应用宝，会弹出 Toast
     *
     * @param context     上下文
     * @param packageName 要在应用市场中打开的包名
     */
    public static void launchAppDetailInQQDownloader(Context context, String packageName) {
        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            goToMarket.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "您的手机没有安装应用宝", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取本机已安装应用商店的包名列表
     * 获取有在AndroidManifest 里面注册<category android:name="android.intent.category.APP_MARKET" />的app
     *
     * @param context 上下文
     * @return 获取本机已安装应用商店的包名列表
     */
    public static ArrayList<String> queryInstalledMarketPackages(@NonNull Context context) {
        ArrayList<String> packages = new ArrayList<>();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infoList = pm.queryIntentActivities(intent, 0);
        if (infoList.size() == 0)
            return packages;
        int size = infoList.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infoList.get(i).activityInfo;
                pkgName = activityInfo.packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(pkgName))
                packages.add(pkgName);
        }
        return packages;
    }


    /**
     * 获取本机已安装的并且目标app已上架的应用市场列表
     *
     * @param context 上下文
     * @return 本机已安装的应用商店包名列表
     */
    public static ArrayList<String> getInstallAppMarkets(@NonNull Context context) {
        //默认的应用市场列表，有些应用市场没有设置APP_MARKET通过隐式搜索不到
        ArrayList<String> pkgList = new ArrayList<>();
        //将我们上传的应用市场都传上去
        pkgList.add("com.xiaomi.market");                       //小米应用商店
        pkgList.add("com.lenovo.leos.appstore");                //联想应用商店
        pkgList.add("com.oppo.market");                         //OPPO应用商店
        pkgList.add("com.tencent.android.qqdownloader");        //腾讯应用宝
        pkgList.add("com.qihoo.appstore");                      //360手机助手
        pkgList.add("com.baidu.appsearch");                     //百度手机助手
        pkgList.add("com.huawei.appmarket");                    //华为应用商店
        pkgList.add("com.wandoujia.phoenix2");                  //豌豆荚
        pkgList.add("com.hiapk.marketpho");                     //安智应用商店
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        ArrayList<String> packages = queryInstalledMarketPackages(context);
        //取两个list并集,去除重复
        pkgList.retainAll(packages);
        return pkgList;
    }

    /**
     * 从指定集合中 过滤出 已经安装的包名集合
     *
     * @param context          上下文
     * @param packages         待过滤包名集合
     * @param containSystemApp 是否要包含系统app
     *                         true     则包含
     *                         false    则不含
     * @return 已安装的包名集合
     */
    public static ArrayList<String> filterInstalledPackagesByGiven(Context context, ArrayList<String> packages, boolean containSystemApp) {
        ArrayList<String> empty = new ArrayList<>();
        if (context == null || packages == null || packages.size() == 0)
            return empty;
        PackageManager pm = context.getPackageManager();
        if (!containSystemApp) {
            for (PackageInfo packageInfo : pm.getInstalledPackages(0)) {
                // 如果非系统应用，则添加至appList,会过滤掉系统的应用
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    empty.add(packageInfo.packageName);
                }
            }
        }
        empty.retainAll(packages);
        return empty;
    }
}