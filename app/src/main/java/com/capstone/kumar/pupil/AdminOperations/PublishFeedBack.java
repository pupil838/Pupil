package com.capstone.kumar.pupil.AdminOperations;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.kumar.pupil.Model.SubjectiveHolder;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.studentFeedBack.StudentFeedModel;
import com.capstone.kumar.pupil.utils.ObjectiveFeedBack;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by kumar on 3/19/2018.
 */

public class PublishFeedBack extends AppCompatActivity {
    private static final String TAG = "PublishFeedBack";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query mQuery;
    private Query mQuery1;

    //widgets
    private TextView mKey,mOne,mTwo,mThree,mFour,mFive,mTotal;
    private BarChart mChart;
    private TextView mTechnical;
    private TextView mHr;
    private TextView mExtra;

    private double first,second,third,four,five,total;

    private Button mPublish;

    //var
    private String clickButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_feedback);

        mPublish = (Button) findViewById(R.id.publish_btn);

        mKey = (TextView) findViewById(R.id.key);
        mChart = (BarChart) findViewById(R.id.chart1);
        mOne = (TextView) findViewById(R.id.one);
        mTwo = (TextView) findViewById(R.id.two);
        mThree = (TextView) findViewById(R.id.three);
        mFour = (TextView) findViewById(R.id.four);
        mFive = (TextView) findViewById(R.id.five);
        mTotal = (TextView) findViewById(R.id.total);

        mTechnical = (TextView) findViewById(R.id.technicalfeed);
        mHr = (TextView) findViewById(R.id.hrfeed);
        mExtra = (TextView) findViewById(R.id.extrafeed);
        mChart.getContentDescription();

        //setData(5);
        mChart.getContentDescription();
        mChart.setDrawGridBackground(false);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        final String key = getIntent().getStringExtra("feedback_key");
        final String companyKey = getIntent().getStringExtra("company_key");




        mQuery1  = mRef.child(getString(R.string.db_objective))
                .child(companyKey);

        mQuery = mRef.child(getString(R.string.db_student_feedBack)).child(companyKey)
                .child(key);
        //mQuery.keepSynced(true);



        makingGraph();

        fetchAllSubjectiveFeedback();

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                StudentFeedModel  feedModel = dataSnapshot.getValue(StudentFeedModel.class);

                clickButton = feedModel.getAuthority();
                mPublish.setText(clickButton);

                mPublish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(clickButton.equals("UnPublish")){

                            mPublish.setText("UnPublished");

                            mRef.child(getString(R.string.db_student_feedBack))
                                    .child(companyKey)
                                    .child(key).child("authority")
                                    .setValue("Publish");

                        }else if(clickButton.equals("Publish")){

                            mPublish.setText("Publish");

                            mRef.child(getString(R.string.db_student_feedBack))
                                    .child(companyKey)
                                    .child(key).child("authority")
                                    .setValue("UnPublish");

                        }

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void fetchAllSubjectiveFeedback(){


        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                StudentFeedModel  feedModel = dataSnapshot.getValue(StudentFeedModel.class);

                mTechnical.setText(feedModel.getTechnical_feedBack());
                mHr.setText(feedModel.getHr_feedBack());
                mExtra.setText(feedModel.getExtra_feedBack());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void makingGraph(){

        mQuery1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ObjectiveFeedBack object = dataSnapshot.getValue(ObjectiveFeedBack.class);
                String companyName =object.getCompany_Name();

                first = object.getOne()/(3.0*object.getFeedbacknumber())*100;
                second = object.getTwo()/(3.0*object.getFeedbacknumber())*100;
                third = object.getThree()/(3.0*object.getFeedbacknumber())*100;
                four = object.getFour()/(3.0*object.getFeedbacknumber())*100;
                five = object.getFive()/(3.0*object.getFeedbacknumber())*100;
                total = object.getTotal()/(3.0*5.0*object.getFeedbacknumber())*100;

                mKey.setText(companyName);
                mOne.setText(Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(first))));
                mTwo.setText(Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(second))));
                mThree.setText(Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(third))));
                mFour.setText(Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(four))));
                mFive.setText(Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(five))));
                mTotal.setText(Double.toString(Double.parseDouble(new DecimalFormat("##.##").format(total))));

                setData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setData(){
        ArrayList<BarEntry> yVals = new ArrayList<>();

        yVals.add(new BarEntry(0,(float) first));
        yVals.add(new BarEntry(1,(float) second));
        yVals.add(new BarEntry(2,(float) third));
        yVals.add(new BarEntry(3,(float) four));
        yVals.add(new BarEntry(4,(float) five));
        yVals.add(new BarEntry(5,(float) total));

        BarDataSet set = new BarDataSet(yVals,"Data Set");

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.argb(255,200,123,50));
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        set.setColors(colors);

        set.setBarShadowColor(Color.rgb(203,203,203));

        set.setDrawValues(true);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setLabelCount(5,false);
        yAxis.setSpaceTop(10f);

        YAxis right = mChart.getAxisRight();
        right.setLabelCount(5,false);
        right.setSpaceTop(10f);

        mChart.setFitBars(true);

        mChart.animateY(500);

        BarData data  = new BarData(set);
        mChart.setData(data);
        data.setBarWidth(0.9f);
        mChart.invalidate();

    }

}
