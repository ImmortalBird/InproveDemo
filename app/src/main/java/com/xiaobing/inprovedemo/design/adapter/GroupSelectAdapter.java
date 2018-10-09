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

public class GroupSelectAdapter extends RecyclerView.Adapter {
    private static final int TYPE_GROUP = 10000;
    private static final int TYPE_CHILD = 10001;
    private ArrayList<GroupBean> mobileOSes;
    private Context context;


    public GroupSelectAdapter(ArrayList<GroupBean> mobileOSes, Context context) {
        this.mobileOSes = mobileOSes;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        LayoutInflater from = LayoutInflater.from(context);
        Log.e("GroupSelectAdapter", "onCreateViewHolder itemType = " + itemType);
        switch (itemType) {
            case TYPE_GROUP:
                return new GroupHolder(from.inflate(R.layout.item_group_title, viewGroup, false));
            default:
                return new ChildHolder(from.inflate(R.layout.item_group_child, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        Log.e("GroupSelectAdapter", "onBindViewHolder pos = " + pos + "----- itemType = " + getItemViewType(pos));
        switch (getItemViewType(pos)) {
            case TYPE_GROUP:
                bindGroup((GroupHolder) viewHolder, getGroup(pos));
                break;
            default:
                bindChild((ChildHolder) viewHolder,getGroup(pos), getGroup(pos).getChildren().get(getChildIndex(pos)));
        }

    }

    private void bindGroup(GroupHolder holder, GroupBean groupBean) {
        holder.tvGroup.setText(groupBean.getTitle());
        holder.cbGroup.setChecked(groupBean.isSelected());
        holder.itemView.setOnClickListener(v -> {
            // 2018/9/7 组被点击 开发分组购物车列表
            if (groupBean.isSelected()) {
                groupBean.setSelected(false);
                for (ChildText ct : groupBean.getChildren()) {
                    ct.setSelected(false);
                }
            } else {
                groupBean.setSelected(true);
                for (ChildText ct : groupBean.getChildren()) {
                    ct.setSelected(true);
                }
            }
            notifyDataSetChanged();
        });
    }

    private GroupBean getGroup(int position) {
        int index = 0;
        for (int i = 0; i < mobileOSes.size(); i++) {
            if (position >= index && position < mobileOSes.get(i).getChildren().size() + 1 + index) {
                return mobileOSes.get(i);
            } else {
                index += mobileOSes.get(i).getChildren().size() + 1;
            }
        }
        return new GroupBean("");
    }

    private void bindChild(ChildHolder holder, GroupBean group, ChildText childText) {
        holder.tvChild.setText(childText.getText());
        holder.cbChild.setChecked(childText.isSelected());
        holder.itemView.setOnClickListener(v -> {
            // 2018/9/7 child被点击
            if (childText.isSelected()) {
                childText.setSelected(false);
                group.setSelected(false);
            } else {
                childText.setSelected(true);
                group.setSelected(true);
                for (ChildText ct : group.getChildren()) {
                    if (!ct.isSelected()){
                        group.setSelected(false);
                        break;
                    }
                }
            }
            notifyDataSetChanged();
        });
    }

    private int getChildIndex(int position) {
        GroupBean bean = getGroup(position);
        int groupIndex = mobileOSes.indexOf(bean);
        int delta = 0;
        for (int i = 0; i < groupIndex; i++) {
            delta += mobileOSes.get(i).getChildren().size();
        }
        return position - groupIndex - delta - 1;
    }

    private boolean isGroup(int position) {
        int count = 0;
        if (position == 0)
            return true;
        for (GroupBean gb : mobileOSes) {
            count += gb.getChildren().size() + 1;
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
        int count = 0;
        for (GroupBean gb : mobileOSes) {
            count += gb.getChildren().size() + 1;
        }
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
