package com.capstone.kumar.pupil.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 4/13/2018.
 */

public class DriveHolder extends RecyclerView.ViewHolder{

    private static final String TAG = "DriveHolder";

    public TextView mCompany;
    public TextView mSalary;
    public TextView mStandingArea;
    public TextView mLocation;
    public TextView mJobProfile;
    public TextView mDriveDate;
    public TextView mJoinDate;
    public TextView mClick;


    public DriveHolder(View itemView) {
        super(itemView);

        mCompany = (TextView)itemView.findViewById(R.id.companyName);
        mSalary = (TextView)itemView.findViewById(R.id.salaryPackage);
        mStandingArea = (TextView)itemView.findViewById(R.id.standingArea);
        mLocation = (TextView)itemView.findViewById(R.id.job_location);
        mJobProfile = (TextView)itemView.findViewById(R.id.jobProfile);
        mDriveDate = (TextView)itemView.findViewById(R.id.Drive_Date);
        mJoinDate = (TextView)itemView.findViewById(R.id.Joining_Date);
        mClick = (TextView)itemView.findViewById(R.id.click);
    }
}
