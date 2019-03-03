package com.xiaobing.improvedemo.util;

import android.util.Log;

import com.xiaobing.improvedemo.BuildConfig;

public class LogUtil {
    private static final String TAG = "TAG - LOG";



    public static void print(){
        print(TAG,"这是一条日志");
    }
    public static void print(String content){
        print(TAG,content);
    }


    public static void print(String tag,String content){
        if (BuildConfig.DEBUG)
            Log.e(tag,content);
    }
}
