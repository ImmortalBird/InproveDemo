package com.xiaobing.inprovedemo.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.base.BaseAdapter;
import com.xiaobing.inprovedemo.main.bean.MainBean;

import java.util.List;

public abstract class MainAdapter extends BaseAdapter<MainAdapter.MainHolder>{

    private List<MainBean> data;
    private LayoutInflater from;

    public MainAdapter(Context mContext, List<MainBean> data) {
        super(mContext);
        this.data = data;
        from = LayoutInflater.from(mContext);
    }

    @Override
    protected MainHolder createHolder(ViewGroup viewGroup, int type) {
        return new MainHolder(from.inflate(R.layout.item_main,viewGroup,false));
    }

    @Override
    protected void bindHold(MainHolder mHolder, int i) {
        mHolder.tvName.setText(data.get(i).getName());
        mHolder.tvName.setOnClickListener(v->onClick(data.get(i).getLink()));
    }

    protected abstract void onClick(String link);


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
