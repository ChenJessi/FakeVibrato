package com.chen.fakevibrato.ui.my.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chen.fakevibrato.R;
import com.chen.fakevibrato.base.BaseActivity;
import com.chen.fakevibrato.ui.my.contract.EditMessageContract;
import com.chen.fakevibrato.ui.my.presenter.EditMessagePresenter;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.widget.glide.GlideApp;
import com.contrarywind.view.WheelView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.Calendar;
import java.util.Date;

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

    private TimePickerView pvTime;

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
        initBirthday();
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
                startEdit(EditNormalActivtiy.Companion.getEDIT_NAME());
                break;
            case R.id.tvNumber:
                startEdit(EditNormalActivtiy.Companion.getEDIT_NUM());
                break;
            case R.id.tvIntroduction:
                startEdit(EditNormalActivtiy.Companion.getEDIT_INTRODUCTION());
                break;
            case R.id.tvSchool:
                break;
            case R.id.tvGender:
                showGenderDialog();
                break;
            case R.id.tvBirthday:
                pvTime.show();
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
        intent.putExtra(EditNormalActivtiy.Companion.getEDIT_TYPE(), type);
        startActivity(intent);
    }

    /**
     * 选择生日
     */
    private void initBirthday() {

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                btn_CustomTime.setText(getTime(date));
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.dialog_birthday, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        WheelView wlYear = v.findViewById(R.id.year);
                        wlYear.setCurrentItem(3);
//                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
//                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
//                        tvSubmit.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                pvTime.returnData();
//                                pvTime.dismiss();
//                            }
//                        });
//                        ivCancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                pvTime.dismiss();
//                            }
//                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .setLineSpacingMultiplier(1.8f)
                .setGravity(Gravity.CENTER)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF4d4d4d)
                .setDividerType(WheelView.DividerType.WRAP)
                .build();
        // pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。

    }
}
