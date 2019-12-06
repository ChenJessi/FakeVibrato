package com.chen.baselibrary.retorfit.rxjava;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by CHEN on 2019/8/15.
 */

public abstract class RxObserver<T> implements Observer<T> {
    /**
     * 自定义tag
     */
    private Object tag;
    private Context context;
    public RxObserver(Object tag) {
        this.tag = tag;
        this.context = (Context) tag;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!TextUtils.isEmpty(tag.toString())) {
            RxManager.get().add(tag, d);
        }
    }

    @Override
    public void onNext(T t) {
        RxManager.get().remove(tag);
        onSuccess(t);
    }
    @Override
    public void onError(Throwable e) {
        Log.e("rxobserver","e=============="+e.getMessage());
        RxManager.get().remove(tag);
        Toast.makeText(context,  e.getMessage(), Toast.LENGTH_SHORT).show();
//        if (e instanceof UnknownHostException || e instanceof ConnectException) {//无网络
//            solveException(ExceptionType.BAD_NETWORK);
//        } else if (e instanceof JsonParseException || e instanceof JSONException ||
//                e instanceof ParseException) {//解析异常
//            solveException(ExceptionType.PARSE_DATA_ERROR);
//        } else if (e instanceof HttpException) {//http异常，比如 404 500
//            solveException(ExceptionType.UNFOUND_ERROR);
//        } else if (e instanceof SocketTimeoutException) {//连接超时
//            solveException(ExceptionType.TIMEOUT_ERROR);
//        } else {//未知错误
//            solveException(ExceptionType.UNKNOWN_ERROR);
//        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功回调
     * @param response 服务端返回的数据
     */
    public abstract void onSuccess(T response);


    /**
     * 连接异常时回调，手动触发
     */
    public void onError(){}

    /**
     * 对于异常情况的统一处理
     * @param type 异常的类型
     */
    public void solveException(ExceptionType type){
        switch (type){
            case BAD_NETWORK:
                Toast.makeText(context, "无网络", Toast.LENGTH_SHORT).show();
                break;
            case PARSE_DATA_ERROR:
                Toast.makeText(context, "数据解析异常", Toast.LENGTH_SHORT).show();
                break;
            case UNFOUND_ERROR:
                Toast.makeText(context, "未找到指定请求接口", Toast.LENGTH_SHORT).show();
                break;
            case TIMEOUT_ERROR:
                Toast.makeText(context, "网络连接超时", Toast.LENGTH_SHORT).show();
                break;
            case UNKNOWN_ERROR:
                Toast.makeText(context, "未知错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public enum ExceptionType {
        /** 无网络 */
        BAD_NETWORK,
        /** 数据解析异常 */
        PARSE_DATA_ERROR,
        /** 找不到相关连接 */
        UNFOUND_ERROR,
        /** 连接超时 */
        TIMEOUT_ERROR,
        /** 未知错误 */
        UNKNOWN_ERROR
    }
}
