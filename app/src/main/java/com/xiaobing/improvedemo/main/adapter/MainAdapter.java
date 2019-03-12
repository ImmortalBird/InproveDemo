package com.xiaobing.improvedemo.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseMainAdapter;
import com.xiaobing.improvedemo.main.bean.MainBean;
import com.xiaobing.improvedemo.util.ParseLinkUtil;

import java.util.List;

/**
 * @author 常晓冰
 */
public class MainAdapter extends BaseMainAdapter<BaseMainAdapter.MainHolder> {

    public MainAdapter(Context mContext, List<MainBean> data) {
        super(mContext, data);
    }

    /**
     * 条目点击
     * @param link 条目中的链接
     */
    @Override
    protected void onClick(String link){
        ParseLinkUtil.parseLink((Activity) mContext,link);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

   public static class MainHolder extends  RecyclerView.ViewHolder{
        private TextView tvName;
        MainHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
