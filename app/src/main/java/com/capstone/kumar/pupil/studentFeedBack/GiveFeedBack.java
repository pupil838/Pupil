package com.capstone.kumar.pupil.studentFeedBack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.FirebaseMethods;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by kumar on 3/17/2018.
 */

public class GiveFeedBack extends BaseFragment {
    private static final String TAG = "GiveFeedBack";

    private FirebaseMethods firebaseMethods;

    private EditText mDriveName;
    private EditText mRegNumber;
    private EditText  mFeedBack;
    private Button mSubmit;
    private boolean authority = false;
    private ProgressDialog mProgressDialog;

    //var
    private String regnumber;
    private String drivename;
    private String feedback;
    private Context  mContext;

    public static GiveFeedBack create(){
        return new GiveFeedBack();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.give_feedback_student;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRegNumber = (EditText)root.findViewById(R.id.reg_num);
        mDriveName = (EditText)root.findViewById(R.id.drive_name);
        mFeedBack = (EditText)root.findViewById(R.id.give_feedback);
        mSubmit = (Button)root.findViewById(R.id.submit_feedback);
        mContext =getActivity();
        firebaseMethods = new FirebaseMethods(mContext);
        mProgressDialog = new ProgressDialog(mContext);


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regnumber  =  mRegNumber.getText().toString();
                drivename = mDriveName.getText().toString();
                feedback = mFeedBack.getText().toString();

                mProgressDialog.setMessage("FeedBack Uploading...");
                mProgressDialog.show();

                if(checkInput(regnumber,drivename,feedback)){
//                    Toast.makeText(mContext, regnumber+ " "+drivename+" "+feedback, Toast.LENGTH_SHORT).show();

                    firebaseMethods.addFeedBack(regnumber, drivename, feedback, authority);


                }
            }
        });
    }

    private boolean checkInput(String regnum, String driveNam, String feedBak){

        if(regnum.equals("")||driveNam.equals("")||feedBak.equals("")){

            Toast.makeText(mContext, "All field must be feild out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
