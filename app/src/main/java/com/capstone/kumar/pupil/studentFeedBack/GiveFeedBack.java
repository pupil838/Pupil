package com.capstone.kumar.pupil.studentFeedBack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.MainActivity;
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
 * Created by kumar on 3/17/2018.
 */

public class GiveFeedBack extends BaseFragment {
    private static final String TAG = "GiveFeedBack";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseMethods firebaseMethods;

    private Spinner mDriveName;
    private RadioGroup firstGroup,secondGroup,thirdGroup,fourthGroup,fifthGroup;
    private EditText techFeedBack;
    private EditText  hrFeedBack;
    private EditText extraFeedBack;
    private Button mSubmit;
    private boolean authority = false;
    private ProgressDialog mProgressDialog;

    //var
    private String technicalFeed;
    private String extraFeed;
    private String hrFeed;
    private String drivekey;
    private String companyName;
    private Context  mContext;
    private ArrayList<String>  arrayList;
    int one,two,three,four,five;

    public static GiveFeedBack create(){
        return new GiveFeedBack();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.give_feedback_student;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mDriveName = (Spinner)root.findViewById(R.id.drive_name);

        firstGroup = (RadioGroup) root.findViewById(R.id.first_group);
        secondGroup = (RadioGroup) root.findViewById(R.id.second_group);
        thirdGroup = (RadioGroup) root.findViewById(R.id.third_group);
        fourthGroup = (RadioGroup) root.findViewById(R.id.fourth_group);
        fifthGroup = (RadioGroup) root.findViewById(R.id.fifth_group);

        techFeedBack = (EditText)root.findViewById(R.id.tech_feedback);
        hrFeedBack = (EditText)root.findViewById(R.id.hr_feedback);
        extraFeedBack = (EditText)root.findViewById(R.id.extra_feedback);

        mSubmit = (Button)root.findViewById(R.id.submit_feedback);

        mContext =getActivity();
        firebaseMethods = new FirebaseMethods(mContext);
        mProgressDialog = new ProgressDialog(mContext);
        arrayList = new ArrayList<String>();

        spinner();

        objectiveQuestion();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Value "+drivekey+" "+one+" "+two+" "+three+" "+four+" "+five, Toast.LENGTH_SHORT).show();

                technicalFeed  =  techFeedBack.getText().toString();
                extraFeed = extraFeedBack.getText().toString();
                hrFeed = hrFeedBack.getText().toString();

                mProgressDialog.setMessage("FeedBack Uploading...");
                mProgressDialog.show();

                if(checkInput(technicalFeed,hrFeed,extraFeed)){
                    Toast.makeText(mContext, technicalFeed+ " "+hrFeed+" "+extraFeed, Toast.LENGTH_SHORT).show();

                    firebaseMethods.addObjective(drivekey,companyName,one,two,three,four,five,authority);
                    firebaseMethods.addFeedBack(drivekey,companyName,technicalFeed, hrFeed, extraFeed, authority);


                }

            }
        });


    }

    private void objectiveQuestion(){


        firstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int first = 0;
                switch (checkedId){
                    case R.id.first_yes:
                        first = first+1;

                        Toast.makeText(mContext, "YES "+first+" "+drivekey, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.first_no:
                        first = first-1;
                        Toast.makeText(mContext, "NO "+first, Toast.LENGTH_SHORT).show();
                        break;
                }
                one = first;
            }
        });

        secondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int second = 0;
                switch (checkedId){
                    case R.id.second_easy:
                        second = second + 1;
                        Toast.makeText(mContext, "Second Low + key"+second+drivekey, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.second_average:
                        second = second + 2;
                        Toast.makeText(mContext, "Second Low + key"+second+drivekey, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.second_difficult:
                        second = second + 3;
                        Toast.makeText(mContext, "Second Average "+second, Toast.LENGTH_SHORT).show();
                        break;
                }
                two = second;
            }
        });

        thirdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int third = 0;
                switch (checkedId){
                    case R.id.third_easy:
                        third = third + 1;
                        Toast.makeText(mContext, "third Low "+third, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.third_average:
                        third = third + 2;
                        Toast.makeText(mContext, "Second Low + key"+third+drivekey, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.third_difficult:
                        third = third + 3;
                        Toast.makeText(mContext, "third Average "+third, Toast.LENGTH_SHORT).show();
                        break;
                }
                three = third;
            }
        });

        fourthGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int fourth = 0;
                switch (checkedId){
                    case R.id.fourth_easy:
                        fourth = fourth + 1;
                        Toast.makeText(mContext, "fourth Low "+fourth, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.fourth_average:
                        fourth = fourth + 2;
                        Toast.makeText(mContext, "Second Low + key"+fourth+drivekey, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.fourth_difficult:
                        fourth = fourth + 3;
                        Toast.makeText(mContext, "fourth Average "+fourth, Toast.LENGTH_SHORT).show();
                        break;
                }
                four = fourth;
            }
        });

        fifthGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int fifth = 0;
                switch (checkedId){
                    case R.id.fifth_easy:
                        fifth = fifth + 1;
                        Toast.makeText(mContext, "fifth Low "+fifth, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.fifth_average:
                        fifth = fifth + 2;
                        Toast.makeText(mContext, "Second Low + key"+fifth+drivekey, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.fifth_difficult:
                        fifth = fifth + 3;
                        Toast.makeText(mContext, "fifth Average "+fifth, Toast.LENGTH_SHORT).show();
                        break;
                }
                five = fifth;
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
