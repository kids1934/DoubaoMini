package com.example.doubaomini.network.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("success")
    public Boolean success;
    @SerializedName("message")
    public String message;
    @SerializedName("errorCode")
    public String errorCode;
    @SerializedName("data")
    public T data;
}
