package com.capstone.kumar.pupil.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 4/12/2018.
 */

public class TrendQueComHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "TrendQueComHolder";

    public TextView companyName;
    public TextView question;
    public TextView clickMe;


    public TrendQueComHolder(View itemView) {
        super(itemView);
        companyName = (TextView)itemView.findViewById(R.id.companyName);
        question = (TextView)itemView.findViewById(R.id.question);
        clickMe = (TextView)itemView.findViewById(R.id.clickMe);
    }
}
