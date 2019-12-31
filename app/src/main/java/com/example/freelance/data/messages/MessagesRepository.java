package com.example.freelance.data.messages;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Message;
import com.example.freelance.data.model.MessageDesc;
import com.example.freelance.ui.ViewCallback;

import java.util.List;

public class MessagesRepository implements MessagesRepositoryCallback{

    private static volatile MessagesRepository instance;
    private final MessagesDataSource dataSource;
    private ViewCallback viewCallback;

    private MessagesRepository(MessagesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static MessagesRepository getInstance(MessagesDataSource dataSource) {
        if (instance == null) {
            instance = new MessagesRepository(dataSource);
        }
        return instance;
    }

    public void getMessages(ViewCallback viewCallback){
        this.viewCallback = viewCallback;
        dataSource.getMessages(this);
    }

    public void getMessage(ViewCallback viewCallback, int id){
        this.viewCallback = viewCallback;
        dataSource.getMessage(this, id);

    }

    @Override
    public void messages(Result<List<Message>> result) {
        viewCallback.result(result);
    }

    @Override
    public void message(Result<MessageDesc> messageDescResult) {
        viewCallback.result(messageDescResult);
    }
}
