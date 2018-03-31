package com.capstone.kumar.pupil.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 3/31/2018.
 */

public class SubjectiveHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "SubjectiveHolder";

    public TextView mTechnical;
    public TextView mHr;
    public TextView mExtra;
    public SubjectiveHolder(View itemView) {
        super(itemView);

        mTechnical = (TextView) itemView.findViewById(R.id.technicalfeed);
        mHr = (TextView) itemView.findViewById(R.id.hrfeed);
        mExtra = (TextView) itemView.findViewById(R.id.extrafeed);
    }
}
