package com.xiaobing.improvedemo.design.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xiaobing.improvedemo.R;
import com.xiaobing.improvedemo.design.bean.ChildText;
import com.xiaobing.improvedemo.design.bean.GroupBean;

import java.util.ArrayList;

public class ExpandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_GROUP = 10000;
    private static final int TYPE_CHILD = 10001;
    private final ArrayList<GroupBean> data;
    private int expandIndex = -1, closeIndex = -1;
    private int changeSize = 0;


    public ExpandAdapter(ArrayList<GroupBean> mobileOSes) {
        this.data = mobileOSes;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        Log.e("ExpandAdapter", "onCreateViewHolder itemType = " + itemType);
        if (itemType == TYPE_GROUP) {
            return new GroupHolder(from.inflate(R.layout.item_group_title, viewGroup, false));
        }
        return new ChildHolder(from.inflate(R.layout.item_group_child, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        Log.e("ExpandAdapter", "onBindViewHolder pos=" + pos + "  itemType=" + getItemViewType(pos) + "  AdapterPosition=" + viewHolder.getAdapterPosition());
        GroupBean group = getGroup(pos);
        if (getItemViewType(pos) == TYPE_GROUP) {
            bindGroup((GroupHolder) viewHolder, group, pos);
        } else {
            int childIndex = pos - group.position - 1;
            ChildText childText = group.getChildren().get(childIndex);
            Log.e("ExpandAdapter", "onBindViewHolder childIndex=" + childIndex + "  childText=" + childText);
            bindChild((ChildHolder) viewHolder, group, childText, pos);
        }

    }

    private void bindGroup(GroupHolder holder, GroupBean groupBean, int pos) {
        holder.tvGroup.setText(groupBean.getTitle());
        holder.cbGroup.setChecked(groupBean.isSelected());
        holder.cbGroup.setOnClickListener(v -> {
            groupBean.setSelectedForAll(!groupBean.isSelected());
            notifyItemRangeChanged(pos, groupBean.getChildren()
                    .size() + 1);
        });
        holder.itemView.setOnClickListener(v -> {
            // 2018/10/9 展开或者收缩分组

            if (!groupBean.isExpand()) {
                groupBean.setExpand(true);
                expandIndex = data.indexOf(groupBean);
                closeIndex = -1;
                changeSize = groupBean.getChildren().size();
                notifyItemRangeInserted(holder.getAdapterPosition() + 1, changeSize);
                notifyItemRangeChanged(holder.getAdapterPosition() + 1,getItemCount());
            } else {
                groupBean.setExpand(false);
                closeIndex = data.indexOf(groupBean);
                changeSize = 0;
                expandIndex = -1;
                notifyItemRangeRemoved(holder.getAdapterPosition() + 1, groupBean.getChildren().size());
                notifyItemRangeChanged(holder.getAdapterPosition() + 1,getItemCount());
            }
            Log.e("OnClick", "changeSize = " + changeSize);
            Log.e("OnClick", "expandIndex = " + expandIndex);
            Log.e("OnClick", "closeIndex = " + closeIndex);
        });
    }

    private GroupBean getGroup(int position) {
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            GroupBean group = data.get(i);
            if (group.isExpand())
                if (position >= index && position < group.getChildren().size() + 1 + index) {
                    group.position = index;
                    return group;
                } else {
                    index += group.getChildren().size() + 1;
                }
            else if (position >= index && position < 1 + index) {
                return group;
            } else
                index += 1;
        }
        return new GroupBean("");
    }

    private void bindChild(ChildHolder holder, GroupBean group, ChildText childText, int pos) {
        String text = childText.getText() + holder.getAdapterPosition() + "---" + pos;
        holder.tvChild.setText(text);
        holder.cbChild.setChecked(childText.isSelected());
        holder.itemView.setOnClickListener(v -> {
            // 2018/9/7 child被点击
            if (childText.isSelected()) {
                childText.setSelected(false);
                if (group.isSelected()) {
                    group.setSelected(false);
                    notifyItemChanged(group.position);
                }
                notifyItemChanged(pos);
            } else {
                childText.setSelected(true);
                notifyItemChanged(pos);
                group.setSelected(true);
                for (ChildText ct : group.getChildren()) {
                    if (!ct.isSelected()) {
                        if (group.isSelected()) {
                            group.setSelected(false);

                        }

                        break;
                    }
                }
                notifyItemChanged(group.position);
            }
        });
    }

    private int getChildIndex(int position) {
        Log.e("ExpandAdapter", "position = " + position);
        GroupBean bean = getGroup(position);
        int groupIndex = data.indexOf(bean);
        Log.e("ExpandAdapter", "groupIndex=" + groupIndex + "  group=" + bean);
        int delta = 0, x = changeSize > 0 ? 1 : 0;
        for (int i = 0; i < groupIndex + x; i++) {
            bean = data.get(i);
            if (i == expandIndex && changeSize > 0) {
                delta += 1;
                changeSize--;
                if (changeSize == 0)
                    expandIndex = -1;
            } else if (closeIndex == i) {
                delta += 1;
                closeIndex = -1;
                changeSize = 0;
            } else {
                if (bean.isExpand())
                    delta += bean.getChildren().size() + 1;
                else
                    delta += 1;
            }
        }
        int index = position - delta - 1 + x;
        Log.e("ExpandAdapter", "index = " + index);
        if (index < 0 || index >= bean.getChildren().size()) {
            expandIndex = -1;
            closeIndex = -1;
            changeSize = 0;
            return 0;
        }

        return index;
    }

    private boolean isGroup(int position) {
        int count = 0;
        if (position == 0)
            return true;
        for (GroupBean gb : data) {
            if (gb.isExpand()) count += gb.getChildren().size() + 1;
            else count += 1;
            if (position == count)
                return true;
        }
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        if (isGroup(position))
            return TYPE_GROUP;
        return TYPE_CHILD;
    }

    @Override
    public int getItemCount() {
        // 2018/10/9 计算条目总数
        /*
         * 1. 判断分组是否是展开状态
         *      如果是：加上子条目的数量和当前组所占的一个条目数量
         *      如果否：只加上 当前组所占的一个条目数量
         */
        int count = 0;
        for (GroupBean gb : data) {
            if (gb.isExpand()) {
                count += gb.getChildren().size() + 1;
            } else count += 1;
        }

        Log.e("ExpandAdapter", "getItemCount = " + count);
        return count;
    }

    private int getGroupIndex(int position) {
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (position >= index && position < data.get(i).getChildren().size() + 1 + index) {
                return i;
            } else {
                index += data.get(i).getChildren().size() + 1;
                i--;
            }
        }
        return 0;
    }

    static class GroupHolder extends RecyclerView.ViewHolder {

        private final CheckBox cbGroup;
        private final TextView tvGroup;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            cbGroup = itemView.findViewById(R.id.cb_group);
            tvGroup = itemView.findViewById(R.id.tv_group);
        }
    }

    static class ChildHolder extends RecyclerView.ViewHolder {

        private final CheckBox cbChild;
        private final TextView tvChild;

        public ChildHolder(@NonNull View itemView) {
            super(itemView);
            cbChild = itemView.findViewById(R.id.cb_child);
            tvChild = itemView.findViewById(R.id.tv_child);
        }
    }
}
