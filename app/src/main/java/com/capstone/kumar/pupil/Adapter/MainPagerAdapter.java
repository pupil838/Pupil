package com.capstone.kumar.pupil.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.capstone.kumar.pupil.studentFeedBack.AllFeedBack;
import com.capstone.kumar.pupil.studentFeedBack.GiveFeedBack;

/**
 * Created by kumar on 3/17/2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return GiveFeedBack.create();
            case 1:
                return AllFeedBack.create();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Give FeedBack";
            case 1:
                return "All FeedBack";
        }

        return super.getPageTitle(position);

    }

    @Override
    public int getCount() {
        return 2;
    }
}
