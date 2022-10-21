package com.xiaobing.improvedemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.xiaobing.improvedemo.main.IDApplication;

public class SPUtil {
    private static final String DEFAULT = "config";
    public static  void  init(Context context){
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(DEFAULT, Context.MODE_PRIVATE);
        }
    }

    private static SharedPreferences sharedPreferences;

    public static void saveString(String key,String value){
        getEditor().putString(key,value).apply();
    }

    private static SharedPreferences.Editor getEditor() {
        SharedPreferences.Editor edit;
        edit = sharedPreferences.edit();
        return edit;
    }
    public static String getString(String key,String defaultValue){
        return sharedPreferences.getString(key,defaultValue);
    }
}
