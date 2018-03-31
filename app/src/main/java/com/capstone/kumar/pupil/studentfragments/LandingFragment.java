package com.capstone.kumar.pupil.studentfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.Model.MyHolder;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.studentFeedBack.StudentFeedModel;
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

        mQuery = myRef.child(getString(R.string.db_student_feedBack)).orderByValue();
        mQuery.keepSynced(true);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.listOfAllDrive);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        allRecord();

    }

    private void allRecord(){

        FirebaseRecyclerAdapter<StudentFeedModel, MyHolder> recyclerAdapter = new
                FirebaseRecyclerAdapter<StudentFeedModel, MyHolder>(
                        StudentFeedModel.class,
                        R.layout.feedback_items,
                        MyHolder.class, mQuery
                ) {
                    @Override
                    protected void populateViewHolder(MyHolder viewHolder, StudentFeedModel model, int position) {

                        viewHolder.mCompanyName.setText(model.getTechnical_feedBack());
                        viewHolder.mFeedBack.setText(model.getHr_feedBack());
//                        viewHolder.mUserName.setText(model.getExtra_feedBack());

                        viewHolder.mReadFull.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        });
                    }
                };

        mRecyclerView.setAdapter(recyclerAdapter);

    }
}
