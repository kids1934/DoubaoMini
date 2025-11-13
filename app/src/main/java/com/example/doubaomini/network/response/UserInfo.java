package com.example.doubaomini.network.response;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("id")
    public Integer id;
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("createTime")
    public String create_time;
}
