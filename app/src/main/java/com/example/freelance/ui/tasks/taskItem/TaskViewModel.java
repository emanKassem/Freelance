package com.example.freelance.ui.tasks.taskItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.freelance.R;
import com.example.freelance.data.Result;
import com.example.freelance.data.model.TaskDesc;
import com.example.freelance.data.tasks.TasksRepository;
import com.example.freelance.ui.ViewCallback;
import com.example.freelance.ui.login.ViewResult;

public class TaskViewModel extends ViewModel implements ViewCallback<TaskDesc> {

    private MutableLiveData<ViewResult> taskResult = new MutableLiveData<>();
    private TasksRepository tasksRepository;

    public LiveData<ViewResult> getTaskResult() {
        return taskResult;
    }

    public TaskViewModel(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void getTask(int id){
        tasksRepository.getTask(this, id);
    }

    @Override
    public void result(Result<TaskDesc> result) {
        if (result instanceof Result.Success) {
            taskResult.setValue(new ViewResult(((Result.Success) result).getData()));
        } else {
            taskResult.setValue(new ViewResult(R.string.login_failed));
        }
    }
}
