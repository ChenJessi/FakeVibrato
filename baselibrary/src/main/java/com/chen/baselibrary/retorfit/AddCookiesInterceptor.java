package com.chen.baselibrary.retorfit;

import android.content.Context;

import com.chen.baselibrary.BaseApplication;
import com.chen.baselibrary.ContextProvider;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by CHEN on 2019/8/15.
 * 请求添加cookies
 */

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor() {
        super();
        this.context = ContextProvider.Companion.getMInstance().getContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences = (HashSet) context.getSharedPreferences("cookie",
                Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}