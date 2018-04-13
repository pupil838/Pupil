package com.capstone.kumar.pupil.studentfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.Model.DriveHolder;
import com.capstone.kumar.pupil.Model.MyHolder;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.studentFeedBack.StudentFeedModel;
import com.capstone.kumar.pupil.utils.UploadDriveModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by kumar on 3/17/2018.
 */

public class LandingFragment extends BaseFragment {
    private static final String TAG = "LandingFragment";

    //firebase
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Query mQuery;

    private RecyclerView mRecyclerView;

    @Override
    public int getLayoutRes() {
        return R.layout.landing_fragment;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);

        mQuery = myRef.child(getString(R.string.db_upload_drive))
                .orderByValue();
        mQuery.keepSynced(true);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.listOfAllDrive);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        allRecord();

    }

    private void allRecord(){

        FirebaseRecyclerAdapter<UploadDriveModel,DriveHolder>  recyclerAdapter = new FirebaseRecyclerAdapter<UploadDriveModel, DriveHolder>
                (UploadDriveModel.class,
                        R.layout.drive_show_item,
                        DriveHolder.class,mQuery) {
            @Override
            protected void populateViewHolder(DriveHolder viewHolder, UploadDriveModel model, int position) {
                viewHolder.mCompany.setText(model.getCompany_name());
                viewHolder.mSalary.setText(model.getSalary_Package());
                viewHolder.mStandingArea.setText(model.getStanding_Area());
                viewHolder.mLocation.setText(model.getJobLocation());
                viewHolder.mJobProfile.setText(model.getJob_Profile());
                viewHolder.mDriveDate.setText(model.getDrive_Date());
                viewHolder.mJoinDate.setText(model.getJoining_Date());

                viewHolder.mClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        mRecyclerView.setAdapter(recyclerAdapter);

    }

}
