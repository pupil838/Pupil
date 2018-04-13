package com.capstone.kumar.pupil.studentfragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.FirebaseMethods;

/**
 * Created by kumar on 4/7/2018.
 */

public class AmcatEnterFeildFragment extends BaseFragment {
    private static final String TAG = "AmcatEnterFeildFragment";

    private FirebaseMethods mFirebaseMethods;

    private EditText mEnglish,mQuant,mLogical,mAMPI,mInfo,mCompProg,mCompSci,mRegNumber;
    private Button mSubmit;

    private String english,quant,logical,ampi,info,compProg,compSci,regNum;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_enter_amcat_feild;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        gadgetInit(root);

    }

    private void gadgetInit(View root){


        mRegNumber = (EditText)root.findViewById(R.id.amcat_regNumber);
        mEnglish = (EditText)root.findViewById(R.id.amcat_english);
        mQuant = (EditText)root.findViewById(R.id.amcat_Quant);
        mLogical = (EditText)root.findViewById(R.id.amcat_Logical);
        mAMPI = (EditText)root.findViewById(R.id.amcat_ampi);
        mInfo = (EditText)root.findViewById(R.id.amcat_IGS);
        mCompProg = (EditText)root.findViewById(R.id.amcat_compProgramming);
        mCompSci = (EditText)root.findViewById(R.id.amcat_compScience);
        mSubmit = (Button)root.findViewById(R.id.amcat_submit);

        mContext = getActivity();
        mFirebaseMethods = new FirebaseMethods(mContext);

        mProgressDialog = new ProgressDialog(getContext());

        mRegNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mRegNumber.setHint("");
                }else{
                    mRegNumber.setHint("11504040");
                }
            }
        });

        mEnglish.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mEnglish.setHint("");
                }else{
                    mEnglish.setHint("65.5");
                }
            }
        });

        mQuant.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mQuant.setHint("");
                }else{
                    mQuant.setHint("65.5");
                }
            }
        });

        mLogical.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mLogical.setHint("");
                }else{
                    mLogical.setHint("65.5");
                }
            }
        });

        mAMPI.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mAMPI.setHint("");
                }else{
                    mAMPI.setHint("65.5");
                }
            }
        });

        mInfo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mInfo.setHint("");
                }else{
                    mInfo.setHint("65.5");
                }
            }
        });

        mCompProg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mCompProg.setHint("");
                }else{
                    mCompProg.setHint("65.5");
                }
            }
        });

        mCompSci.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mCompSci.setHint("");
                }else{
                    mCompSci.setHint("65.5");
                }
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getContext(), "Hello AMCAT CLicked", Toast.LENGTH_SHORT).show();

                mProgressDialog.setMessage("Uploading...");
                mProgressDialog.show();

                regNum = mRegNumber.getText().toString();
                english = mEnglish.getText().toString();
                quant = mQuant.getText().toString();
                logical = mLogical.getText().toString();
                ampi = mAMPI.getText().toString();
                info = mInfo.getText().toString();
                compProg = mCompProg.getText().toString();
                compSci = mCompSci.getText().toString();

                mFirebaseMethods.aMCATDATA(regNum,english,quant,logical,ampi,info,compProg,compSci);

            }
        });
    }

}