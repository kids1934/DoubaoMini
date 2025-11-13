package com.example.doubaomini.network.response;

import com.google.gson.annotations.SerializedName;

public class UserLoginRes {
    @SerializedName("accessToken")
    public String accessToken;
    @SerializedName("userInfo")
    public UserInfo userInfo;
}
