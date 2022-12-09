package com.xiaobing.improvedemo.base;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.main.bean.MainBean;

import java.util.List;

/**
 * @author 常晓冰
 */
public abstract class BaseMainAdapter extends BaseAdapter<BaseMainAdapter.MainHolder>{

    protected List<MainBean> data;
    private final LayoutInflater from;

    protected BaseMainAdapter(Context mContext, List<MainBean> data) {
        super(mContext);
        this.data = data;
        from = LayoutInflater.from(mContext);
    }

    @Override
    protected MainHolder createHolder(ViewGroup viewGroup, int type) {
        return new MainHolder(from.inflate(R.layout.item_main,viewGroup,false));
    }

    @Override
    protected void bindHolder(MainHolder mHolder, int i) {
        mHolder.tvName.setText(data.get(i).getName());
        mHolder.tvName.setOnClickListener(v-> {
            try {
                MainBean mainBean = data.get(i);
                Class<?> aClass = Class.forName(mainBean.packageName + "." + mainBean.className);
                mContext.startActivity(new Intent(mContext, aClass));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

   public static class MainHolder extends  RecyclerView.ViewHolder{
        private final TextView tvName;
        MainHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
