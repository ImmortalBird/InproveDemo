package com.xiaobing.inprovedemo.design.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.design.bean.ChildText;
import com.xiaobing.inprovedemo.design.bean.GroupBean;

import java.util.ArrayList;

public class ExpandAdapter extends RecyclerView.Adapter {
    private static final int TYPE_GROUP = 10000;
    private static final int TYPE_CHILD = 10001;
    private ArrayList<GroupBean> mobileOSes;
    private Context context;
    private int expandIndex = -1, closeIndex = -1;
    private int changeSize = 0;
    private int changeAction = 0;


    public ExpandAdapter(ArrayList<GroupBean> mobileOSes, Context context) {
        this.mobileOSes = mobileOSes;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        LayoutInflater from = LayoutInflater.from(context);
        Log.e("ExpandAdapter", "onCreateViewHolder itemType = " + itemType);
        switch (itemType) {
            case TYPE_GROUP:
                return new GroupHolder(from.inflate(R.layout.item_group_title, viewGroup, false));
            default:
                return new ChildHolder(from.inflate(R.layout.item_group_child, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        Log.e("ExpandAdapter", "onBindViewHolder pos = " + pos + "----- itemType = " + getItemViewType(pos));
        switch (getItemViewType(pos)) {
            case TYPE_GROUP:
                bindGroup((GroupHolder) viewHolder, getGroup(pos),pos);
                break;
            default:
                int childIndex = getChildIndex(pos);
                Log.e("ExpandAdapter", "onBindViewHolder childIndex = " + childIndex);
                bindChild((ChildHolder) viewHolder, getGroup(pos), getGroup(pos).getChildren().get(childIndex),pos);
        }

    }

    private void bindGroup(GroupHolder holder, GroupBean groupBean, int pos) {
        holder.tvGroup.setText(groupBean.getTitle());
        holder.cbGroup.setChecked(groupBean.isSelected());
        holder.itemView.setOnClickListener(v -> {
            // 2018/10/9 展开或者收缩分组

            if (!groupBean.isExpand()) {
                groupBean.setExpand(true);
                expandIndex = mobileOSes.indexOf(groupBean);
                closeIndex = -1;
                changeSize = groupBean.getChildren().size();
                notifyItemRangeInserted(holder.getAdapterPosition() + 1, changeSize);
            } else {
                groupBean.setExpand(false);
                closeIndex = mobileOSes.indexOf(groupBean);
                changeSize = 0;
                expandIndex = -1;
                notifyItemRangeRemoved(holder.getAdapterPosition() + 1, groupBean.getChildren().size());
            }
            Log.e("OnClick", "changeSize = " + changeSize);
            Log.e("OnClick", "expandIndex = " + expandIndex);
            Log.e("OnClick", "closeIndex = " + closeIndex);
        });
    }

    private GroupBean getGroup(int position) {
        int index = 0;
        for (int i = 0; i < mobileOSes.size(); i++) {
            if (mobileOSes.get(i).isExpand())
                if (position >= index && position < mobileOSes.get(i).getChildren().size() + 1 + index) {
                    return mobileOSes.get(i);
                } else {
                    index += mobileOSes.get(i).getChildren().size() + 1;
                }
            else if (position >= index && position < 1 + index) {
                return mobileOSes.get(i);
            } else
                index += 1;
        }
        return new GroupBean("");
    }

    private void bindChild(ChildHolder holder, GroupBean group, ChildText childText, int pos) {
        holder.tvChild.setText(childText.getText() + holder.getAdapterPosition() + "---" + pos);
        holder.cbChild.setChecked(childText.isSelected());
        holder.itemView.setOnClickListener(v -> {
            // 2018/9/7 child被点击
//            if (childText.isSelected()) {
//                childText.setSelected(false);
//                group.setSelected(false);
//            } else {
//                childText.setSelected(true);
//                group.setSelected(true);
//                for (ChildText ct : group.getChildren()) {
//                    if (!ct.isSelected()){
//                        group.setSelected(false);
//                        break;
//                    }
//                }
//            }
//            notifyDataSetChanged();
        });
    }

    private int getChildIndex(int position) {
        Log.e("ExpandAdapter", "position = " + position);
        GroupBean bean = getGroup(position);
        int groupIndex = mobileOSes.indexOf(bean);
        Log.e("ExpandAdapter", "groupIndex = " + groupIndex);
        int delta = 0, x = changeSize > 0 ? 1 : 0;
        for (int i = 0; i < groupIndex + x; i++) {
//        for (int i = 0; i < groupIndex; i++) {
            bean = mobileOSes.get(i);
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
//                if(changeSize <= 0){
//            }else{
//                if (!bean.isExpand())
//                    delta += bean.getChildren().size()+1;
//                else
//                    delta += 1;
//            }
        }
        int index = position - delta - 1 + x;
        Log.e("ExpandAdapter", "index = " + index);
        if (index < 0 || index >= bean.getChildren().size()){
            expandIndex = -1;
            closeIndex = -1;
            changeSize = 0;
            return 0;
//            index = getChildIndex(position);
        }

        return index;
    }

    private boolean isGroup(int position) {
        int count = 0;
        if (position == 0)
            return true;
        for (GroupBean gb : mobileOSes) {
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
        for (GroupBean gb : mobileOSes) {
            if (gb.isExpand()) {
                count += gb.getChildren().size() + 1;
            } else count += 1;

        }

        Log.e("ExpandAdapter","getItemCount = " + count);
        return count;
    }

    private int getGroupIndex(int position) {
        int index = 0;
        for (int i = 0; i < mobileOSes.size(); i++) {
            if (position >= index && position < mobileOSes.get(i).getChildren().size() + 1 + index) {
                return i;
            } else {
                index += mobileOSes.get(i).getChildren().size() + 1;
                i--;
            }
        }
        return 0;
    }

    class GroupHolder extends RecyclerView.ViewHolder {

        private CheckBox cbGroup;
        private TextView tvGroup;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            cbGroup = itemView.findViewById(R.id.cb_group);
            tvGroup = itemView.findViewById(R.id.tv_group);
        }
    }

    class ChildHolder extends RecyclerView.ViewHolder {

        private CheckBox cbChild;
        private TextView tvChild;

        public ChildHolder(@NonNull View itemView) {
            super(itemView);
            cbChild = itemView.findViewById(R.id.cb_child);
            tvChild = itemView.findViewById(R.id.tv_child);
        }
    }
}
