package com.example.freelance.ui.adapters;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.freelance.R;
import com.example.freelance.ui.messages.MessagesFragment;
import com.example.freelance.ui.tasks.TasksFragment;
import com.example.freelance.ui.visits.VisitsFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.title_visits, R.string.title_tasks, R.string.title_messages };
    private final Context mContext;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return VisitsFragment.newInstance();
            case 1:
                return TasksFragment.newInstance();
            case 2:
                return MessagesFragment.newInstance();
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
        // Show 3 total pages.
        return 3;
    }
}
