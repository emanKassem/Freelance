package com.example.freelance.ui.visits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.freelance.R;
import com.example.freelance.data.Result;
import com.example.freelance.data.model.Visit;
import com.example.freelance.data.visits.VisitsRepository;
import com.example.freelance.ui.ViewCallback;
import com.example.freelance.ui.login.ViewResult;

import java.util.List;

public class VisitsViewModel extends ViewModel implements ViewCallback<List<Visit>> {

    private MutableLiveData<ViewResult> visitsResult = new MutableLiveData<>();
    private VisitsRepository visitsRepository;

    public LiveData<ViewResult> getVisitsResult() {
        return visitsResult;
    }

    public VisitsViewModel(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    public void getVisits(){
        visitsRepository.getVisits(this);
    }

    @Override
    public void result(Result<List<Visit>> result) {
        if (result instanceof Result.Success) {
            visitsResult.setValue(new ViewResult(((Result.Success) result).getData()));
        } else {
            visitsResult.setValue(new ViewResult(R.string.login_failed));
        }
    }
}
