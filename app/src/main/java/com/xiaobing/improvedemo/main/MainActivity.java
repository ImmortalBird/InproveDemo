package com.xiaobing.improvedemo.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.main.adapter.MainAdapter;
import com.xiaobing.improvedemo.main.bean.MainBean;
import com.xiaobing.improvedemo.network.retrofit.Api;
import com.xiaobing.improvedemo.util.ParseLinkUtil;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        rvMain = findViewById(R.id.rv_main);
        setTitle(getString(R.string.ID_01_01));
        initView();
//        getData();
    }

    private void getData() {
        Log.e("getData","开始");
        Api.getComApi().getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("getData","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("getData","onError = " + e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String o) {
                        Log.e("getData","onNext = " + o);
                    }
                });
    }

    private void initView() {
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<MainBean> data = new ArrayList<>();
        MainBean e = new MainBean();
        e.setName(UriAction.ACTION_DESIGN);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_DESIGN));
        data.add(e);
        e = new MainBean();
        e.setName(UriAction.ACTION_APP_COMPAT_ACTIVITY);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_APP_COMPAT_ACTIVITY));
        data.add(e);
        e = new MainBean();
        e.setName(UriAction.ACTION_NOTIFICATION_ACTIVITY);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_NOTIFICATION_ACTIVITY));
        data.add(e);
        e = new MainBean();
        e.setName(UriAction.ACTION_SPAN_ACTIVITY);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_SPAN_ACTIVITY));
        data.add(e);
        e = new MainBean();
        e.setName(UriAction.ACTION_IO_ACTIVITY);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_IO_ACTIVITY));
        data.add(e);
        MainAdapter adapter = new MainAdapter(this, data) {
            @Override
            protected void onClick(String link) {
                ParseLinkUtil.parseLink(MainActivity.this,link);
            }
        };
        rvMain.setAdapter(adapter);

    }
}
