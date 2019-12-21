package com.example.freelance.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.data.model.Task;
import com.example.freelance.ui.login.ViewResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TasksFragment extends Fragment {

    TasksViewModel tasksViewModel;
    @BindView(R.id.tasks_recyclerView)
    RecyclerView tasksRecyclerView;
    TasksAdapter tasksAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        tasksViewModel = ViewModelProviders.of(this, new TasksViewModelFactory()).get(TasksViewModel.class);
        tasksViewModel.getTasksResult().observe(this, new Observer<ViewResult>() {
            @Override
            public void onChanged(ViewResult viewResult) {
                if (viewResult == null) {
                    return;
                }
                if (viewResult.getError() != null) {
                    showFailed(viewResult.getError());
                }
                if (viewResult.getSuccess() != null) {
                    updateUiWithTasks((List<Task>) viewResult.getSuccess());
                }
            }
        });
        tasksViewModel.getTasks();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(App.getContext()));
    }

    private void updateUiWithTasks(List<Task> tasks) {
        tasksAdapter = new TasksAdapter(tasks);
        tasksRecyclerView.setAdapter(tasksAdapter);
    }

    private void showFailed(Integer error) {
        Toast.makeText(App.getContext(), "failed", Toast.LENGTH_LONG).show();
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }
}
