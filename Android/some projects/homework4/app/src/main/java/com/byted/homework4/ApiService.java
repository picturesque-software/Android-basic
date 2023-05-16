package com.byted.homework4;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("wxarticle/chapters/json")
    Call<ArticleResponse> getArticles();


    @FormUrlEncoded
    @POST("user/register")
    Call<UserResponse> register(@Field("username") String userName, @Field("password") String password, @Field("repassword") String rePassword);

}
