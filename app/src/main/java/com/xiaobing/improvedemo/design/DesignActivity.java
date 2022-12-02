package com.xiaobing.improvedemo.design;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.activity.BaseActivity;
import com.xiaobing.improvedemo.base.BaseMainAdapter;
import com.xiaobing.improvedemo.link.UriAction;
import com.xiaobing.improvedemo.main.bean.MainBean;
import com.xiaobing.improvedemo.util.ParseLinkUtil;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DesignActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.activity_design;
    }

    @Override
    protected void initView() {
        RecyclerView rvDesign = findViewById(R.id.rv_design);
        setTitle(getString(R.string.ID_design_01));
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
        BaseMainAdapter<RecyclerView.ViewHolder> adapter = new BaseMainAdapter<RecyclerView.ViewHolder>(this, data) {
            @Override
            protected void onClick(String link) {
                ParseLinkUtil.parseLink(DesignActivity.this,link);
            }
        };
        rvDesign.setAdapter(adapter);

    }
}
