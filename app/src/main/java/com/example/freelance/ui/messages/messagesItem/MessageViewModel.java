package com.example.freelance.ui.messages.messagesItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.freelance.R;
import com.example.freelance.data.Result;
import com.example.freelance.data.messages.MessagesRepository;
import com.example.freelance.data.model.MessageDesc;
import com.example.freelance.ui.ViewCallback;
import com.example.freelance.ui.login.ViewResult;

public class MessageViewModel extends ViewModel implements ViewCallback<MessageDesc> {

    private MutableLiveData<ViewResult> messageResult = new MutableLiveData<>();
    private MessagesRepository messagesRepository;

    public LiveData<ViewResult> getMessageResult() {
        return messageResult;
    }

    public MessageViewModel(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void getMessage(int id){
        messagesRepository.getMessage(this, id);
    }

    @Override
    public void result(Result<MessageDesc> result) {
        if (result instanceof Result.Success) {
            messageResult.setValue(new ViewResult(((Result.Success) result).getData()));
        } else {
            messageResult.setValue(new ViewResult(R.string.login_failed));
        }
    }
}
