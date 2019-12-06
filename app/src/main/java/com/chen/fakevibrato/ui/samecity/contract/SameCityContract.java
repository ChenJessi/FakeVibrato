package com.chen.fakevibrato.ui.samecity.contract;

import com.chen.baselibrary.base.BasePresenter;
import com.chen.baselibrary.base.BaseView;
import com.chen.fakevibrato.ui.my.view.UserFragment;
import com.chen.fakevibrato.ui.samecity.view.SameCityFragment;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public interface SameCityContract {
    interface View extends BaseView {
    }
    abstract class Presenter extends BasePresenter<SameCityFragment> {

    }
}
