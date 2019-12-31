package com.example.freelance.data.visits;

import com.example.freelance.app.App;
import com.example.freelance.data.model.Result;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.model.Visit;
import com.example.freelance.data.tasks.TasksRepositoryCallback;
import com.example.freelance.network.ApiClient;
import com.example.freelance.network.ApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitsDataSource {
    ApiService apiService;

    public VisitsDataSource(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }

    public void getVisits(final VisitsRepositoryCallback callback){
        Call<Result<List<Visit>>> visits = apiService.visits();
        visits.enqueue(new Callback<Result<List<Visit>>>() {
            @Override
            public void onResponse(Call<Result<List<Visit>>> call, Response<Result<List<Visit>>> response) {
                if (response.body()!=null){
                    Result<List<Visit>> visits = response.body();
                    callback.visits(new com.example.freelance.data.Result.Success<>(visits.getData()));
                }else {
                    callback.visits(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
                }
            }

            @Override
            public void onFailure(Call<Result<List<Visit>>> call, Throwable t) {
                callback.visits(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
            }
        });
    }
}
