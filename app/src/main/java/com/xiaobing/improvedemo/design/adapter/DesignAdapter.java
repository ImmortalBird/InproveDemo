package com.xiaobing.improvedemo.design.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.xiaobing.improvedemo.base.BaseAdapter;
import com.xiaobing.improvedemo.design.bean.DesignBean;
import com.xiaobing.improvedemo.base.BaseMainAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class DesignAdapter extends BaseAdapter<BaseMainAdapter.MainHolder> {

    private List<DesignBean> data;

    public DesignAdapter(Context mContext,List<DesignBean> data) {
        super(mContext);
        this.data = data;
    }


    @Override
    protected BaseMainAdapter.MainHolder createHolder(ViewGroup viewGroup, int type) {
        return null;
    }

    @Override
    protected void bindHolder(BaseMainAdapter.MainHolder viewHolder, int position) {

    }
}
