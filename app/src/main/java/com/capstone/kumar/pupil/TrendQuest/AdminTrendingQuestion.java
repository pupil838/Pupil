package com.capstone.kumar.pupil.TrendQuest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.R;

/**
 * Created by kumar on 4/10/2018.
 */

public class AdminTrendingQuestion  extends AppCompatActivity {
    private static final String TAG = "AdminTrendingQuestion";

    private ImageView mEnglish,mQuant,mLogical,mAMPI,mInfo,mCompProg,mCompSci;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_trending_question);

        initwidgets();
        callAction();
    }

    private void initwidgets(){

        mEnglish = (ImageView) findViewById(R.id.category_english);
        mQuant = (ImageView) findViewById(R.id.category_quant);
        mLogical = (ImageView)  findViewById(R.id.category_logical);
        mAMPI = (ImageView) findViewById(R.id.category_ampi);
        mInfo = (ImageView) findViewById(R.id.category_info);
        mCompProg = (ImageView) findViewById(R.id.category_compProg);
        mCompSci = (ImageView) findViewById(R.id.category_compSci);

    }

    private void callAction(){

        mEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "English", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","English");
                startActivity(intent);
            }
        });

        mQuant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "Quantitative", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","Quantitative");
                startActivity(intent);
            }
        });

        mLogical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "Logical", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","Logical");
                startActivity(intent);
            }
        });

        mAMPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "AMPI", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","AMPI");
                startActivity(intent);
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "INFO", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","Information");
                startActivity(intent);
            }
        });

        mCompProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "CompProgram", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","ComputerProgram");
                startActivity(intent);
            }
        });

        mCompSci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminTrendingQuestion.this, "Comp Science", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminTrendingQuestion.this,AdminTrendSecondPage.class);
                intent.putExtra("category","ComputerScience");
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        moveTaskToBack(false);

        startActivity(new Intent(AdminTrendingQuestion.this, MainActivity.class));
        finish();
    }

}
