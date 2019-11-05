package com.xiaobing.improvedemo.setting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseAdapter;
import com.xiaobing.improvedemo.setting.bean.SettingBean;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends BaseAdapter<SettingAdapter.SettingHolder> {
    private List<SettingBean> data = new ArrayList<>();
    public SettingAdapter(Context mContext) {
        super(mContext);
    }

    public void setData(ArrayList<SettingBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    protected SettingHolder createHolder(ViewGroup viewGroup, int type) {
        return new SettingHolder(LayoutInflater.from(mContext).inflate(R.layout.item_setting,viewGroup,false));
    }

    @Override
    protected void bindHolder(SettingHolder viewHolder, int position) {
        viewHolder.tvDesc.setText(data.get(position).getName());
        viewHolder.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(data.get(position).getAction())));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SettingHolder extends RecyclerView.ViewHolder{

        TextView tvDesc;
        SettingHolder(@NonNull View itemView) {
            super(itemView);
            tvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
