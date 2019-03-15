package com.xiaobing.improvedemo.base;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author 常晓冰
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    protected Context mContext;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return createHolder(viewGroup, getItemViewType(i));
    }

    /**
     * 根据type 返回相应的 Holder
     *
     * @param viewGroup 父控件 即：RecyclerView
     * @param type      类型
     * @return Holder
     */
    protected abstract T createHolder(ViewGroup viewGroup, int type);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            bindHold((T) viewHolder, i);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 数据处理
     *
     * @param viewHolder holder
     * @param position   对应条目的索引值
     */
    protected abstract void bindHold(T viewHolder, int position);
}
