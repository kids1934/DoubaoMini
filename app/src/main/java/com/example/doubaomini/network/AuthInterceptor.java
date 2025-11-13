package com.example.doubaomini.network;

import android.content.Context;

import com.example.doubaomini.utils.SPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private Context context;

    public AuthInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 拿到当前请求
        Request original = chain.request();

        // 从 SharedPreferences 取出 token
        String token = SPUtil.getString(context, "token", null);

        // 如果有 token，就加上 Authorization 头
        Request.Builder builder = original.newBuilder();
        if (token != null) {
            builder.header("Authorization", "Bearer " + token);
        }

        // 发出新请求
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}

