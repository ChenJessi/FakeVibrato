package com.chen.fakevibrato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by CHEN on 2019/6/17
 * @email 188669@163.com
 */
public class TestAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<List<String>> mList = new ArrayList<List<String>>();

    public TestAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i = 0; i < 20; i++) {
            List<String> list = new ArrayList<String>();
            list.add("child:"+1);
            list.add("child:"+2);
            list.add("child:"+3);
            list.add("child:"+4);
            list.add("child:"+5);
            list.add("child:"+6);
            list.add("child:"+7);
            list.add("child:"+8);
            list.add("child:"+9);
            list.add("child:"+10);
            list.add("child:"+11);
            mList.add(list);
        }
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).size();
    }

    @Override
    public List<String> getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.group_test, parent, false);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.tvGroup.setText("GROUP : " + groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.child_test, parent, false);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.tvChild.setText("CHILD : " + childPosition + " msg :"+mList.get(groupPosition).get(childPosition));
        holder.tvChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(groupPosition).add("添加测试");
                onGroupCollapsed(groupPosition);
                onGroupExpanded(groupPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private class GroupHolder  {
        private TextView tvGroup;
        public GroupHolder(View view) {
            tvGroup =  view.findViewById(R.id.tvGroup);
        }
    }

    private class ChildHolder  {
        private TextView tvChild;
        public ChildHolder(View view) {
            tvChild =  view.findViewById(R.id.tvChild);
        }
    }
}
