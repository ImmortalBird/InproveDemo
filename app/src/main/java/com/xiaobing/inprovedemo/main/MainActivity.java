package com.xiaobing.inprovedemo.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.link.UriAction;
import com.xiaobing.inprovedemo.main.adapter.MainAdapter;
import com.xiaobing.inprovedemo.main.bean.MainBean;
import com.xiaobing.inprovedemo.util.ParseLinkUtil;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMain = findViewById(R.id.rv_main);
        initView();

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
        MainAdapter adapter = new MainAdapter(this, data) {
            @Override
            protected void onClick(String link) {
                ParseLinkUtil.parseLink(MainActivity.this,link);
            }
        };
        rvMain.setAdapter(adapter);

    }
}
