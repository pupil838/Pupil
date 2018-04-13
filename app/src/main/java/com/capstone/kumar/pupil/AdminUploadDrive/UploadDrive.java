package com.capstone.kumar.pupil.AdminUploadDrive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.FirebaseMethods;

/**
 * Created by kumar on 3/24/2018.
 */

public class UploadDrive extends AppCompatActivity {
    private static final String TAG = "UploadDrive";

    private EditText mComany_name,mDriveDate,mSaleryPack,mStandingArea,mJobProfile;
    private EditText mSkillRequired,mBondDetail,mAboutComp,mJoiningDate,mJobLocation;
    private Button mUpload_Drive;
    private Context mContext;
    private FirebaseMethods mFirebaseMethods;

    private String comany_name,driveDate,saleryPack,standingArea,jobProfile;
    private String skillRequired,bondDetail,aboutComp,joiningDate,jobLocation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_drive);

        mComany_name = (EditText) findViewById(R.id.company_name);
        mDriveDate  =  (EditText) findViewById(R.id.drive_date);
        mSaleryPack =  (EditText) findViewById(R.id.salary_package);
        mStandingArea = (EditText) findViewById(R.id.standing_area);
        mJobProfile = (EditText) findViewById(R.id.job_profile);
        mSkillRequired = (EditText) findViewById(R.id.skillRequired);
        mBondDetail  = (EditText) findViewById(R.id.bondDetail);
        mAboutComp = (EditText)  findViewById(R.id.companyDetail);
        mJoiningDate = (EditText) findViewById(R.id.joiningDate);
        mJobLocation = (EditText) findViewById(R.id.jobLocation);



        mUpload_Drive = (Button) findViewById(R.id.upload_drive);
        mContext = UploadDrive.this;
        mFirebaseMethods = new FirebaseMethods(mContext);

     //   editTextClick();
//        getValueFromEditText();

        mUpload_Drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comany_name = mComany_name.getText().toString();
                driveDate = mDriveDate.getText().toString();
                saleryPack = mSaleryPack.getText().toString();
                standingArea = mStandingArea.getText().toString();
                jobProfile = mJobProfile.getText().toString();
                skillRequired = mSkillRequired.getText().toString();
                bondDetail = mBondDetail.getText().toString();
                aboutComp = mAboutComp.getText().toString();
                joiningDate = mJoiningDate.getText().toString();
                jobLocation  = mJobLocation.getText().toString();


                if(checkInput(comany_name,driveDate,saleryPack,standingArea,jobProfile
                        ,skillRequired,bondDetail,aboutComp,joiningDate)) {
                    mFirebaseMethods.uploadDrive(comany_name, driveDate, saleryPack, standingArea, jobProfile
                            , skillRequired, bondDetail, aboutComp, joiningDate, jobLocation);

                    Toast.makeText(mContext, "Drive Uploaded Succesffully...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UploadDrive.this,MainActivity.class));
                    finish();

                }
            }
        });

    }

    private boolean checkInput(String driveName, String driveDate, String saleryPack, String standingArea,
                       String jobProfile, String skillRequired, String bondDetail,
                       String aboutComp, String joiningDate){

        if(driveName.equals("")||driveDate.equals("")||saleryPack.equals("")||standingArea.equals("")
                ||jobProfile.equals("")||skillRequired.equals("")||bondDetail.equals("")
                ||aboutComp.equals("")||joiningDate.equals("")){

            Toast.makeText(mContext, "All field must be feild out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void editTextClick(){
        mComany_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mComany_name.setText("");
                }else{
                    mComany_name.setText("Enter Company Name");
                }
            }
        });

        mDriveDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mDriveDate.setText("");
                }else{
                    mDriveDate.setText("Enter Drive Date");
                }
            }
        });

        mSaleryPack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mSaleryPack.setText("");
                }else{
                    mSaleryPack.setText("Enter Salary Package");
                }
            }
        });
        mStandingArea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mStandingArea.setText("");
                }else{
                    mStandingArea.setText("Standing Area");
                }
            }
        });
        mJobProfile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mJobProfile.setText("");
                }else{
                    mJobProfile.setText("Enter Job Profile");
                }
            }
        });
        mSkillRequired.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mSkillRequired.setText("");
                }else{
                    mSkillRequired.setText("Skill Required");
                }
            }
        });
        mBondDetail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mBondDetail.setText("");
                }else{
                    mBondDetail.setText("Enter Bond Detail");
                }
            }
        });
        mAboutComp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mAboutComp.setText("");
                }else{
                    mAboutComp.setText("About Company");
                }
            }
        });
        mJoiningDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mJoiningDate.setText("");
                }else{
                    mJoiningDate.setText("Enter Joining Date");
                }
            }
        });

    }
}
