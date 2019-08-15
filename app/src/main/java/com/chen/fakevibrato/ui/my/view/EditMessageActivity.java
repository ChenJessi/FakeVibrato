package com.chen.fakevibrato.ui.my.view;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.internal.schedulers.NewThreadWorker;

import android.os.Bundle;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.my.contract.EditMessageContract;
import com.chen.fakevibrato.ui.my.presenter.EditMessagePresenter;

/**
 * 编辑个人资料
 */
public class EditMessageActivity extends BaseActivity<EditMessagePresenter> implements EditMessageContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_message;
    }

    @Override
    protected EditMessagePresenter initPresenter() {
        return new EditMessagePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
