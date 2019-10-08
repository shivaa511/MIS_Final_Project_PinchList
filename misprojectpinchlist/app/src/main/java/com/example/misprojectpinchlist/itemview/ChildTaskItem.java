package com.example.misprojectpinchlist.itemview;

import android.view.View;
import android.widget.TextView;

import com.example.misprojectpinchlist.R;
import com.example.misprojectpinchlist.bean.ChildTask;
import com.example.misprojectpinchlist.viewholder.AbstractAdapterItem;



public class ChildTaskItem extends AbstractAdapterItem {
    private TextView mName;

    @Override
    public int getLayoutResId() {
        return R.layout.item_employee;
    }

    @Override
    public void onBindViews(View root) {
        mName = (TextView) root.findViewById(R.id.tv_name);
    }

    @Override
    public void onSetViews() {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        if (model instanceof ChildTask) {
            ChildTask childTask = (ChildTask) model;
            mName.setText( childTask.name);
        }
    }
}
