package com.capstone.kumar.pupil.TrendQuest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.FirebaseMethods;
import com.capstone.kumar.pupil.utils.UploadDriveModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by kumar on 4/10/2018.
 */

public class EnterTrendQuest extends AppCompatActivity {
    private static final String TAG = "EnterTrendQuest";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseMethods firebaseMethods;

    private Spinner mDriveName;
    private EditText mQuestion;
    private EditText mAnswer;
    private Button mSubmit;

    private String drivekey;
    private String companyName;
    private String question;
    private String answer;
    private Context mContext;

    private ProgressDialog mProgressDialog;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_trend_enterquest);

        mContext = EnterTrendQuest.this;
        firebaseMethods = new FirebaseMethods(mContext);
        mProgressDialog = new ProgressDialog(mContext);

        mDriveName = (Spinner) findViewById(R.id.company_choose);
        mQuestion = (EditText) findViewById(R.id.question);
        mAnswer  = (EditText) findViewById(R.id.answer);
        mSubmit = (Button) findViewById(R.id.submit_trendQuest);

        final String categoryName = getIntent().getStringExtra("categoryName");
        arrayList = new ArrayList<String>();
        spinner();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mProgressDialog.setMessage("Trending Question Uploading...");
                mProgressDialog.show();

                question = mQuestion.getText().toString();
                answer = mAnswer.getText().toString();

                firebaseMethods.addTrendingQuestion(categoryName,drivekey,companyName,question,answer);


            }
        });
    }

    private void spinner(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        /**
         * for fetching value in spinner from firebase.
         */
        Query mQuery = mRef.child(getString(R.string.db_upload_drive))
                .orderByValue();

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(final DataSnapshot ds: dataSnapshot.getChildren()){
                    UploadDriveModel model = ds.getValue(UploadDriveModel.class);
                    arrayList.add(model.getCompany_name());


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,arrayList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mDriveName.setAdapter(adapter);

                    /**
                     * after select name from spinner which company choose get its key value
                     */
                    mDriveName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            companyName = arrayList.get(position);

                            //Toast.makeText(mContext, "Clicked value ", Toast.LENGTH_SHORT).show();

                            /**
                             * for getting key value of Company name
                             */
                            Query query = mRef.child(getString(R.string.db_upload_drive))
                                    .orderByChild(getString(R.string.db_drive_field_nameCompany))
                                    .equalTo(companyName);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot childSnap : dataSnapshot.getChildren()) {

                                        drivekey = childSnap.getKey();

                                        Toast.makeText(mContext, "Pressed " + drivekey, Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }//end of onSelected item

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//end of addValueListener

    }

}
