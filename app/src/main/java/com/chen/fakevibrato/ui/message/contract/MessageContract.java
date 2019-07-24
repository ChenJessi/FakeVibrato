package com.chen.fakevibrato.ui.message.contract;

import com.chen.fakevibrato.base.BasePresenter;
import com.chen.fakevibrato.base.BaseView;
import com.chen.fakevibrato.ui.message.view.MessageFragment;
import com.chen.fakevibrato.ui.samecity.view.SameCityFragment;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public interface MessageContract {
    interface View extends BaseView {
    }
    abstract class Presenter extends BasePresenter<MessageFragment> {

    }
}
