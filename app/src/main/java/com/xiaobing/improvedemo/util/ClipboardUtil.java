package com.xiaobing.improvedemo.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

/**
* Created by Joker. 2020/7/29 11:37
*
* @E-mail: xiaobing.joker@qq.com
* @Description:
*/
class ClipboardUtil {
   private static final String TAG = "ClipboardUtil";

   /**
    * 获取剪贴板中第一条内容
    * @param activity 上下文对象
    * @return 剪贴板第一条的内容，如果剪贴板为空，返回 "-1"
    */
   public static String getPasteString(final Activity activity) {
       // 获取剪贴板内容

       Log.d(TAG, "Thread.currentThread().getId()" + Thread.currentThread().getId());

       ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
       ClipData clipData = null;
       if (clipboard != null) {
           clipData = clipboard.getPrimaryClip();
       }
       if (clipData != null && clipData.getItemCount() > 0) {
           CharSequence text = clipData.getItemAt(0).getText();
           Log.d(TAG, "getFromClipboard text=" + text.toString());
           return text.toString();
       }
       return "-1";

   }

}
