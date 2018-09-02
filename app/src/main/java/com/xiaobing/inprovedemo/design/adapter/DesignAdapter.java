package com.xiaobing.inprovedemo.design.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xiaobing.inprovedemo.base.BaseAdapter;
import com.xiaobing.inprovedemo.design.bean.DesignBean;
import com.xiaobing.inprovedemo.main.adapter.MainAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class DesignAdapter extends BaseAdapter<MainAdapter.MainHolder> {

    private List<DesignBean> data;

    public DesignAdapter(Context mContext,List<DesignBean> data) {
        super(mContext);
        this.data = data;
    }


    @Override
    protected MainAdapter.MainHolder createHolder(ViewGroup viewGroup, int type) {
        return null;
    }

    @Override
    protected void bindHold(MainAdapter.MainHolder viewHolder, int position) {

    }
}
