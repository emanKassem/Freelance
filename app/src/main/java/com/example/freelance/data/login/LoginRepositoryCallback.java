package com.example.freelance.data.login;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.LoggedInUser;

public interface LoginRepositoryCallback {
    void isLogin(Result<LoggedInUser> result);
}
