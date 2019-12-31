package com.example.freelance.data.messages;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Message;
import com.example.freelance.data.model.MessageDesc;

import java.util.List;

public interface MessagesRepositoryCallback {
    void messages(Result<List<Message>> messages);
    void message(Result<MessageDesc> messageDescResult);
}
