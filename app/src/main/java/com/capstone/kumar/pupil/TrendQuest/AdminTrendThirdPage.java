package com.capstone.kumar.pupil.TrendQuest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.TrendingQuestionModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by kumar on 4/12/2018.
 */

public class AdminTrendThirdPage extends AppCompatActivity {
    private static final String TAG = "AdminTrendThirdPage";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query mQuery;
    private Query mDelQuery;

    private TextView mQuestion;
    private TextView mCompanyName;
    private TextView mAnswer;
    private Button mDelTrendQue;

    private String categoryName;
    private String mQuestionKey;
    private String mAdmin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_tren_third_page);

        mDelTrendQue = (Button) findViewById(R.id.deleteTrendQuestion);
        mQuestion = (TextView) findViewById(R.id.question);
        mCompanyName = (TextView) findViewById(R.id.companyName);
        mAnswer = (TextView) findViewById(R.id.answer);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        categoryName = getIntent().getStringExtra("categoryName");
        mQuestionKey = getIntent().getStringExtra("QuestionKey");

        mQuery = mRef.child(getString(R.string.db_trending_questions))
                .child(categoryName).child(mQuestionKey);

        mAuth = FirebaseAuth.getInstance();
        mAdmin  = mAuth.getCurrentUser().getUid();

        if(mAdmin.equals(getString(R.string.admin_ID))) {
            mDelTrendQue.setVisibility(View.VISIBLE);
            /*
             for delete question
         */
            mDelTrendQue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mDelQuery = mRef.child(getString(R.string.db_trending_questions))
                            .child(categoryName).child(mQuestionKey).orderByValue();
                    mDelQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().removeValue();
                            Toast.makeText(AdminTrendThirdPage.this, "Value Deleted Successfully...", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AdminTrendingQuestion.class));
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });

        }else{

            mDelTrendQue.setVisibility(View.GONE);

        }

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TrendingQuestionModel trendingQuestionModel = dataSnapshot.getValue(TrendingQuestionModel.class);

              try{

                mQuestion.setText(trendingQuestionModel.getTrendingQuestion());
                mCompanyName.setText(trendingQuestionModel.getCompanyName());
                mAnswer.setText(trendingQuestionModel.getTrendingAnswer());

              }catch(NullPointerException e){

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
