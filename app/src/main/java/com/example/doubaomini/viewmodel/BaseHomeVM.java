package com.example.doubaomini.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doubaomini.bean.ChatMessage;
import com.example.doubaomini.network.RetrofitService;
import com.example.doubaomini.network.request.UserLogin;
import com.example.doubaomini.network.request.UserRegister;
import com.example.doubaomini.network.response.UserInfo;
import com.example.doubaomini.network.response.UserLoginRes;
import com.example.doubaomini.utils.ContextUtil;
import com.example.doubaomini.utils.SPUtil;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseHomeVM extends ViewModel {

    public MutableLiveData<List<ChatMessage>> message = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLogin = new MutableLiveData<>(false);
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public MutableLiveData<UserInfo> userInfo = new MutableLiveData<>();
    public MutableLiveData<String> token = new MutableLiveData<>();


    public void login(UserLogin userLogin){
        RetrofitService.getApiService(ContextUtil.getContext()).login(userLogin).enqueue(new Callback<UserLoginRes>() {
            @Override
            public void onResponse(@NonNull Call<UserLoginRes> call, @NonNull Response<UserLoginRes> response) {
                assert response.body() != null;
                userInfo.postValue(response.body().userInfo);
                token.postValue(response.body().accessToken);
                SPUtil.putString(ContextUtil.getContext(), "token", token.getValue());
            }

            @Override
            public void onFailure(@NonNull Call<UserLoginRes> call, @NonNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    errorMessage.postValue("网络连接失败，请检查网络");
                } else if (t instanceof SocketTimeoutException) {
                    errorMessage.postValue("请求超时，请稍后再试");
                } else {
                    errorMessage.postValue("服务器异常：" + t.getMessage());
                }
            }
        });
    }

    public void register(UserRegister userRegister){
        RetrofitService.getApiService(ContextUtil.getContext()).register(userRegister).enqueue(new Callback<UserLoginRes>() {
            @Override
            public void onResponse(@NonNull Call<UserLoginRes> call, @NonNull Response<UserLoginRes> response) {
                assert response.body() != null;
                userInfo.postValue(response.body().userInfo);
                token.postValue(response.body().accessToken);
                SPUtil.putString(ContextUtil.getContext(), "token", token.getValue());
            }

            @Override
            public void onFailure(@NonNull Call<UserLoginRes> call, @NonNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    errorMessage.postValue("网络连接失败，请检查网络");
                } else if (t instanceof SocketTimeoutException) {
                    errorMessage.postValue("请求超时，请稍后再试");
                } else {
                    errorMessage.postValue("服务器异常：" + t.getMessage());
                }
            }
        });
    }

}
