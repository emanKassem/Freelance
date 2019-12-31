package com.example.freelance.network;

import com.example.freelance.data.model.Login;
import com.example.freelance.data.model.Message;
import com.example.freelance.data.model.MessageDesc;
import com.example.freelance.data.model.Result;
import com.example.freelance.data.model.ResultList;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.model.TaskDesc;
import com.example.freelance.data.model.Visit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<Result<Login>> login(@Field("email") String userName, @Field("password") String password);

    @GET("tasks")
    Call<ResultList<Task>> tasks();

    @GET("visits")
    Call<Result<List<Visit>>> visits();

    @GET("messages")
    Call<ResultList<Message>> messages();

    @GET("messages/{id}")
    Call<Result<MessageDesc>> message(@Path("id") int id);

    @GET("tasks/{id}")
    Call<Result<TaskDesc>> task(@Path("id") int id);
}
