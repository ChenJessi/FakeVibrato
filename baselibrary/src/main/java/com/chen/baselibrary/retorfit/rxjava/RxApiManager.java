package com.chen.baselibrary.retorfit.rxjava;

import io.reactivex.disposables.Disposable;

/**
 * Created by CHEN on 2019/8/15.
 * 取消请求接口
 */

public interface RxApiManager<T> {
    void add(T tag, Disposable disposable);
    void remove(T tag);

    void cancel(T tag);

    void cancelAll();
}
