package com.example.freelance.data.tasks;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Task;
import com.example.freelance.ui.ViewCallback;

import java.util.List;

public class TasksRepository implements TasksRepositoryCallback{

    private static volatile TasksRepository instance;
    private final TasksDataSource dataSource;
    private ViewCallback viewCallback;

    private TasksRepository(TasksDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static TasksRepository getInstance(TasksDataSource dataSource) {
        if (instance == null) {
            instance = new TasksRepository(dataSource);
        }
        return instance;
    }

    public void getTasks(ViewCallback viewCallback){
        this.viewCallback = viewCallback;
        dataSource.getTasks(this);
    }

    @Override
    public void tasks(Result<List<Task>> tasks) {
        viewCallback.result(tasks);
    }
}
