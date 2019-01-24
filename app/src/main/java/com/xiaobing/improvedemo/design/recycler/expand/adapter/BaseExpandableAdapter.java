package com.xiaobing.improvedemo.design.recycler.expand.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xiaobing.improvedemo.design.recycler.expand.bean.ExpandableGroup;
import com.xiaobing.improvedemo.design.recycler.expand.bean.ExpandableList;
import com.xiaobing.improvedemo.design.recycler.expand.bean.ExpandableListPosition;
import com.xiaobing.improvedemo.design.recycler.expand.controller.ExpandCollapseController;
import com.xiaobing.improvedemo.design.recycler.expand.holder.ChildViewHolder;
import com.xiaobing.improvedemo.design.recycler.expand.holder.GroupViewHolder;
import com.xiaobing.improvedemo.design.recycler.expand.listener.ExpandCollapseListener;
import com.xiaobing.improvedemo.design.recycler.expand.listener.GroupExpandCollapseListener;
import com.xiaobing.improvedemo.design.recycler.expand.listener.OnGroupClickListener;

import java.util.List;

public abstract class BaseExpandableAdapter<GVH extends GroupViewHolder,CVH extends ChildViewHolder> extends RecyclerView.Adapter
        implements ExpandCollapseListener, OnGroupClickListener {

    protected ExpandableList expandableList;
    private ExpandCollapseController expandCollapseController;
    private static final String EXPAND_STATE_MAP = "expandable_recyclerview_adapter_expand_state_map";
    private OnGroupClickListener groupClickListener;
    private GroupExpandCollapseListener expandCollapseListener;


    public BaseExpandableAdapter(List<? extends ExpandableGroup> groups) {
        this.expandableList = new ExpandableList(groups);
        this.expandCollapseController = new ExpandCollapseController(expandableList, this);
    }


    @Override
    public int getItemViewType(int position) {
        return expandableList.getUnflattenedPosition(position).type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ExpandableListPosition.GROUP:
                GVH gvh = onCreateGroupViewHolder(parent, viewType);
                gvh.setOnGroupClickListener(this);
                return gvh;
            case ExpandableListPosition.CHILD:
                CVH cvh = onCreateChildViewHolder(parent, viewType);
                return cvh;
            default:
                throw new IllegalArgumentException("viewType is not valid");
        }
    }

    public abstract GVH onCreateGroupViewHolder(ViewGroup parent, int viewType);

    public abstract  CVH onCreateChildViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = expandableList.getExpandableGroup(listPos);
        switch (listPos.type) {
            case ExpandableListPosition.GROUP:
                onBindGroupViewHolder((GVH) holder, position, group);

                if (isGroupExpanded(group)) {
                    ((GVH) holder).expand();
                } else {
                    ((GVH) holder).collapse();
                }
                break;
            case ExpandableListPosition.CHILD:
                onBindChildViewHolder((CVH) holder, position, group, listPos.childPos);
                break;
        }
    }

    protected abstract void onBindChildViewHolder(CVH holder, int position, ExpandableGroup group, int childPos);

    protected abstract void onBindGroupViewHolder(GVH holder, int position, ExpandableGroup group);

    /**
     * Stores the expanded state map across state loss.
     * <p>
     * Should be called from whatever {@link Activity} that hosts the RecyclerView that {@link
     * BaseExpandableAdapter} is attached to.
     * <p>
     * This will make sure to add the expanded state map as an extra to the
     * instance state bundle to be used in {@link #onRestoreInstanceState(Bundle)}.
     *
     * @param savedInstanceState The {@code Bundle} into which to store the
     * expanded state map
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBooleanArray(EXPAND_STATE_MAP, expandableList.expandedGroupIndexes);
    }
    /**
     * Fetches the expandable state map from the saved instance state {@link Bundle}
     * and restores the expanded states of all of the list items.
     * <p>
     * Should be called from {@link Activity#onRestoreInstanceState(Bundle)}  in
     * the {@link Activity} that hosts the RecyclerView that this
     * {@link BaseExpandableAdapter} is attached to.
     * <p>
     *
     * @param savedInstanceState The {@code Bundle} from which the expanded
     * state map is loaded
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(EXPAND_STATE_MAP)) {
            return;
        }
        expandableList.expandedGroupIndexes = savedInstanceState.getBooleanArray(EXPAND_STATE_MAP);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return expandableList.getVisibleItemCount();
    }

    @Override
    public void onGroupExpanded(int positionStart, int itemCount) {

    }

    @Override
    public void onGroupCollapsed(int positionStart, int itemCount) {

    }

    @Override
    public boolean onGroupClick(int flatPos) {
        if (groupClickListener != null) {
            groupClickListener.onGroupClick(flatPos);
        }
        return expandCollapseController.toggleGroup(flatPos);

    }

    /**
     * @param flatPos The flat list position of the group
     * @return true if the group is expanded, *after* the toggle, false if the group is now collapsed
     */
    public boolean toggleGroup(int flatPos) {
        return expandCollapseController.toggleGroup(flatPos);
    }

    /**
     * @param group the {@link ExpandableGroup} being toggled
     * @return true if the group is expanded, *after* the toggle, false if the group is now collapsed
     */
    public boolean toggleGroup(ExpandableGroup group) {
        return expandCollapseController.toggleGroup(group);
    }

    /**
     * @param flatPos the flattened position of an item in the list
     * @return true if {@code group} is expanded, false if it is collapsed
     */
    public boolean isGroupExpanded(int flatPos) {
        return expandCollapseController.isGroupExpanded(flatPos);
    }

    /**
     * @param group the {@link ExpandableGroup} being checked for its collapsed state
     * @return true if {@code group} is expanded, false if it is collapsed
     */
    public boolean isGroupExpanded(ExpandableGroup group) {
        return expandCollapseController.isGroupExpanded(group);
    }
    /**
     * 从RecyclerView中获取所有的Group数据 {@link ExpandableGroup}
     * The full list of {@link ExpandableGroup} backing this RecyclerView
     *
     * @return the list of {@link ExpandableGroup} that this object was instantiated with
     */
    public List<? extends ExpandableGroup> getGroups() {
        return expandableList.groups;
    }

}
