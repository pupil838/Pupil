package com.capstone.kumar.pupil.AdminUploadDrive;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.FirebaseMethods;

/**
 * Created by kumar on 3/24/2018.
 */

public class UploadDrive extends AppCompatActivity {
    private static final String TAG = "UploadDrive";

    private EditText mComany_name;
    private Button mUpload_Drive;
    private Context mContext;
    private FirebaseMethods mFirebaseMethods;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_drive);

        mComany_name = (EditText) findViewById(R.id.company_name);
        mUpload_Drive = (Button) findViewById(R.id.upload_drive);
        mContext = UploadDrive.this;
        mFirebaseMethods = new FirebaseMethods(mContext);

        mUpload_Drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String driveName = mComany_name.getText().toString();
                mFirebaseMethods.uploadDrive(driveName);

            }
        });
    }
}
