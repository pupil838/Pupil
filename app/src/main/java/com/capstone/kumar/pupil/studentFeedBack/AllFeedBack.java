package com.capstone.kumar.pupil.studentFeedBack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.Model.MyHolder;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.ObjectiveFeedBack;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by kumar on 3/17/2018.
 */

public class AllFeedBack extends BaseFragment {
    private static final String TAG = "AllFeedBack";

    //firebase
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Query mQuery;

    private RecyclerView mRecyclerView;



    public static AllFeedBack create(){
        return new AllFeedBack();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.all_feedback_student;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

//            mQuery = myRef.child(getString(R.string.db_student_feedBack))
//                     .orderByChild("authority").equalTo(true);

        mQuery = myRef.child(getString(R.string.db_objective)).orderByValue();
            mQuery.keepSynced(true);

            mRecyclerView = (RecyclerView) root.findViewById(R.id.feedBack_recyclerView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setReverseLayout(true);
            layoutManager.setStackFromEnd(true);
            mRecyclerView.setLayoutManager(layoutManager);

            allRecord();

    }
//
//    private void allRecord(){
//
//            FirebaseRecyclerAdapter<StudentFeedModel, MyHolder> recyclerAdapter = new
//                    FirebaseRecyclerAdapter<StudentFeedModel, MyHolder>(
//                            StudentFeedModel.class,
//                            R.layout.feedback_items,
//                            MyHolder.class, mQuery
//                    ) {
//                        @Override
//                        protected void populateViewHolder(MyHolder viewHolder, StudentFeedModel model, int position) {
//                            viewHolder.mCompanyName.setText(model.getTechnical_feedBack());
//                            viewHolder.mFeedBack.setText(model.getHr_feedBack());
//                            viewHolder.mUserName.setText(model.getExtra_feedBack());
//                            viewHolder.mReadFull.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    startActivity(new Intent(getContext(), MainActivity.class));
//                                }
//                            });
//                        }
//                    };
//
//            mRecyclerView.setAdapter(recyclerAdapter);
//
//    }

    private void allRecord(){

        FirebaseRecyclerAdapter<ObjectiveFeedBack,MyHolder> object = new FirebaseRecyclerAdapter<ObjectiveFeedBack, MyHolder>(
                ObjectiveFeedBack.class,
                R.layout.feedback_items,
                MyHolder.class,mQuery
        ) {
            @Override
            protected void populateViewHolder(MyHolder viewHolder, ObjectiveFeedBack model, int position) {


                String fiveLevel = "";
                String totalLevel="";
                double totlL = model.getTotal()/(3.0*5.0*model.getFeedbacknumber())*100;
                double driveL = model.getFive()/(3.0*model.getFeedbacknumber())*100;

//                fiveLevel = Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(driveL)));
                totalLevel = Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(totlL)));
                double condition = Double.parseDouble(new DecimalFormat("##.##").format(totlL));
                viewHolder.itemView.setBackgroundColor(Color.GREEN);
                if(condition<40.00){
                    viewHolder.mFeedBack.setText("The Level of Drive EASY");
                    viewHolder.mFeedBack.setTextColor(Color.GREEN);
                }else if(condition>=40.00 & condition<70.00){
                    viewHolder.mFeedBack.setText("The Level of Drive AVERAGE");
                    viewHolder.mFeedBack.setTextColor(Color.YELLOW);
                }else if(condition>=70.00 & condition<=100){
                    viewHolder.mFeedBack.setText("The Level of Drive HARD");
                    viewHolder.mFeedBack.setTextColor(Color.RED);
                }

                viewHolder.mCompanyName.setText(model.getCompany_Name());
 //               viewHolder.mFeedBack.setText("Level of Drive Interview "+totalLevel+"%");

//                viewHolder.mUserName.setText("Fifth ques "+fiveLevel+"%");
                final String key = model.getCompanyId();
                viewHolder.mReadFull.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(),ReadFullFeedBack.class);
                        intent.putExtra("Company_Key",key);
                        startActivity(intent);
                    }
                });
            }
        };
        mRecyclerView.setAdapter(object);
    }

}
