package com.xiaobing.improvedemo.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.main.adapter.MainAdapter;
import com.xiaobing.improvedemo.main.bean.MainBean;
import com.xiaobing.improvedemo.network.retrofit.Api;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author 常晓冰
 * @E-mail 471342365@qq.com
 * 主Activity，其他Activity的入口
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_ActivityTheme);
        super.onCreate(savedInstanceState);
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

    /**
     * 这些东西不需要手写，可以使用APT生成
     * @return 主页的内容列表
     */
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
        data.add(new MainBean.Builder().name(UriAction.ACTION_AIDL).build());
        return data;
    }
}
