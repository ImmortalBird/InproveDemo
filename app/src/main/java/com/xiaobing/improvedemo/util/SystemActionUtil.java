package com.xiaobing.improvedemo.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

/**
 * Created by Joker. 2020/7/29 17:14
 *
 * @E-mail: xiaobing.joker@qq.com
 * @Description:
 */
class SystemActionUtil {
    /**
     * 打电话
     */
    public static void callPhone(@NonNull Context mContext,@NonNull String mNumBerString) {
        if(mContext instanceof Activity){
            Intent intent = new Intent(Intent.ACTION_VIEW
                    , Uri.parse("tel:" + mNumBerString));
            Activity mActivity = (Activity) mContext;
            mActivity.startActivity(intent);
        }
    }
}
