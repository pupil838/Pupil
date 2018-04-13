package com.capstone.kumar.pupil.Section;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.capstone.kumar.pupil.Adapter.BaseFragment;
import com.capstone.kumar.pupil.Model.StudentSectionHolder;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.AmcatMarksUpload;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by kumar on 4/8/2018.
 */

public class SectionCreateFragment extends BaseFragment {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query mQuery;

    private EditText mSectionName,mLessThen;
    private Spinner mChooseSubject;
    private Button mShow,mMakeSection;
    private RecyclerView mRecycler;

    private Context mContext;

    private double lessThen,studentCount;
    private String sectionName;
    private String chooseSubject;
    private ArrayList<String> arrayList;   //for spinner.
    private ArrayList<String> sectionArrayList;  //it will store all value which fetch according to condition.

    @Override
    public int getLayoutRes() {
        return R.layout.section_create_fragment;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initGadget(root);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();
        mQuery = mRef.child(getString(R.string.db_amcat_marks)).orderByValue();
        mQuery.keepSynced(true);

    }

    private void initGadget(View root){
        mSectionName = (EditText)root.findViewById(R.id.sectionName);
        mLessThen = (EditText)root.findViewById(R.id.lessThen);
        mChooseSubject = (Spinner)root.findViewById(R.id.choose_subject);
        mShow = (Button)root.findViewById(R.id.show_list);
        mRecycler = (RecyclerView)root.findViewById(R.id.show_student_list);
        mMakeSection  = (Button)root.findViewById(R.id.make_section);

        mContext  = getActivity();
        arrayList = new ArrayList<String>();
        sectionArrayList = new ArrayList<String>();

        arrayList.add("english");
        arrayList.add("quant");
        arrayList.add("logical");
        arrayList.add("ampi");
        arrayList.add("info");
        arrayList.add("compProg");
        arrayList.add("compSci");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChooseSubject.setAdapter(adapter);

        mChooseSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chooseSubject = arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        mRecycler.setLayoutManager(layoutManager);

        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessThen = Double.valueOf(mLessThen.getText().toString());

                    showList(lessThen);
            }
        });

        mMakeSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Value "+sectionArrayList, Toast.LENGTH_SHORT).show();
            }
        });

}

    private void showList(final double condition){
        FirebaseRecyclerAdapter<AmcatMarksUpload,StudentSectionHolder> adapter = new FirebaseRecyclerAdapter<AmcatMarksUpload, StudentSectionHolder>
                (AmcatMarksUpload.class,
                        R.layout.show_student_list,
                        StudentSectionHolder.class,mQuery) {
            @Override
            protected void populateViewHolder(StudentSectionHolder viewHolder, AmcatMarksUpload model, int position) {

                double englishCondition = Double.valueOf(model.getEnglish());
                double quantCondition = Double.valueOf(model.getQuant());
                double logicalCondition = Double.valueOf(model.getLogical());
                double ampiCondition = Double.valueOf(model.getAmpi());
                double infoCondition = Double.valueOf(model.getInfo());
                double compProgCondition = Double.valueOf(model.getCompProg());
                double compSciCondition = Double.valueOf(model.getCompSci());

                if(chooseSubject == "english") {
                    if (englishCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getEnglish());
                        sectionArrayList.add(model.getEnglish());
                    }
                }else if(chooseSubject == "quant"){
                    if (quantCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getQuant());
                        sectionArrayList.add(model.getQuant());
                    }
                }else if(chooseSubject == "logical"){
                    if (logicalCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getLogical());
                        sectionArrayList.add(model.getLogical());
                    }
                }else if(chooseSubject == "ampi"){
                    if (ampiCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getAmpi());
                        sectionArrayList.add(model.getAmpi());
                    }
                }else if(chooseSubject == "info"){
                    if (infoCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getInfo());
                        sectionArrayList.add(model.getInfo());
                    }
                }else if(chooseSubject == "compProg"){
                    if (compProgCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getCompProg());
                        sectionArrayList.add(model.getCompProg());
                    }
                }else if(chooseSubject == "compSci"){
                    if (compSciCondition <= condition) {
                        viewHolder.mRegNumber.setText(model.getRegNum());
                        viewHolder.mMarks.setText(model.getCompSci());
                        sectionArrayList.add(model.getCompSci());
                    }
                }else{
                    Toast.makeText(mContext, "Wrong Data", Toast.LENGTH_SHORT).show();
                }
            }

        };

        mRecycler.setAdapter(adapter);

    }

}
