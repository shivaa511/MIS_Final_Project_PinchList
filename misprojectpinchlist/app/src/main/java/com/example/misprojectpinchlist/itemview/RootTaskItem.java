package com.example.misprojectpinchlist.itemview;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misprojectpinchlist.R;
import com.example.misprojectpinchlist.bean.RootTask;
import com.example.misprojectpinchlist.viewholder.AbstractExpandableAdapterItem;



public class RootTaskItem extends AbstractExpandableAdapterItem {
    private TextView mName;

    private RootTask mCompany;

    @Override
    public int getLayoutResId() {
        return R.layout.item_root;
    }

    @Override
    public void onBindViews(final View root) {
/*
* control item expand and unexpand
* */
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doExpandOrUnexpand();
                Toast.makeText(root.getContext(), "click company：" + mCompany.name, Toast.LENGTH_SHORT).show();

            }
        });
        mName = (TextView) root.findViewById(R.id.tv_name);



    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        float start, target;
        if (expanded) {
            start = 0f;
            target = 90f;
        } else {
            start = 90f;
            target = 0f;
        }




    }


    @Override
    public void onSetViews() {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);

        onExpansionToggled(getExpandableListItem().isExpanded());
        mCompany = (RootTask) model;
        mName.setText(mCompany.name);

    }
}
