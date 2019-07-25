package com.chen.fakevibrato.im.common;

/**
 * @author Created by CHEN on 2019/7/24
 * @email 188669@163.com
 */

import android.app.Application;

import com.chen.fakevibrato.R;
import com.chen.fakevibrato.utils.MyLog;
import com.chen.fakevibrato.utils.UrlCode;

/**
 * IM全局错误码枚举
 *
 * 因为目前不同的 API 会根据业务情况返回相同的错误码，所以需要根据 API 区分每个错误码的提示，所以做出以下处理。
 *
 * API 类型的错误码规则：
 * 每个 API 的错误码由 API 的 url 对应的代码{@link UrlCode} + API 对应的错误码
 * 比如登录接口 LOGIN，此 API 的 url 代码为 1，当 API 返回错误码 为 1000  且错误码偏移为 10000 时,对应 ErrorCode 为 11000
 *
 */
public enum ErrorCode {
    IM_ERROR(-4, 0),
    UNKNOWN_ERROR(999999, 0);

    private int code;
    private int messageResId;
    private static Application application;

    ErrorCode(int code, int messageResId) {
        this.code = code;
        this.messageResId = messageResId;
    }

    public int getCode() {
        return code;
    }

    public int getMessageResId() {
        return messageResId;
    }

    public String getMessage() {
        if(application == null){
            MyLog.e("ErrorCode getMessage need init first.");
            return "";
        }
        //TODO 默认错误提示语
        String msg = "";
        if(messageResId > 0){
            msg = application.getResources().getString(messageResId);
        }
        return msg;
    }

    public static ErrorCode fromCode(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.code == code)
                return errorCode;
        }

        return UNKNOWN_ERROR;
    }

    public static void init(Application application){
        ErrorCode.application = application;
    }

}
