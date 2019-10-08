package com.example.misprojectpinchlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.example.misprojectpinchlist.adapter.BaseExpandableAdapter;
import com.example.misprojectpinchlist.bean.ChildTask;
import com.example.misprojectpinchlist.bean.ParentTask;
import com.example.misprojectpinchlist.bean.RootTask;
import com.example.misprojectpinchlist.itemview.RootTaskItem;
import com.example.misprojectpinchlist.itemview.ParentTaskItem;
import com.example.misprojectpinchlist.itemview.ChildTaskItem;
import com.example.misprojectpinchlist.viewholder.AbstractAdapterItem;
import com.example.misprojectpinchlist.viewholder.PinchItemTouchListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements PinchItemTouchListener.PinchZoomListener{
  private static final String TAG = "MainActivity";
  private final int ITEM_TYPE_ROOT_TASK = 1;
  private final int ITEM_TYPE_PARENT_TASK = 2;
  private final int ITEM_TYPE_CHILD_TASK = 3;
  private RecyclerView mRecyclerView;
  private BaseExpandableAdapter mBaseExpandableAdapter;
  private ArrayList mRootTaskList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar ab = getSupportActionBar();
    ab.setDisplayHomeAsUpEnabled(false);
    mRecyclerView = (RecyclerView) findViewById(R.id.rcv);
       /* final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecyclerView != null) {
                    Log.d("tag", "fab");
                    RootTask root = (RootTask) mBaseExpandableAdapter.getDataList().get(0);
                    company.name = root.name + "-has changed";
                 //   mBaseExpandableAdapter.modifyItem(0, root);
                }


            }
        });*/
    initData();
    mBaseExpandableAdapter = new BaseExpandableAdapter(mRootTaskList) {
      @NonNull
      @Override
      public AbstractAdapterItem<Object> getItemView(Object type) {
        int itemType = (int) type;
        switch (itemType) {

          case ITEM_TYPE_ROOT_TASK:
            return new RootTaskItem();
          case ITEM_TYPE_PARENT_TASK:
            return new ParentTaskItem();
          case ITEM_TYPE_CHILD_TASK:
            return new ChildTaskItem();
        }
        return null;
      }

      @Override
      public Object getItemViewType(Object t) {
        if (t instanceof RootTask) {
          return ITEM_TYPE_ROOT_TASK;
        } else if (t instanceof ParentTask) {
          return ITEM_TYPE_PARENT_TASK;
        } else if (t instanceof ChildTask) {
          return ITEM_TYPE_CHILD_TASK;
        }
        return -1;
      }
    };
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mRecyclerView.setAdapter(mBaseExpandableAdapter);
    // mRecyclerView.addOnItemTouchListener(new PinchItemTouchListener(this, this));
    mRecyclerView.addOnItemTouchListener(new PinchItemTouchListener(this, this));
    // mRecyclerView.addOnItemTouchListener();
  }

  private void initData() {
    mRootTaskList = new ArrayList<>();
    mRootTaskList.add(createParentTask("Task-1", true));
    mRootTaskList.add(createParentTask("Task-2", true));
    mRootTaskList.add(createParentTask("Task-3", true));
    mRootTaskList.add(createParentTask("Task-4", true));
    mRootTaskList.add(createParentTask("Task-5", true));
    mRootTaskList.add(createParentTask("Task-6", true));
    mRootTaskList.add(createParentTask("Task-7", true));
  }

  private Object createParentTask(String parentTaskname, boolean isExpandDefault) {
    RootTask firstRootTask = new RootTask();
    firstRootTask.name = parentTaskname;
    List<ParentTask> parentTasks = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ParentTask parentTask = new ParentTask();
      parentTask.name = "ParentTask:" + i;

      List<ChildTask> childTaskList = new ArrayList<>();
      for (int j = 0; j < 2; j++) {
        ChildTask childtask = new ChildTask();
        childtask.name = "ChildTask:" + j;
        childTaskList.add(childtask);
      }
      parentTask.mRootTasks = childTaskList;
      parentTasks.add( parentTask );
    }
    firstRootTask.mParentTasks = parentTasks;
    firstRootTask.mExpanded = isExpandDefault;
    return firstRootTask;
  }

  @Override public void onPinchZoomOut(int position) {
    mBaseExpandableAdapter.onParentListItemExpanded(position - 1);
  }

  @Override public void onPinchZoomIn(int position) {
    Log.d(TAG, "onPinchZoomIn: " + position);
    mBaseExpandableAdapter.onParentListItemCollapsed(position-2);
  }
}
