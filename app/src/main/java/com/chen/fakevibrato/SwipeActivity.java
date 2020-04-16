package com.chen.fakevibrato;

import android.view.View;
import android.widget.TextView;

import com.chen.baselibrary.base.DBasePresenter;
import com.chen.fakevibrato.base.DBaseSupportActivity;
import com.chen.fakevibrato.skin.SkinManager;
import com.chen.fakevibrato.utils.MyLog;

import java.io.File;

public class SwipeActivity extends DBaseSupportActivity {
    private TextView text1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe;
    }

    @Override
    protected DBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        text1 = findViewById(R.id.text1);
    }

    @Override
    protected void initListener() {
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    @Override
    protected void initData() {

    }

//    @CheckNet
    private void test(){
//        val fixFile = new File();
        File file = new File("/mnt/sdcard/tencent/QQfile_recv/test.skin");
        MyLog.e("点击执行了=========="+file);
        SkinManager.Companion.getInstance().loadSkin(file.getPath());

//        IDaoSoupport<UserInfo> daoSoupport = DaoSupportFactory.Companion.getFactory().getDao(UserInfo.class);
//        List<UserInfo> userInfos = new ArrayList<>();
//
//        for (int i = 0; i < 5000; i++) {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setName("测试  :  ");
//            userInfos.add(userInfo);
//        }
//        daoSoupport.insert(userInfos);
//
//        List<UserInfo> users = daoSoupport.query();
//        Toast.makeText(this, "users -> " + users.size(), Toast.LENGTH_LONG).show();
//        HttpUtils.Companion.with(SwipeActivity.this)
//                .url("www.baidu.com")
//                .cache(true)
//                .addParam("","")
//                .execute(new HttpCallBack<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//
//                    }
//
//                    @Override
//                    public void onError(@NotNull Exception e) {
//                        super.onError(e);
//                    }
//                });
    }
}
