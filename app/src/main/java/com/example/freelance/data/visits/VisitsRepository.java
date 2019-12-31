package com.example.freelance.data.visits;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Task;
import com.example.freelance.data.model.Visit;
import com.example.freelance.data.tasks.TasksDataSource;
import com.example.freelance.data.tasks.TasksRepository;
import com.example.freelance.ui.ViewCallback;

import java.util.List;

public class VisitsRepository implements VisitsRepositoryCallback{

    private static volatile VisitsRepository instance;
    private final VisitsDataSource dataSource;
    private ViewCallback viewCallback;

    private VisitsRepository(VisitsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static VisitsRepository getInstance(VisitsDataSource dataSource) {
        if (instance == null) {
            instance = new VisitsRepository(dataSource);
        }
        return instance;
    }

    public void getVisits(ViewCallback viewCallback){
        this.viewCallback = viewCallback;
        dataSource.getVisits(this);
    }

    @Override
    public void visits(Result<List<Visit>> visits) {
        viewCallback.result(visits);
    }
}
