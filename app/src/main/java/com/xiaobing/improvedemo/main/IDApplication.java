package com.xiaobing.improvedemo.main;

import android.app.Application;
import androidx.multidex.MultiDex;

import com.xiaobing.improvedemo.network.rr2.NetworkManager;
import com.xiaobing.improvedemo.util.DisplayUtil;
import com.xiaobing.improvedemo.util.SPUtil;


/**
 * Created by Administrator on 2018/8/29 0029.
 */

public class IDApplication extends Application {
    public static volatile IDApplication instance;

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
        instance = this;
        NetworkManager.getInstance().init();
        MultiDex.install(this);
        DisplayUtil.init(this);
        SPUtil.init(this);
    }
}
