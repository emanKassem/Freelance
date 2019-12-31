package com.example.freelance.data.tasks;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.model.TaskDesc;

import java.util.List;

public interface TasksRepositoryCallback {
    void task(Result<TaskDesc> taskDesc);
    void tasks(Result<List<Task>> tasks);
}
