package com.xiaobing.inprovedemo.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.base.BaseActivity;
import com.xiaobing.inprovedemo.link.UriAction;
import com.xiaobing.inprovedemo.main.adapter.MainAdapter;
import com.xiaobing.inprovedemo.main.bean.MainBean;
import com.xiaobing.inprovedemo.util.ParseLinkUtil;

import java.util.ArrayList;

public class DesignActivity extends BaseActivity {

    private RecyclerView rvDesign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        rvDesign = findViewById(R.id.rv_design);
        initView();
    }
    private void initView() {
        rvDesign.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<MainBean> data = new ArrayList<>();
        MainBean e = new MainBean();
        e.setName(UriAction.ACTION_EXPAND_RECYCLER_VIEW_ACTIVITY);
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_EXPAND_RECYCLER_VIEW_ACTIVITY));
        data.add(e);
        MainAdapter adapter = new MainAdapter(this, data) {
            @Override
            protected void onClick(String link) {
                ParseLinkUtil.parseLink(DesignActivity.this,link);
            }
        };
        rvDesign.setAdapter(adapter);

    }
}
