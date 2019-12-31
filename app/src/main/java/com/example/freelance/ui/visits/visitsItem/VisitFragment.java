package com.example.freelance.ui.visits.visitsItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.freelance.R;
import com.example.freelance.data.model.Visit;
import com.example.freelance.ui.adapters.TabsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        Bundle bundle = getArguments();
        Visit visit = bundle.getParcelable("visit");
        toolbar.setTitle("Visit ".concat(String.valueOf(visit.getId())));
        toolbar.setNavigationOnClickListener(view -> {
            getFragmentManager().popBackStack();
            getFragmentManager().popBackStack();
        });

        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getActivity(), getChildFragmentManager(), visit);

        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        return root;
    }
}
