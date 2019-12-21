package com.example.freelance.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class ViewResult<T> {
    @Nullable
    private T success;
    @Nullable
    private Integer error;

    public ViewResult(@Nullable Integer error) {
        this.error = error;
    }

    public ViewResult(@Nullable T success) {
        this.success = success;
    }

    @Nullable
    public T getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}
