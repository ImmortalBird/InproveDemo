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
        setContentView(R.layout.activity_design);
        super.onCreate(savedInstanceState);
        rvDesign = findViewById(R.id.rv_design);
        setTitle(getString(R.string.ID_design_01));
        initView();
    }
    private void initView() {
        rvDesign.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<MainBean> data = new ArrayList<>();
        MainBean e = new MainBean();
        e.setName(getString(R.string.ID_design_01_02));
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_GROUP_SELECT_RECYCLER_VIEW_ACTIVITY));
        data.add(e);
        e = new MainBean();
        e.setName(getString(R.string.ID_design_02_01));
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_EXPAND_SELECT_RECYCLER_VIEW_ACTIVITY));
        data.add(e);
        e = new MainBean();
        e.setName(getString(R.string.ID_design_02_02));
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_EXPAND_RECYCLER_VIEW_ACTIVITY));
        data.add(e);
        e = new MainBean();
        e.setName(getString(R.string.ID_design_02_03));
        e.setLink(ParseLinkUtil.getLink(UriAction.ACTION_EXPAND_CLOSE_ACTIVITY));
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
