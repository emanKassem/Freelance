package com.example.freelance.data.tasks;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Task;

import java.util.List;

public interface TasksRepositoryCallback {
    void tasks(Result<List<Task>> tasks);
}
