package com.xiaobing.inprovedemo.design.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.base.BaseActivity;
import com.xiaobing.inprovedemo.design.adapter.ExpandAdapter;
import com.xiaobing.inprovedemo.design.adapter.ExpandSelectAdapter;
import com.xiaobing.inprovedemo.design.bean.ChildText;
import com.xiaobing.inprovedemo.design.bean.GroupBean;

import java.util.ArrayList;

public class ExpandRecyclerViewActivity extends BaseActivity {

    private RecyclerView rv;
    private ArrayList<GroupBean> mobileOSes = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_expand_rv);
        super.onCreate(savedInstanceState);
        rv = findViewById(R.id.rv);
        setTitle(getString(R.string.ID_design_02_02));
        setData();
        rv.setAdapter(new ExpandAdapter(mobileOSes,this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData() {
        ArrayList<ChildText> iphones = new ArrayList<>();
        iphones.add(new ChildText("苹果"));
        iphones.add(new ChildText("橘子"));
        iphones.add(new ChildText("芒果"));
        iphones.add(new ChildText("香蕉"));
        iphones.add(new ChildText("火龙果"));
        iphones.add(new ChildText("草莓"));
        iphones.add(new ChildText("柚子"));
        iphones.add(new ChildText("哈密瓜"));

        ArrayList<ChildText> nexus = new ArrayList<>();
        nexus.add(new ChildText("足球"));
        nexus.add(new ChildText("篮球"));
        nexus.add(new ChildText("乒乓球"));
        nexus.add(new ChildText("棒球"));
        nexus.add(new ChildText("保龄球"));
        nexus.add(new ChildText("溜溜球"));
        nexus.add(new ChildText("橄榄球"));

        ArrayList<ChildText> games = new ArrayList<>();
        games.add(new ChildText("单击游戏"));
        games.add(new ChildText("主机游戏"));
        games.add(new ChildText("FPS游戏"));
        games.add(new ChildText("挂机游戏"));
        games.add(new ChildText("小游戏"));
        games.add(new ChildText("手游"));
        ArrayList<ChildText> windowPhones = new ArrayList<>();
        windowPhones.add(new ChildText("苹果"));
        windowPhones.add(new ChildText("三星"));
        windowPhones.add(new ChildText("华为"));
        windowPhones.add(new ChildText("小米"));
        windowPhones.add(new ChildText("Google"));
        windowPhones.add(new ChildText("360"));
        windowPhones.add(new ChildText("中兴"));
        windowPhones.add(new ChildText("联想"));

        mobileOSes.add(new GroupBean("水果", iphones));
        mobileOSes.add(new GroupBean("球类", nexus));
        mobileOSes.add(new GroupBean("游戏", games));
        mobileOSes.add(new GroupBean("手机", windowPhones));
    }

}
