package com.xiaobing.improvedemo.main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.joker.common.ITest;
import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.main.adapter.MainAdapter;
import com.xiaobing.improvedemo.main.bean.MainBean;
import com.xiaobing.improvedemo.network.retrofit.Api;
import com.xiaobing.improvedemo.service.RemoteService;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }


    ITest proxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_ActivityTheme);
        super.onCreate(savedInstanceState);
    findViewById(R.id.bind).setOnClickListener(v ->{
            Log.e("Binder","bindService--");
                bindService(new Intent(this, RemoteService.class), new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.e("Binder","onServiceConnected--name = " + name + "--service = "+service);
                        proxy = ITest.Stub.asInterface(service);
                        Log.e("Binder","onServiceConnected-- proxy=" + proxy);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.e("Binder","onServiceDisconnected--" + name);
                    }
                }, BIND_AUTO_CREATE);
        });
        findViewById(R.id.start).setOnClickListener(v ->{
            Log.e("Binder","startService--");
                startService(new Intent(this, RemoteService.class));
        });
        findViewById(R.id.thread).setOnClickListener(v ->{
            Log.e("Binder","new THREAD--");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Log.e("Binder","Thread in activity is alive ");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        });
        findViewById(R.id.add).setOnClickListener(v ->{
            try {
                Log.e("Binder","proxy.add--" + proxy);
                proxy.add(1,2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        findViewById(R.id.send).setOnClickListener(v->{

            try {
                Log.e("Binder","proxy.send--" + proxy);
                proxy.send(1,2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    private void getData() {
        Log.e("getData", "开始");
        Api.getComApi()
                .getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("getData", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("getData", "onError = " + e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String o) {
                        Log.e("getData", "onNext = " + o);
                    }
                });
    }

    @Override
    protected void initView() {
        RecyclerView rvMain = findViewById(R.id.rv_main);
        setTitle(getString(R.string.ID_01_01));
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(this, getMainBeans());
        rvMain.setAdapter(adapter);
        getData();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private ArrayList<MainBean> getMainBeans() {
        ArrayList<MainBean> data = new ArrayList<>();
        data.add(new MainBean.Builder().name(UriAction.ACTION_DESIGN).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_APP_COMPAT_ACTIVITY).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_NOTIFICATION_ACTIVITY).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_SPAN_ACTIVITY).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_IO_ACTIVITY).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_RX_ACTIVITY).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_ANIMATION_MAIN).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_CUSTOM_VIEW_MAIN).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_SETTING_MAIN).build());
        data.add(new MainBean.Builder().name(UriAction.ACTION_FONT_MAIN).build());
        return data;
    }
}
