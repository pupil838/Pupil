package com.capstone.kumar.pupil.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 3/18/2018.
 */

public class MyHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "MyHolder";

    public TextView mUserName;
    public TextView mFeedBack;
    public TextView mCompanyName;
    public TextView mReadFull;

    public MyHolder(View itemView) {
        super(itemView);

        mCompanyName = (TextView) itemView.findViewById(R.id.get_companyName);
        mFeedBack = (TextView) itemView.findViewById(R.id.get_feedback);
        mUserName = (TextView) itemView.findViewById(R.id.get_userName);
        mReadFull = (TextView) itemView.findViewById(R.id.readFull);
    }

}
