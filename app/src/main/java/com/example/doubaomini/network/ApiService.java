package com.example.doubaomini.network;

import com.example.doubaomini.network.request.UserLogin;
import com.example.doubaomini.network.request.UserRegister;
import com.example.doubaomini.network.response.UserLoginRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {


    @POST("/api/user/login")
    Call<UserLoginRes> login(@Body UserLogin userLogin);
    @POST("/api/user/register")
    Call<UserLoginRes> register(@Body UserRegister userRegister);
}
