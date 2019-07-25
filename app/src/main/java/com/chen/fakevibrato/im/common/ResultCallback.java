package com.chen.fakevibrato.im.common;

/**
 * @author Created by CHEN on 2019/7/24
 * @email 188669@163.com
 *
 * im 回调类
 * 执行 Task 的回调
 * @param <Result> 请求成功时的结果类
 */
public interface ResultCallback<Result> {
    void onSuccess(Result result);

    void onFail(int errorCode);
}
