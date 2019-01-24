package com.xiaobing.improvedemo.design.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.design.adapter.ExpandCloseAdapter;
import com.xiaobing.improvedemo.design.bean.ChildText;
import com.xiaobing.improvedemo.design.recycler.expand.bean.ExpandableGroup;

import java.util.ArrayList;

public class ExpandCloseActivity extends BaseActivity {

    private ArrayList<ExpandableGroup> mobileOSes = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_expand_rv);
        super.onCreate(savedInstanceState);
        RecyclerView rv = findViewById(R.id.rv);
        setTitle(getString(R.string.ID_design_02_03));
        setData();
        rv.setAdapter(new ExpandCloseAdapter(mobileOSes));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setData() {
        ArrayList<ChildText> iphones = new ArrayList<>();
        iphones.add(new ChildText("1苹果"));
        iphones.add(new ChildText("2橘子"));
        iphones.add(new ChildText("3芒果"));
        iphones.add(new ChildText("4香蕉"));
        iphones.add(new ChildText("5火龙果"));
        iphones.add(new ChildText("6草莓"));
        iphones.add(new ChildText("7柚子"));
        iphones.add(new ChildText("8哈密瓜"));
        iphones.add(new ChildText("9西瓜"));
        iphones.add(new ChildText("10葡萄"));
        iphones.add(new ChildText("11柿子"));
        iphones.add(new ChildText("12山竹"));
        iphones.add(new ChildText("13榴莲"));

        ArrayList<ChildText> nexus = new ArrayList<>();
        nexus.add(new ChildText("1足球"));
        nexus.add(new ChildText("2篮球"));
        nexus.add(new ChildText("3乒乓球"));
        nexus.add(new ChildText("4棒球"));
        nexus.add(new ChildText("5保龄球"));
        nexus.add(new ChildText("6溜溜球"));
        nexus.add(new ChildText("7橄榄球"));

        ArrayList<ChildText> games = new ArrayList<>();
        games.add(new ChildText("1单机游戏"));
        games.add(new ChildText("2主机游戏"));
        games.add(new ChildText("3FPS游戏"));
        games.add(new ChildText("4挂机游戏"));
//        games.add(new ChildText("小游戏"));
//        games.add(new ChildText("手游"));
        ArrayList<ChildText> windowPhones = new ArrayList<>();
        windowPhones.add(new ChildText("1苹果"));
        windowPhones.add(new ChildText("2三星"));
        windowPhones.add(new ChildText("3华为"));
        windowPhones.add(new ChildText("4小米"));
        windowPhones.add(new ChildText("5Google"));
        windowPhones.add(new ChildText("6360"));
        windowPhones.add(new ChildText("7中兴"));
        windowPhones.add(new ChildText("8联想"));

//        mobileOSes.add(new GroupBean("水果", iphones));
//        mobileOSes.add(new GroupBean("球类", nexus));
//        mobileOSes.add(new GroupBean("游戏", games));
//        mobileOSes.add(new GroupBean("手机", windowPhones));
    }

}
