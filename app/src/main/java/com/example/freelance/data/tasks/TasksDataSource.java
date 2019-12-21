package com.example.freelance.data.tasks;

import com.example.freelance.app.App;
import com.example.freelance.data.model.Result;
import com.example.freelance.data.model.Task;
import com.example.freelance.network.ApiClient;
import com.example.freelance.network.ApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TasksDataSource {

    ApiService apiService;

    public TasksDataSource(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }

    public void getTasks(final TasksRepositoryCallback callback){
        Call<Result<List<Task>>> tasks = apiService.tasks();
        tasks.enqueue(new Callback<Result<List<Task>>>() {
            @Override
            public void onResponse(Call<Result<List<Task>>> call, Response<Result<List<Task>>> response) {
                if (response.body()!=null){
                    Result<List<Task>> tasks = response.body();
                    callback.tasks(new com.example.freelance.data.Result.Success<>(tasks.getData()));
                }else {
                    callback.tasks(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
                }
            }

            @Override
            public void onFailure(Call<Result<List<Task>>> call, Throwable t) {
                callback.tasks(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
            }
        });
    }
}
