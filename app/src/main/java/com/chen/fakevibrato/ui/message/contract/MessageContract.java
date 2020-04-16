package com.chen.fakevibrato.ui.message.contract;

import com.chen.baselibrary.base.DBasePresenter;
import com.chen.baselibrary.base.BaseView;
import com.chen.fakevibrato.ui.message.view.MessageFragment;

/**
 * @author Created by CHEN on 2019/7/18
 * @email 188669@163.com
 */
public interface MessageContract {
    interface View extends BaseView {
    }
    abstract class Presenter extends DBasePresenter<MessageFragment> {

    }
}
