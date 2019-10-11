package com.chen.fakevibrato.bean;


import com.chen.fakevibrato.widget.SwipeLayout;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public class SwipeBean {
    private int position = -1;
    private SwipeLayout.DragEdge dragEdge = SwipeLayout.DragEdge.Left;
    private boolean isOpen = false;
    public SwipeBean() {
    }
    public SwipeBean(int position) {
        this.position = position;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public SwipeLayout.DragEdge getDragEdge() {
        return dragEdge;
    }

    public void setDragEdge(SwipeLayout.DragEdge dragEdge) {
        this.dragEdge = dragEdge;
    }



    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
