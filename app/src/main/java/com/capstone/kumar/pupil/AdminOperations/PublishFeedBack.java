package com.capstone.kumar.pupil.AdminOperations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.studentFeedBack.StudentFeedModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by kumar on 3/19/2018.
 */

public class PublishFeedBack extends AppCompatActivity {
    private static final String TAG = "PublishFeedBack";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query mQuery;

    //widgets
    private Button mPublish;
    private TextView mCompany,mRegNum,mFeedback;

    //var
    private boolean clickButton = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_feedback);

        mPublish = (Button) findViewById(R.id.publish_btn);
        mCompany = (TextView) findViewById(R.id.company_name);
        mRegNum = (TextView) findViewById(R.id.user_name);
        mFeedback = (TextView) findViewById(R.id.feedback);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        final String key = getIntent().getStringExtra("feedback_key");

        mPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickButton == false){
                    mPublish.setText("UnPublish");

                    mRef.child(getString(R.string.db_student_feedBack))
                            .child(key).child("authority")
                             .setValue(true);

                    clickButton = true;
                }else if(clickButton == true){
                    mPublish.setText("Publish");

                    mRef.child(getString(R.string.db_student_feedBack))
                            .child(key).child("authority")
                            .setValue(false);

                    clickButton = false;
                }
            }
        });



        mQuery = mRef.child(getString(R.string.db_student_feedBack))
                .child(key);
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                StudentFeedModel  feedModel = dataSnapshot.getValue(StudentFeedModel.class);

                mCompany.setText(feedModel.getTechnical_feedBack());
                mRegNum.setText(feedModel.getHr_feedBack());
                mFeedback.setText(feedModel.getExtra_feedBack());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
