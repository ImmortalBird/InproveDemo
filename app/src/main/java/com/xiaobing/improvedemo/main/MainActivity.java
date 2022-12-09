package com.xiaobing.improvedemo.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;
import com.xiaobing.improvedemo.link.LinkUtil;
import com.xiaobing.improvedemo.main.adapter.MainAdapter;
import com.xiaobing.improvedemo.network.retrofit.Api;

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

        MainAdapter adapter = new MainAdapter(this, new LinkUtil().getMainBeans());
//        MainAdapter adapter = new MainAdapter(this, getMainBeans());
        rvMain.setAdapter(adapter);
        getData();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
