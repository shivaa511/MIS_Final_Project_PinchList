package com.example.misprojectpinchlist.itemview;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misprojectpinchlist.R;
import com.example.misprojectpinchlist.bean.ParentTask;
import com.example.misprojectpinchlist.model.ExpandableListItem;
import com.example.misprojectpinchlist.viewholder.AbstractExpandableAdapterItem;

import java.util.List;


public class ParentTaskItem extends AbstractExpandableAdapterItem implements View.OnClickListener {
    private TextView mName;
    private ImageView mArrow;
    private TextView mExpand;



    @Override
    public void onClick(View view) {
/*
* control item expand and unepand
* */
        if (getExpandableListItem() != null && getExpandableListItem().getChildItemList() != null) {
            if (getExpandableListItem().isExpanded()) {
                collapseView();
            } else {
                expandView();
            }

        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        float start, target;
        if (expanded) {
            mExpand.setText("unexpand");
            start = 0f;
            target = 90f;
        } else {
            mExpand.setText("expand");
            target = 0f;
            start = 90f;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mArrow, View.ROTATION, start, target);
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_department;
    }

    @Override
    public void onBindViews(View root) {
        mName = (TextView) root.findViewById(R.id.tv_name);
        mArrow = (ImageView) root.findViewById(R.id.iv_arrow);
        mExpand = (TextView) root.findViewById(R.id.tv_expand);
        mExpand.setText("expand");
        mExpand.setOnClickListener(this);
    }

   @Override
    public void onSetViews() {
        mArrow.setVisibility( View.GONE);
    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        onSetViews();
        ParentTask parentTask = (ParentTask) model;
        mName.setText( parentTask.name);
        ExpandableListItem parentListItem= (ExpandableListItem) model;
        List<?> childItemList=parentListItem.getChildItemList();
        if (childItemList!=null&&!childItemList.isEmpty()){
            mArrow.setVisibility( View.VISIBLE);
        }

    }
}
