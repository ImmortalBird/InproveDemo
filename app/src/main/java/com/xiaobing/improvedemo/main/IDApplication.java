package com.xiaobing.improvedemo.main;

import android.app.Application;

/**
 * Created by Administrator on 2018/8/29 0029.
 */

public class IDApplication extends Application {
    private static IDApplication instance;
    public synchronized static IDApplication getInstance(){
        if (instance == null){
            synchronized(IDApplication.class){
                if (instance == null){
                    instance = new IDApplication();
                }
            }
        }
        return instance;
    }
}
