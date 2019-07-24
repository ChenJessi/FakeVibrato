package com.chen.fakevibrato;

import android.app.Application;
import android.content.Intent;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * @author Created by CHEN on 2019/6/10
 * @email 188669@163.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);

    }


//    private void connect(String token) {
//
//        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {
//
//            RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
//
//                /**
//                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
//                 *                            2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
//                 */
//                @Override
//                public void onTokenIncorrect() {
//
//                }
//
//                /**
//                 * 连接融云成功
//                 * @param userid 当前 token 对应的用户 id
//                 */
//                @Override
//                public void onSuccess(String userid) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }
//
//                /**
//                 * 连接融云失败
//                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
//                 */
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                }
//            });
//        }
//    }
}
