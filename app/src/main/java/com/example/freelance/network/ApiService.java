package com.example.freelance.network;

import com.example.freelance.data.model.Login;
import com.example.freelance.data.model.Result;
import com.example.freelance.data.model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<Result<Login>> login(@Field("email") String userName, @Field("password") String password);

    @GET("tasks")
    Call<Result<List<Task>>> tasks();
}
