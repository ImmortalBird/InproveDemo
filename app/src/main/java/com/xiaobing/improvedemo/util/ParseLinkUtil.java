package com.xiaobing.improvedemo.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.animation.AnimationMainActivity;
import com.xiaobing.improvedemo.animation.MatrixActivity;
import com.xiaobing.improvedemo.animation.OpenBookActivity;
import com.xiaobing.improvedemo.appcompat.AppCompatActivityDemo;
import com.xiaobing.improvedemo.custom.CustomViewMainActivity;
import com.xiaobing.improvedemo.design.DesignActivity;
import com.xiaobing.improvedemo.design.activity.ExpandListActivity;
import com.xiaobing.improvedemo.design.activity.GroupSelectRecyclerViewActivity;
import com.xiaobing.improvedemo.design.activity.ExpandSelectRecyclerViewActivity;
import com.xiaobing.improvedemo.font.FontActivity;
import com.xiaobing.improvedemo.io.IOActivity;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.notification.NotificationActivity;
import com.xiaobing.improvedemo.rx2.RxActivity;
import com.xiaobing.improvedemo.service.AIDLActivity;
import com.xiaobing.improvedemo.setting.SettingActivity;
import com.xiaobing.improvedemo.span.SpanActivity;

/**
 * @author 常晓冰
 */
public class ParseLinkUtil {


    public static String getLink(String... sss) {
        String link = UriAction.SCHEME.concat("://");
        for (String s : sss) {
            link = link.concat(s).concat("/");
        }

        return link.substring(0, link.length() - 1);
    }

    /**
     * 解析Link，跳转相应页面
     *
     * @param mContext 上下文
     * @param link     链接
     */
    public static boolean parseLink(Activity mContext, String link) {
        if (TextUtils.isEmpty(link) || !link.startsWith(mContext.getString(R.string.scheme))) {
            return false;
        }
        Uri uri = Uri.parse(link);
        if (uri.getAuthority() != null) {
            switch (uri.getAuthority()) {
                case UriAction.ACTION_DESIGN:
                    mContext.startActivity(new Intent(mContext, DesignActivity.class));
                    return true;
                case UriAction.ACTION_APP_COMPAT_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, AppCompatActivityDemo.class));
                    return true;
                case UriAction.ACTION_GROUP_SELECT_RECYCLER_VIEW_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, GroupSelectRecyclerViewActivity.class));
                    return true;
                case UriAction.ACTION_EXPAND_RECYCLER_VIEW_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, ExpandListActivity.class));
                    return true;
                case UriAction.ACTION_NOTIFICATION_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, NotificationActivity.class));
                    return true;
                case UriAction.ACTION_EXPAND_SELECT_RECYCLER_VIEW_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, ExpandSelectRecyclerViewActivity.class));
                    return true;
                case UriAction.ACTION_EXPAND_CLOSE_ACTIVITY:
                    break;
                case UriAction.ACTION_SPAN_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, SpanActivity.class));
                    return true;
                case UriAction.ACTION_IO_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, IOActivity.class));
                    return true;
                case UriAction.ACTION_RX_ACTIVITY:
                    mContext.startActivity(new Intent(mContext, RxActivity.class));
                    return true;
                case UriAction.ACTION_ANIMATION_MAIN:
                    mContext.startActivity(new Intent(mContext, AnimationMainActivity.class));
                    return true;
                case UriAction.ACTION_ANIMATION_OPEN_BOOK:
                    mContext.startActivity(new Intent(mContext, OpenBookActivity.class));
                    return true;
                case UriAction.ACTION_MATRIX:
                    mContext.startActivity(new Intent(mContext, MatrixActivity.class));
                    return true;
                case UriAction.ACTION_CUSTOM_VIEW_MAIN:
                    mContext.startActivity(new Intent(mContext, CustomViewMainActivity.class));
                    return true;
                case UriAction.ACTION_SETTING_MAIN:
                    mContext.startActivity(new Intent(mContext, SettingActivity.class));
                    return true;
                case UriAction.ACTION_FONT_MAIN:
                    mContext.startActivity(new Intent(mContext, FontActivity.class));
                    return true;
                case UriAction.ACTION_AIDL:
                    mContext.startActivity(new Intent(mContext, AIDLActivity.class));
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

}
