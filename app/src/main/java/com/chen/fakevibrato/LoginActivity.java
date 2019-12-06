package com.chen.fakevibrato;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.chen.fakevibrato.base.BaseSupportActivity;
import com.chen.fakevibrato.im.UserCache;
import com.chen.fakevibrato.ui.home.presenter.LoginPresenter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseSupportActivity<LoginPresenter> {
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvPsdLogin)
    TextView tvPsdLogin;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    private UserCache userCache;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        constraintLayout.setPadding(0, QMUIDisplayHelper.getStatusBarHeight(this), 0, 0);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivClose, R.id.tvPsdLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                break;
            case R.id.tvPsdLogin:
                break;
        }
    }
}
