package com.capstone.kumar.pupil.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 4/8/2018.
 */

public class StudentSectionHolder extends RecyclerView.ViewHolder {

    public TextView mRegNumber;
    public TextView mMarks;

    public StudentSectionHolder(View itemView) {
        super(itemView);

        mRegNumber = (TextView)itemView.findViewById(R.id.regNumber);
        mMarks = (TextView)itemView.findViewById(R.id.marks);
    }
}
