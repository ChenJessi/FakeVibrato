package com.chen.fakevibrato.im;

import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.chen.fakevibrato.im.common.ErrorCode;
import com.chen.fakevibrato.im.common.ResultCallback;
import com.chen.fakevibrato.model.LoginResult;
import com.chen.fakevibrato.utils.MyLog;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * @author Created by CHEN on 2019/7/24
 * @email 188669@163.com
 * im 即时通讯管理类
 */

public class IMManager {
    private static volatile IMManager instance;

    private Context context;

    private UserConfigCache configCache;
    private UserCache userCache;
    private MutableLiveData<Boolean> autologinResult = new MutableLiveData<>();

    private IMManager() {
    }

    public static IMManager getInstance() {
        if (instance == null) {
            synchronized (IMManager.class) {
                if (instance == null) {
                    instance = new IMManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param context
     */
    public void init(Context context) {
        this.context = context.getApplicationContext();
        // 调用 RongIM 初始化
        initRongIM(context);

        //        // 初始化用户和群组信息内容提供者
        //        initInfoProvider(context);
        //
        //        // 初始化自定义消息和消息模版
        //        initMessageAndTemplate();
        //
        //        // 初始化扩展模块
        //        initExtensionModules(context);
        //
        //        // 初始化已读回执类型
        //        initReadReceiptConversation();
        //
        //        // 初始化会话界面相关内容
        //        initConversation();
        //
        //        // 初始化会话列表界面相关内容
        //        initConversationList();
        //
        //        // 初始化连接状态变化监听
        //        initConnectStateChangeListener();
        //
        //        // 初始化消息监听
        //        initOnReceiveMessage(context);
        //
        //        // 初始化聊天室监听
        //        initChatRoomActionListener();
        //
        //        // 长按消息转发等功能
        //        initMessageItemLongClickAction(context);

        // 缓存连接
        cacheConnectIM();
    }


    /**
     * 获取当前用户 id
     *
     * @return
     */
    public String getCurrentId() {
        return RongIM.getInstance().getCurrentUserId();
    }


    /**
     * 调用初始化 RongIM
     *
     * @param context
     */
    private void initRongIM(Context context) {
        /*
         * 如果是连接到私有云需要在此配置服务器地址
         * 如果是公有云则不需要调用此方法
         */
        //RongIM.setServerInfo("nav.cn.ronghub.com", "up.qbox.me");

        /*
         * 初始化 SDK，在整个应用程序全局，只需要调用一次。建议在 Application 继承类中调用。
         */

        /* 若直接调用init方法请在 IMLib 模块中的 AndroidManifest.xml 中, 找到 <meta-data> 中 android:name 为 RONG_CLOUD_APP_KEY的标签，
         * 将 android:value 替换为融云 IM 申请的APP KEY
         */
        RongIM.init(context);

        // 可在初始 SDK 时直接带入融云 IM 申请的APP KEY
        //        RongIM.init(context, "n19jmcy59f1q9", true);

        //        RongIM.init(context, "kj7swf8o7dot2", true);

    }

    /**
     * 缓存登录
     */
    private void cacheConnectIM() {

        if (RongIM.getInstance().getCurrentConnectionStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            autologinResult.setValue(true);
            return;
        }
        // 用户设置缓存 sp
        configCache = new UserConfigCache(context.getApplicationContext());
        userCache = new UserCache(context.getApplicationContext());
        /**
         * 缓存中获取token
         */
        UserCacheInfo userCache = this.userCache.getUserCache();
        if (userCache == null) {
            autologinResult.setValue(false);
            return;
        }
        //        String loginToken = this.userCache.getUserCache().getLoginToken();

        String loginToken = "/wizU+Uy0SwOT8aHgepbZRc547uv9tNpf5Qq13t7aVsdHzK26E0lZLUbgBzglMrefMUSS9zPLzBeGbOGSLRk3A==";

        MyLog.d("连接 ： loginToken  " + loginToken);
        if (TextUtils.isEmpty(loginToken)) {
            autologinResult.setValue(false);
            return;
        }
        MyLog.d("连接 ： loginToken··  " + loginToken);
        connectIM(loginToken, true, new ResultCallback<String>() {
            @Override
            public void onSuccess(String s) {
                autologinResult.setValue(true);
                MyLog.d("连接成功 ： " + s);
            }

            @Override
            public void onFail(int errorCode) {
                autologinResult.setValue(false);
                MyLog.d("连接失败 ： " + errorCode);
            }
        });
    }

    /**
     * 连接 IM 服务
     *
     * @param token
     * @param getTokenOnIncorrect
     * @param callback
     */
    public void connectIM(String token, boolean getTokenOnIncorrect, ResultCallback<String> callback) {
        /*
         *  考虑到会有后台调用此方法，所以不采用 LiveData 做返回值
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                MyLog.d("连接 onTokenIncorrect");
                if (getTokenOnIncorrect) {
                    getToken(new ResultCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            connectIM(loginResult.token, false, callback);
                        }

                        @Override
                        public void onFail(int errorCode) {
                            callback.onFail(errorCode);
                        }
                    });
                } else {
                    if (callback != null) {
                        callback.onFail(ErrorCode.IM_ERROR.getCode());
                    } else {
                        // do nothing
                    }
                }
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                // 连接 IM 成功后，初始化数据库
                //                DbManager.getInstance(context).openDb(s);
                callback.onSuccess(userid);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                MyLog.e("connect error - code:" + errorCode.getValue() + ", msg:" + errorCode.getMessage());
                if (callback != null) {
                    callback.onFail(errorCode.getValue());
                } else {
                    // do nothing
                }
            }
        });
    }


    /**
     * 获取用户 IM token
     * 此接口需要在登录成功后可调用，用于在 IM 提示 token 失效时刷新 token 使用
     *
     * @param callback
     */
    private void getToken(ResultCallback<LoginResult> callback) {
        /**
         * 网络请求获取token
         */
    }

    /**
     * 自动重连结果
     *
     * @return
     */
    public LiveData<Boolean> getAutoLoginResult() {
        return autologinResult;
    }
}

