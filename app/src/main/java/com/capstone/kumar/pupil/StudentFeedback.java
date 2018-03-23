package com.capstone.kumar.pupil;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.capstone.kumar.pupil.Adapter.MainPagerAdapter;

public class StudentFeedback extends AppCompatActivity {
    private static final String TAG = "StudentFeedModel";

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);

        mViewPager = (ViewPager) findViewById(R.id.viewPager_feedBack);
        mTabLayout = (TabLayout) findViewById(R.id.am_tab_layout);

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
