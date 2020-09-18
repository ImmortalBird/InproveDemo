package com.xiaobing.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Joker. 2020/8/25 14:40
 *
 * @E-mail: xiaobing.joker@qq.com
 * @Description:
 */
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String s = "position = " + position;
        holder.tvHead.setText(s);
    }

    @Override
    public int getItemCount() {
        return 200;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView tvHead;
        ImageView image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvHead = itemView.findViewById(R.id.tvHead);
            image = itemView.findViewById(R.id.image);
        }
    }


}
