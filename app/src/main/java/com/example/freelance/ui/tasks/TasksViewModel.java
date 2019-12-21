package com.example.freelance.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.freelance.R;
import com.example.freelance.data.Result;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.tasks.TasksRepository;
import com.example.freelance.ui.ViewCallback;
import com.example.freelance.ui.login.ViewResult;

import java.util.List;

public class TasksViewModel extends ViewModel implements ViewCallback<List<Task>> {

    private MutableLiveData<ViewResult> tasksResult = new MutableLiveData<>();
    private TasksRepository tasksRepository;

    public LiveData<ViewResult> getTasksResult() {
        return tasksResult;
    }

    public TasksViewModel(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void getTasks(){
        tasksRepository.getTasks(this);
    }

    @Override
    public void result(Result<List<Task>> result) {
        if (result instanceof Result.Success) {
            tasksResult.setValue(new ViewResult(((Result.Success) result).getData()));
        } else {
            tasksResult.setValue(new ViewResult(R.string.login_failed));
        }
    }
}
