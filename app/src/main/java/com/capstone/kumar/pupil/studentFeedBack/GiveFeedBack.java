package com.capstone.kumar.pupil.studentFeedBack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    private ArrayList<String>  arrayList;

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
        mDriveName = (Spinner)root.findViewById(R.id.drive_name);
        mFeedBack = (EditText)root.findViewById(R.id.give_feedback);
        mSubmit = (Button)root.findViewById(R.id.submit_feedback);
        mContext =getActivity();
        firebaseMethods = new FirebaseMethods(mContext);
        mProgressDialog = new ProgressDialog(mContext);
        arrayList = new ArrayList<String>();

        spinner();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regnumber  =  mRegNumber.getText().toString();
//                drivename = mDriveName.getText().toString();
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

    private void spinner(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        Query mQuery = mRef.child(getString(R.string.db_upload_drive))
                .orderByValue();

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    UploadDriveModel model = ds.getValue(UploadDriveModel.class);
                    arrayList.add(model.getCompany_name());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//
//        arrayList.add("HELLO1");
//        arrayList.add("HELLO2");
//        arrayList.add("HELLO3");
//        arrayList.add("HELLO4");
//        arrayList.add("HELLO5");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDriveName.setAdapter(adapter);
    }

}
