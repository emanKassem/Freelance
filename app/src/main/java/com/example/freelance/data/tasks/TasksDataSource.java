package com.example.freelance.data.tasks;

import com.example.freelance.app.App;
import com.example.freelance.data.model.Result;
import com.example.freelance.data.model.ResultList;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.model.TaskDesc;
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
        Call<ResultList<Task>> tasks = apiService.tasks();
        tasks.enqueue(new Callback<ResultList<Task>>() {
            @Override
            public void onResponse(Call<ResultList<Task>> call, Response<ResultList<Task>> response) {
                if (response.body()!=null){
                    ResultList<Task> tasks = response.body();
                    callback.tasks(new com.example.freelance.data.Result.Success<>(tasks.getData()));
                }else {
                    callback.tasks(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
                }
            }

            @Override
            public void onFailure(Call<ResultList<Task>> call, Throwable t) {
                callback.tasks(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
            }
        });
    }

    public void getTask(TasksRepositoryCallback callback, int id) {
        Call<Result<TaskDesc>> taskDesc = apiService.task(id);
        taskDesc.enqueue(new Callback<Result<TaskDesc>>() {
            @Override
            public void onResponse(Call<Result<TaskDesc>> call, Response<Result<TaskDesc>> response) {
                if (response.body()!=null){
                    Result<TaskDesc> message = response.body();
                    callback.task(new com.example.freelance.data.Result.Success<>(message.getData()));
                }else {
                    callback.task(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
                }
            }

            @Override
            public void onFailure(Call<Result<TaskDesc>> call, Throwable t) {
                callback.task(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
            }
        });
    }
}
