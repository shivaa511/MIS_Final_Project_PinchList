package com.example.misprojectpinchlist.bean;

import com.example.misprojectpinchlist.model.ExpandableListItem;

import java.util.List;



public class RootTask implements ExpandableListItem {

    public boolean mExpanded = false;
    public String name;
    public List<ParentTask> mParentTasks;

    @Override
    public List<?> getChildItemList() {
        return mParentTasks;
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        mExpanded = isExpanded;
    }

    @Override
    public String toString() {
        return "RootTask{" +
                "name='" + name + '\'' +
                '}';
    }
}
