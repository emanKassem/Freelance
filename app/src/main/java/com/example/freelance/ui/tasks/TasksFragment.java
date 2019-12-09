package com.example.freelance.ui.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freelance.R;


public class TasksFragment extends Fragment {

    TasksViewModel tasksViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        final TextView textView = view.findViewById(R.id.text_tasks);
        tasksViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return view;
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }
}
