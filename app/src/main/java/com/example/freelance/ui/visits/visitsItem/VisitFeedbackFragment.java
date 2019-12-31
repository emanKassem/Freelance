package com.example.freelance.ui.visits.visitsItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freelance.R;
import com.example.freelance.data.model.Visit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitFeedbackFragment extends Fragment {

    @BindView(R.id.feedback_tv)
    TextView feedbackTV;
    @BindView(R.id.feedback_submit)
    TextView feedbackSubmit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visit_feedback, container, false);
        ButterKnife.bind(this, view);
        Visit visit = getArguments().getParcelable("visit");
        if (visit.getFeedback()!=null){
            feedbackTV.setText(visit.getFeedback());
            feedbackSubmit.setVisibility(View.GONE);
        }
        return view;
    }

    public static VisitFeedbackFragment newInstance() {
        return new VisitFeedbackFragment();
    }
}
