package com.chen.fakevibrato.ui.my.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.my.contract.EditMessageContract;
import com.chen.fakevibrato.ui.my.presenter.EditMessagePresenter;
import com.chen.fakevibrato.widget.glide.GlideApp;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;

/**
 * 编辑个人资料
 */
public class EditMessageActivity extends BaseActivity<EditMessagePresenter> implements EditMessageContract.View {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivHead)
    QMUIRadiusImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvIntroduction)
    TextView tvIntroduction;
    @BindView(R.id.tvSchool)
    TextView tvSchool;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        initToolbar(toolbar);
        GlideApp.with(EditMessageActivity.this)
                .load(R.mipmap.logo)
                .transform(new ColorFilterTransformation(0x79000000))
                .into(ivHead);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ivBack, R.id.ivHead, R.id.tvName, R.id.tvNumber, R.id.tvIntroduction, R.id.tvSchool, R.id.tvGender, R.id.tvBirthday, R.id.tvArea})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivHead:
                showHeadDialog();
                break;
            case R.id.tvName:
//                startEdit();
                break;
            case R.id.tvNumber:
                break;
            case R.id.tvIntroduction:
                break;
            case R.id.tvSchool:
                break;
            case R.id.tvGender:
                showGenderDialog();
                break;
            case R.id.tvBirthday:
                break;
            case R.id.tvArea:
                break;
        }
    }


    private void showHeadDialog() {
        final String[] items = new String[]{"拍一张", "相册选择", "查看大图", "取消"};
        new QMUIDialog.MenuDialogBuilder(EditMessageActivity.this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    private void showGenderDialog() {
        final String[] items = new String[]{"男", "女", "不显示"};
        new QMUIDialog.MenuDialogBuilder(EditMessageActivity.this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    /**
     * 编辑
     *
     * @param type
     */
    private void startEdit(int type) {
        Intent intent = new Intent(EditMessageActivity.this, EditNormalActivtiy.class);
        startActivity(intent);
    }
}
