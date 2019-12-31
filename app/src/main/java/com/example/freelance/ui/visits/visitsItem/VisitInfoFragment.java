package com.example.freelance.ui.visits.visitsItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.freelance.R;
import com.example.freelance.app.App;
import com.example.freelance.data.model.Visit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitInfoFragment extends Fragment {

    @BindView(R.id.contact_imageView)
    ImageView contactImage;
    @BindView(R.id.contact_textView)
    TextView contactText;
    @BindView(R.id.visit_territory_tv)
    TextView territoryText;
    @BindView(R.id.visit_brick_tv)
    TextView brickText;
    @BindView(R.id.visit_due_date_tv)
    TextView dueToText;
    @BindView(R.id.visit_created_at_tv)
    TextView createdAtText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visit_info, container, false);
        ButterKnife.bind(this, view);
        Visit visit = getArguments().getParcelable("visit");
        contactText.setText(visit.getContact());
        territoryText.setText(visit.getTerritory());
        Glide.with(App.getContext()).load(visit.getContactPicture()).centerCrop()
                .placeholder(R.drawable.ic_profile_24dp).into(contactImage);
        brickText.setText(visit.getBrick());
        dueToText.setText(visit.getDueDate());
        createdAtText.setText(visit.getCreatedAt());

        return view;
    }

    public static VisitInfoFragment newInstance() {
        return new VisitInfoFragment();
    }
}
