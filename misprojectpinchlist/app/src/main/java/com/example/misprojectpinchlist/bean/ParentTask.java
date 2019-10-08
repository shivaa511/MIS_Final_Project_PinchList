package com.example.misprojectpinchlist.bean;

import com.example.misprojectpinchlist.model.ExpandableListItem;

import java.util.List;


public class ParentTask implements ExpandableListItem {
    private boolean mExpand = false;
    public String name;
    public List<ChildTask> mRootTasks;

    @Override
    public List<?> getChildItemList() {
        return mRootTasks;
    }

    @Override
    public boolean isExpanded() {
        return mExpand;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        mExpand = isExpanded;
    }

    @Override
    public String toString() {
        return "ParentTask{" +
                "mExpand=" + mExpand +
                ", name='" + name + '\'' +
                ", mRootTasks=" + mRootTasks +
                '}';
    }
}
