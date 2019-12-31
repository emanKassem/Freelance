package com.example.freelance.data.visits;

import com.example.freelance.data.Result;
import com.example.freelance.data.model.Visit;

import java.util.List;

public interface VisitsRepositoryCallback {
    void visits(Result<List<Visit>> visits);
}
