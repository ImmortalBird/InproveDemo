package com.xiaobing.improvedemo.main.adapter;

import android.content.Context;

import com.xiaobing.improvedemo.base.BaseMainAdapter;
import com.xiaobing.improvedemo.main.bean.MainBean;

import java.util.List;

/**
 * @author 常晓冰
 */
public class MainAdapter extends BaseMainAdapter {

    public MainAdapter(Context mContext, List<MainBean> data) {
        super(mContext, data);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

}
