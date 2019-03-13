package com.xiaobing.improvedemo.animation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.base.BaseAdapter;

/**
 * @author 常晓冰
 */
public class BookAdapter extends BaseAdapter {
    private OnBookClickListener listener;


    public BookAdapter(Context mContext) {
        super(mContext);
    }


    public void setListener(OnBookClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup viewGroup, int type) {
        return new BookHolder(LayoutInflater.from(mContext).inflate(R.layout.item_book,viewGroup,false));
    }

    @Override
    protected void bindHold(RecyclerView.ViewHolder viewHolder, int position) {
        BookHolder bookHolder = (BookHolder) viewHolder;
        bookHolder.itemView.setOnClickListener(v->{
            if (listener != null){
                listener.onItemClick(position,bookHolder.book);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 40;
    }

    public interface OnBookClickListener {
        void onItemClick(int pos, View view);
    }


    class BookHolder extends RecyclerView.ViewHolder{

        ImageView book;
        TextView name;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.iv_book);
            name = itemView.findViewById(R.id.tv_name);
        }

    }
}
