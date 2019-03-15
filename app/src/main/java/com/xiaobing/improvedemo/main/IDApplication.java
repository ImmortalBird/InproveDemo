package com.xiaobing.improvedemo.main;

import android.app.Application;
import androidx.multidex.MultiDex;


/**
 * Created by Administrator on 2018/8/29 0029.
 */

public class IDApplication extends Application {
    private static volatile IDApplication instance;
    public static IDApplication getInstance(){
        if (instance == null){
            synchronized(IDApplication.class){
                if (instance == null){
                    instance = new IDApplication();
                }
            }
        }
        return instance;
    }
    public static IDApplication getInstance2(){
        return SingletonHolder.SINGLETON;
    }
    private static class SingletonHolder{
        private static final IDApplication SINGLETON = new IDApplication();

    }

    @Override
    public void onCreate() {
        super.onCreate();
//        NetworkManager.getInstance().init();
        MultiDex.install(this);
    }
}
