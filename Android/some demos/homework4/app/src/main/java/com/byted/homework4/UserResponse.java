package com.byted.homework4;

import com.google.gson.annotations.SerializedName;

class UserResponse {
    @SerializedName("errorCode")
    public int errorCode;
    @SerializedName("errorMsg")
    public String errorMsg;
    @SerializedName("data")
    User user;
}
