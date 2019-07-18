package com.chen.fakevibrato.ui.home.contract;

import com.chen.fakevibrato.base.BasePresenter;
import com.chen.fakevibrato.base.BaseView;
import com.chen.fakevibrato.ui.home.view.HomeFragment;
import com.chen.fakevibrato.ui.home.view.HomeListFragment;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public interface HomeListContract {
    interface View extends BaseView {
    }
    abstract class Presenter extends BasePresenter<HomeListFragment> {

    }
}
