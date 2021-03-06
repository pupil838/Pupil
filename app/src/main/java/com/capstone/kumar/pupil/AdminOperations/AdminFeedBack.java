package com.capstone.kumar.pupil.AdminOperations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.Model.MyHolder;
import com.capstone.kumar.pupil.Model.MyHolderAdminfeedback;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.studentFeedBack.StudentFeedModel;
import com.capstone.kumar.pupil.utils.UploadDriveModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by kumar on 3/19/2018.
 */

public class AdminFeedBack  extends AppCompatActivity {
    //firebase
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Query mQuery;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_feedback);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);

        mQuery = myRef.child(getString(R.string.db_upload_drive)).orderByValue();
        mQuery.keepSynced(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.feedBack_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(AdminFeedBack.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(layoutManager);

        allRecord();
    }

    private void allRecord(){


        FirebaseRecyclerAdapter<UploadDriveModel, MyHolderAdminfeedback> recyclerAdapter = new
                FirebaseRecyclerAdapter<UploadDriveModel, MyHolderAdminfeedback>(
                        UploadDriveModel.class,
                        R.layout.admin_company_feedback_items,
                        MyHolderAdminfeedback.class, mQuery
                ) {
                    @Override
                    protected void populateViewHolder(MyHolderAdminfeedback viewHolder, UploadDriveModel model, int position) {
                        final String key = getRef(position).getKey();

                        viewHolder.mCompanyName.setText(model.getCompany_name());
                        viewHolder.mClickMe.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(AdminFeedBack.this, MainActivity.class));
                            }
                        });

                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AdminFeedBack.this, "Key "+key, Toast.LENGTH_SHORT).show();
                                Intent intent  = new Intent(AdminFeedBack.this,CompanyFeedBack.class);
                                intent.putExtra("feedback_key",key);
                                startActivity(intent);
                            }
                        });
                    }
                };

        mRecyclerView.setAdapter(recyclerAdapter);

    }


}
