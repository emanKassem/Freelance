package com.example.freelance.ui.visits;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.freelance.data.model.Visit;
import com.example.freelance.ui.login.ViewResult;
import com.example.freelance.ui.visits.visitsItem.VisitFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitsFragment extends Fragment {

    VisitsViewModel visitsViewModel;
    @BindView(R.id.visits_recyclerview)
    RecyclerView visitsRecyclerView;
    private ProgressDialog progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visits, container, false);
        ButterKnife.bind(this, view);
        final ProgressDialog pd = ProgressDialog.show(App.getContext(), "", "Loading...", false, true);
        visitsViewModel = ViewModelProviders.of(this, new VisitsViewModelFactory()).get(VisitsViewModel.class);
        visitsViewModel.getVisitsResult().observe(this, new Observer<ViewResult>() {
            @Override
            public void onChanged(ViewResult viewResult) {
                pd.dismiss();
                if (viewResult == null) {
                    return;
                }
                if (viewResult.getError() != null) {
                    showFailed(viewResult.getError());
                }
                if (viewResult.getSuccess() != null) {
                    updateUiWithTasks((List<Visit>) viewResult.getSuccess());
                }
            }
        });
        visitsViewModel.getVisits();
        return view;
    }

    private void updateUiWithTasks(List<Visit> visits) {
        visitsRecyclerView.setAdapter(new VisitsAdapter(visits, visit -> {
            VisitFragment visitFragment= new VisitFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("visit", visit);
            visitFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drawer_layout, visitFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }));
    }

    private void showFailed(Integer error) {
        Toast.makeText(App.getContext(), "failed", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        visitsRecyclerView.setLayoutManager(new LinearLayoutManager(App.getContext()));
    }

    public static VisitsFragment newInstance() {
        return new VisitsFragment();
    }
}
