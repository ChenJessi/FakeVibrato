package com.chen.fakevibrato.bean;

import com.chen.fakevibrato.widget.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by CHEN on 2019/6/17
 * @email 188669@163.com
 */
public class CommentBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<CommentChildBean> {

    private String content;
    private List<CommentChildBean> list;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentChildBean> getList() {
        return list;
    }

    public void setList(List<CommentChildBean> list) {
        this.list = list;
    }

    @Override
    public int getChildCount() {
        return list.size();
    }

    @Override
    public CommentChildBean getChildAt(int childIndex) {
        if (list == null){
            return null;
        }
        return list.get(childIndex);
    }

    @Override
    public boolean isExpandable() {
        return true;
    }
}
