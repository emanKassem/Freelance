package com.example.freelance.ui;

import com.example.freelance.data.Result;

public interface ViewCallback<T> {
    void result(Result<T> result);
}
