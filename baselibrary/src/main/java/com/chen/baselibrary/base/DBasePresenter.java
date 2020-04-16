package com.chen.baselibrary.base;

/**
 * @author Created by CHEN on 2019/6/11
 * @email 188669@163.com
 */
@Deprecated
public abstract class DBasePresenter<V extends BaseView> {
    protected final String TAG = this.getClass().getSimpleName();

    protected V mView;

    public DBasePresenter() {
    }
    public void attachView(V view){
        this.mView = view;
    }
    protected V getContext(){
        return  mView;
    }
}
