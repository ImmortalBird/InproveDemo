package com.xiaobing.improvedemo.design.bean;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GroupBean {

    private String title;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setSelectedForAll(boolean selected) {
        setSelected(selected);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).setSelected(selected);
        }
    }

    private boolean isSelected;
    private boolean isExpand;

    private ArrayList<ChildText> children;


    public int position;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public GroupBean(String title) {
        this.title = title;
    }

    public GroupBean(String title, ArrayList<ChildText> children) {
        this.title = title;
        this.children = children;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChildText> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(ArrayList<ChildText> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "GroupBean{" +
                "title='" + title + '\'' +
                ", isSelected=" + isSelected +
                ", isExpand=" + isExpand +
                ", children=" + children +
                ", position=" + position +
                '}';
    }
}
