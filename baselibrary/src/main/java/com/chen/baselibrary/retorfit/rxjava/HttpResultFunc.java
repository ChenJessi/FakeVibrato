package com.chen.baselibrary.retorfit.rxjava;


import android.util.Log;

import io.reactivex.functions.Function;


/**
 * Created by CHEN on 2019/8/15.
 */

public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

    @Override
    public T apply(HttpResult<T> tHttpResult) throws Exception {
        Log.d("HttpResultFunc", "HttpResultFunc : " +tHttpResult.isSuccess());
        if (!tHttpResult.isSuccess()) {
            if (tHttpResult.getStatus() != null){
                throw new RuntimeException(tHttpResult.getStatus().getMessage());
            }else {
                throw new RuntimeException("数据格式错误");
            }
        }
        return tHttpResult.getData();
    }

}

