package com.example.freelance.data.messages;

import com.example.freelance.app.App;
import com.example.freelance.data.model.Message;
import com.example.freelance.data.model.MessageDesc;
import com.example.freelance.data.model.Result;
import com.example.freelance.data.model.ResultList;
import com.example.freelance.network.ApiClient;
import com.example.freelance.network.ApiService;
import com.example.freelance.ui.ViewCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesDataSource {

    ApiService apiService;

    public MessagesDataSource(){
        apiService = ApiClient.getClient(App.getContext()).create(ApiService.class);
    }

    public void getMessages(final MessagesRepositoryCallback callback){
        Call<ResultList<Message>> messages = apiService.messages();
        messages.enqueue(new Callback<ResultList<Message>>() {
            @Override
            public void onResponse(Call<ResultList<Message>> call, Response<ResultList<Message>> response) {
                if (response.body()!=null){
                    ResultList<Message> messages = response.body();
                    callback.messages(new com.example.freelance.data.Result.Success<>(messages.getData()));
                }else {
                    callback.messages(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
                }
            }

            @Override
            public void onFailure(Call<ResultList<Message>> call, Throwable t) {
                callback.messages(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
            }
        });
    }

    public void getMessage(MessagesRepositoryCallback callback, int id) {
        Call<Result<MessageDesc>> messageDesc = apiService.message(id);
        messageDesc.enqueue(new Callback<Result<MessageDesc>>() {
            @Override
            public void onResponse(Call<Result<MessageDesc>> call, Response<Result<MessageDesc>> response) {
                if (response.body()!=null){
                    Result<MessageDesc> message = response.body();
                    callback.message(new com.example.freelance.data.Result.Success<>(message.getData()));
                }else {
                    callback.message(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
                }
            }

            @Override
            public void onFailure(Call<Result<MessageDesc>> call, Throwable t) {
                callback.message(new com.example.freelance.data.Result.Error(new IOException("Error getting tasks")));
            }
        });
    }
}
