package com.example.freelance.ui.messages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.freelance.R;
import com.example.freelance.data.Result;
import com.example.freelance.data.messages.MessagesRepository;
import com.example.freelance.data.model.Message;
import com.example.freelance.ui.ViewCallback;
import com.example.freelance.ui.login.ViewResult;

import java.util.List;

public class MessagesViewModel extends ViewModel implements ViewCallback<List<Message>> {


    private MutableLiveData<ViewResult> messagesResult = new MutableLiveData<>();
    private MessagesRepository messagesRepository;

    public LiveData<ViewResult> getMessagesResult() {
        return messagesResult;
    }

    public MessagesViewModel(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void getMessages(){
        messagesRepository.getMessages(this);
    }

    @Override
    public void result(Result<List<Message>> result) {
        if (result instanceof Result.Success) {
            messagesResult.setValue(new ViewResult(((Result.Success) result).getData()));
        } else {
            messagesResult.setValue(new ViewResult(R.string.login_failed));
        }
    }

}
