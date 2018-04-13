package com.capstone.kumar.pupil.TrendQuest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.Model.TrendQueComHolder;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.TrendingQuestionModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by kumar on 4/10/2018.
 */

public class AdminTrendSecondPage extends AppCompatActivity {
    private static final String TAG = "AdminTrendSecondPage";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query mQuery;

    private RecyclerView mCompanyList;
    private ImageView mEnterQuest;

    private String mAdmin;
    private String getCategory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_trend_secondpage);

        mDatabase =  FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();


        getCategory = getIntent().getStringExtra("category");

        mEnterQuest = (ImageView) findViewById(R.id.enter_quest);

        mCompanyList = (RecyclerView) findViewById(R.id.trendingCompanyList);

        mAuth = FirebaseAuth.getInstance();
        mAdmin  = mAuth.getCurrentUser().getUid();

        if(mAdmin.equals(getString(R.string.admin_ID))) {
            mEnterQuest.setVisibility(View.VISIBLE);

            mEnterQuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(AdminTrendSecondPage.this, EnterTrendQuest.class);
                    intent.putExtra("categoryName", getCategory);
                    startActivity(intent);
                }
            });

        }else{

            mEnterQuest.setVisibility(View.GONE);

        }

        mQuery = mRef.child(getString(R.string.db_trending_questions))
                .child(getCategory)
                .orderByValue();
        mQuery.keepSynced(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mCompanyList.setLayoutManager(layoutManager);

        companyList();
    }

    private void  companyList(){
        FirebaseRecyclerAdapter<TrendingQuestionModel,TrendQueComHolder> adapter = new FirebaseRecyclerAdapter<TrendingQuestionModel,
                TrendQueComHolder>
                (TrendingQuestionModel.class,
                        R.layout.tredques_company_item,
                        TrendQueComHolder.class,mQuery) {
            @Override
            protected void populateViewHolder(TrendQueComHolder viewHolder, TrendingQuestionModel model, int position) {
                final String key = getRef(position).getKey();

                viewHolder.companyName.setText(model.getCompanyName());
                viewHolder.question.setText(model.getTrendingQuestion());

                viewHolder.clickMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(AdminTrendSecondPage.this, "Click", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),AdminTrendThirdPage.class);
                        intent.putExtra("categoryName",getCategory);
                        intent.putExtra("QuestionKey",key);
                        startActivity(intent);
                    }
                });
            }
        };
        mCompanyList.setAdapter(adapter);
    }

}
