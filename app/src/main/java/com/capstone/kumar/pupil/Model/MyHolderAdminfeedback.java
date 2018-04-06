package com.capstone.kumar.pupil.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 3/18/2018.
 */

public class MyHolderAdminfeedback extends RecyclerView.ViewHolder {
    private static final String TAG = "MyHolder";

    public TextView mCompanyName;
    public TextView mClickMe;

    public MyHolderAdminfeedback(View itemView) {
        super(itemView);

        mCompanyName = (TextView) itemView.findViewById(R.id.get_companyName);
        mClickMe = (TextView) itemView.findViewById(R.id.click_me);
    }

}
