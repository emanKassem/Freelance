package com.example.freelance.ui.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.freelance.R;
import com.example.freelance.data.model.Visit;
import com.example.freelance.ui.visits.visitsItem.VisitFeedbackFragment;
import com.example.freelance.ui.visits.visitsItem.VisitInfoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    VisitInfoFragment visitInfoFragment = VisitInfoFragment.newInstance();
    VisitFeedbackFragment visitFeedbackFragment = VisitFeedbackFragment.newInstance();
    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.title_visit_info, R.string.title_visit_feedback};
    private final Context mContext;

    public TabsPagerAdapter(Context context, FragmentManager fm, Visit visit) {
        super(fm);
        mContext = context;
        Bundle bundle = new Bundle();
        bundle.putParcelable("visit", visit);
        visitInfoFragment.setArguments(bundle);
        visitFeedbackFragment.setArguments(bundle);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return visitInfoFragment;
            case 1:
                return visitFeedbackFragment;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
