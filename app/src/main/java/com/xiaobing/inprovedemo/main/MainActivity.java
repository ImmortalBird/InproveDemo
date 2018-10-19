package com.xiaobing.inprovedemo.main;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.base.BaseActivity;
import com.xiaobing.inprovedemo.link.UriAction;
import com.xiaobing.inprovedemo.main.adapter.MainAdapter;
import com.xiaobing.inprovedemo.main.bean.MainBean;
import com.xiaobing.inprovedemo.util.ParseLinkUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        rvMain = findViewById(R.id.rv_main);
        setTitle(getString(R.string.ID_01_01));
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
        e = new MainBean();
        e.setName(UriAction.ACTION_NOTIFICATION_ACTIVITY);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_NOTIFICATION_ACTIVITY));
        data.add(e);
        MainAdapter adapter = new MainAdapter(this, data) {
            @Override
            protected void onClick(String link) {
                ParseLinkUtil.parseLink(MainActivity.this,link);
            }
        };
        ImageView viewById = findViewById(R.id.img);
        rvMain.setAdapter(adapter);

    }
}
