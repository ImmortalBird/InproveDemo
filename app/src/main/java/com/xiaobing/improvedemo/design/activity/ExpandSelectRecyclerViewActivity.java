package com.xiaobing.improvedemo.design.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseActivity;
import com.xiaobing.improvedemo.design.adapter.ExpandSelectAdapter;
import com.xiaobing.improvedemo.design.bean.ChildText;
import com.xiaobing.improvedemo.design.bean.GroupBean;

import java.util.ArrayList;

public class ExpandSelectRecyclerViewActivity extends BaseActivity {

    private RecyclerView rv;
    private ArrayList<GroupBean> mobileOSes = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv = findViewById(R.id.rv);
        setTitle(getString(R.string.ID_design_02_01));
        setData();
        rv.setAdapter(new ExpandSelectAdapter(mobileOSes,this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_expand_rv;
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

        ArrayList<ChildText> windowPhones = new ArrayList<>();
        windowPhones.add(new ChildText("单击游戏"));
        windowPhones.add(new ChildText("主机游戏"));
        windowPhones.add(new ChildText("FPS游戏"));
        windowPhones.add(new ChildText("挂机游戏"));
        windowPhones.add(new ChildText("小游戏"));
        windowPhones.add(new ChildText("手游"));

        mobileOSes.add(new GroupBean("水果", iphones));
        mobileOSes.add(new GroupBean("球类", nexus));
        mobileOSes.add(new GroupBean("游戏", windowPhones));
    }

}
