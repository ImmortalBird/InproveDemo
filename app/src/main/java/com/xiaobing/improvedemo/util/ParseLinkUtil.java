package com.xiaobing.improvedemo.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.appcompat.AppCompatActivityDemo;
import com.xiaobing.improvedemo.design.DesignActivity;
import com.xiaobing.improvedemo.design.activity.ExpandListActivity;
import com.xiaobing.improvedemo.design.activity.GroupSelectRecyclerViewActivity;
import com.xiaobing.improvedemo.design.activity.ExpandSelectRecyclerViewActivity;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.notification.NotificationActivity;
import com.xiaobing.improvedemo.span.SpanActivity;

public class ParseLinkUtil {


    public static String getLink(String... sss){
        String link = UriAction.SCHEME.concat("://");
        for (String s : sss){
            link= link.concat(s).concat("/");
        }

        return link.substring(0,link.length()-1);
    }

    /**
     * 解析Link，跳转相应页面
     *
     * @param mContext      上下文
     * @param link          链接
     */
    public static boolean parseLink(Activity mContext, String link) {
        boolean isRight = true;
        if (TextUtils.isEmpty(link) || !link.startsWith(mContext.getString(R.string.scheme))) {
            return false;
        }
        Uri uri = Uri.parse(link);
        switch (uri.getAuthority()) {
            case UriAction.ACTION_DESIGN:
                mContext.startActivity(new Intent(mContext, DesignActivity.class));
                isRight = true;
                break;
            case UriAction.ACTION_APP_COMPAT_ACTIVITY:
                mContext.startActivity(new Intent(mContext, AppCompatActivityDemo.class));
                isRight = true;
                break;
            case UriAction.ACTION_GROUP_SELECT_RECYCLER_VIEW_ACTIVITY:
                mContext.startActivity(new Intent(mContext, GroupSelectRecyclerViewActivity.class));
                isRight = true;
                break;
            case UriAction.ACTION_EXPAND_RECYCLER_VIEW_ACTIVITY:
                mContext.startActivity(new Intent(mContext, ExpandListActivity.class));
                isRight = true;
                break;
            case UriAction.ACTION_NOTIFICATION_ACTIVITY:
                mContext.startActivity(new Intent(mContext, NotificationActivity.class));
                isRight = true;
                break;
            case UriAction.ACTION_EXPAND_SELECT_RECYCLER_VIEW_ACTIVITY:
                mContext.startActivity(new Intent(mContext, ExpandSelectRecyclerViewActivity.class));
                isRight = true;
                break;
            case UriAction.ACTION_EXPAND_CLOSE_ACTIVITY:

                break;
            case UriAction.ACTION_SPAN_ACTIVITY:
                mContext.startActivity(new Intent(mContext, SpanActivity.class));
                isRight = true;
                break;
//            case UriAction.PRODUCT:
//                break;
//            case UriAction.LOGIN:
//                isRight = true;
//                break;
//            case UriAction.REGISTER:
//                isRight = true;
//                break;
//            case UriAction.ACTIVITY://跳转到活动 ？？？一期没有
//
//                break;
//            case UriAction.GIFT:
//                FreeGiftActivity.start(mContext);
//                isRight = true;
//                break;
//            case UriAction.USER:
//                String type = uri.getQueryParameter(PARAM_TYPE);
//                pathSegments = uri.getPathSegments();
//                newUser(mContext, type);
//                break;
//            case UriAction.FLASH_SALE:
//                FlashSaleActivity.start(mContext, productId);
//                break;
//            case UriAction.FLASH_SALE_NEW:
//                mId = uri.getQueryParameter(PARAM_ID);
//                pathSegments = uri.getPathSegments();
//                newFlashSale(mContext);
//                break;
        }
        return isRight;
    }

}
