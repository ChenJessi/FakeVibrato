package com.chen.fakevibrato.ui.my.contract;

import com.chen.baselibrary.base.BasePresenter;
import com.chen.baselibrary.base.BaseView;
import com.chen.fakevibrato.ui.my.view.EditMessageActivity;
import com.chen.fakevibrato.ui.my.view.EditNormalActivtiy;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public interface EditNormalContract {
    interface View extends BaseView {
    }
    abstract class Presenter extends BasePresenter<EditNormalActivtiy> {

    }
}
