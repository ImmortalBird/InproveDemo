package com.xiaobing.improvedemo.design.recycler.expand.adapter;

import android.view.ViewGroup;

import com.xiaobing.improvedemo.design.recycler.expand.bean.ExpandableGroup;
import com.xiaobing.improvedemo.design.recycler.expand.holder.CHolder;
import com.xiaobing.improvedemo.design.recycler.expand.holder.GHolder;

import java.util.List;

public class ExAdapter extends BaseExpandableAdapter<GHolder,CHolder> {
    public ExAdapter(List list) {
        super(list);
    }


    @Override
    public GHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public CHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindChildViewHolder(CHolder holder, int position, ExpandableGroup group, int childPos) {

    }

    @Override
    protected void onBindGroupViewHolder(GHolder holder, int position, ExpandableGroup group) {

    }
}
