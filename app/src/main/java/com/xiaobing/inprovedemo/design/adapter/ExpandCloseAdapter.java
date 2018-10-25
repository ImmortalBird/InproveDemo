package com.xiaobing.inprovedemo.design.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xiaobing.inprovedemo.R;
import com.xiaobing.inprovedemo.design.bean.ChildText;
import com.xiaobing.inprovedemo.design.bean.GroupBean;
import com.xiaobing.inprovedemo.design.bean.expand.bean.ExpandableGroup;
import com.xiaobing.inprovedemo.design.bean.expand.bean.ExpandableList;
import com.xiaobing.inprovedemo.design.bean.expand.bean.ExpandableListPosition;
import com.xiaobing.inprovedemo.design.bean.expand.controller.ExpandCollapseController;
import com.xiaobing.inprovedemo.design.bean.expand.holder.GroupViewHolder;
import com.xiaobing.inprovedemo.design.bean.expand.listener.ExpandCollapseListener;
import com.xiaobing.inprovedemo.design.bean.expand.listener.GroupExpandCollapseListener;
import com.xiaobing.inprovedemo.design.bean.expand.listener.OnGroupClickListener;

import java.util.ArrayList;
import java.util.List;

public class ExpandCloseAdapter extends RecyclerView.Adapter
        implements ExpandCollapseListener, OnGroupClickListener {
    private ExpandableList expandableList;
    private ExpandCollapseController expandCollapseController;

    private OnGroupClickListener groupClickListener;
    private GroupExpandCollapseListener expandCollapseListener;

    public ExpandCloseAdapter(List<? extends ExpandableGroup> groups) {
        this.expandableList = new ExpandableList(groups);
        this.expandCollapseController = new ExpandCollapseController(expandableList, this);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    private GroupHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_title, parent, false);
        return new GroupHolder(view);
    }

    private ChildHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_group_child, parent, false);
        return new ChildHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = expandableList.getExpandableGroup(listPos);
        switch (listPos.type) {
            case ExpandableListPosition.GROUP:
                onBindGroupViewHolder((GroupHolder) holder, position, group);

                if (isGroupExpanded(group)) {
                    ((GroupHolder) holder).expand();
                } else {
                    ((GroupHolder) holder).collapse();
                }
                break;
            case ExpandableListPosition.CHILD:
                onBindChildViewHolder((ChildHolder) holder, position, group, listPos.childPos);
                break;
        }
    }

    private void onBindChildViewHolder(ChildHolder holder, int position, ExpandableGroup group, int childPos) {

    }

    private void onBindGroupViewHolder(GroupHolder holder, int position, ExpandableGroup group) {

    }

    private boolean isGroupExpanded(ExpandableGroup group) {
        return expandCollapseController.isGroupExpanded(group);
    }

    @Override
    public int getItemViewType(int position) {
        return expandableList.getUnflattenedPosition(position).type;
    }

    @Override
    public int getItemCount() {
        return expandableList.getVisibleItemCount();
    }

    @Override
    public void onGroupExpanded(int positionStart, int itemCount) {
        //update header
        int headerPosition = positionStart - 1;
        notifyItemChanged(headerPosition);

        // only insert if there items to insert
        if (itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
            if (expandCollapseListener != null) {
                int groupIndex = expandableList.getUnflattenedPosition(positionStart).groupPos;
                expandCollapseListener.onGroupExpanded(getGroups().get(groupIndex));
            }
        }

    }

    @Override
    public void onGroupCollapsed(int positionStart, int itemCount) {
        //update header
        int headerPosition = positionStart - 1;
        notifyItemChanged(headerPosition);

        // only insert if there items to insert
        if (itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
            if (expandCollapseListener != null) {
                int groupIndex = expandableList.getUnflattenedPosition(positionStart).groupPos;
                expandCollapseListener.onGroupExpanded(getGroups().get(groupIndex));
            }
        }
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        if (groupClickListener != null) {
            groupClickListener.onGroupClick(flatPos);
        }
        return expandCollapseController.toggleGroup(flatPos);
    }

    class GroupHolder extends GroupViewHolder {

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
        public void setName(String name){
            tvChild.setText(name);
        }
        public ChildHolder(@NonNull View itemView) {
            super(itemView);
            cbChild = itemView.findViewById(R.id.cb_child);
            tvChild = itemView.findViewById(R.id.tv_child);
        }
    }
    /**
     * The full list of {@link ExpandableGroup} backing this RecyclerView
     *
     * @return the list of {@link ExpandableGroup} that this object was instantiated with
     */
    public List<? extends ExpandableGroup> getGroups() {
        return expandableList.groups;
    }

}
