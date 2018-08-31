package com.xiaobing.inprovedemo.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.design.DesignActivity;
import com.xiaobing.inprovedemo.link.UriAction;

public class ParseLinkUtil {

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
