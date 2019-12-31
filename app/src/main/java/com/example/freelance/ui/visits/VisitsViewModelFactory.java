package com.example.freelance.ui.visits;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.freelance.data.visits.VisitsDataSource;
import com.example.freelance.data.visits.VisitsRepository;

public class VisitsViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VisitsViewModel.class)) {
            return (T) new VisitsViewModel(VisitsRepository.getInstance(new VisitsDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
