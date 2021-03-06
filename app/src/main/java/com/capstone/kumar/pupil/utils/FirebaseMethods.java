package com.capstone.kumar.pupil.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.TrendQuest.AdminTrendSecondPage;
import com.capstone.kumar.pupil.TrendQuest.AdminTrendingQuestion;
import com.capstone.kumar.pupil.studentFeedBack.StudentFeedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static java.security.AccessController.getContext;

/**
 * Created by kumar on 3/11/2018.
 */

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    //widgets
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private String user_ID;

    public FirebaseMethods(Context mContext) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        this.mContext = mContext;

        if(mAuth.getCurrentUser() != null){
            user_ID = mAuth.getCurrentUser().getUid();
        }

    }

    public void registerNewEmail(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if(!task.isSuccessful()){

                            Toast.makeText(mContext, "Failed to Authentication", Toast.LENGTH_SHORT).show();

                        }else{
                            sendVerificationEmail();
                            user_ID = mAuth.getCurrentUser().getUid();
                            Log.d(TAG,"onCompleted, AuthState Changed " + user_ID);

                        }
                    }
                });
    }

    private void sendVerificationEmail(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(mContext, "Receive Email Varification", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(mContext, "Coudn't Send Email Verification Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void addNewUser(String email,String userName,String password,Double cgpa){
        User_SignUp user = new User_SignUp(user_ID,userName,email,password,cgpa);
        mDatabaseReference.child(mContext.getString(R.string.db_User))
                .child(mAuth.getCurrentUser().getUid())
                .child(mContext.getString(R.string.db_user_info))
                .setValue(user);
    }

    /**
     * Objective feedback
     */

    public void addObjective(final String driveKey, final String companyName, final int one, final int two, final int three, final int four,
                             final int five, final String authority){

        final int[] objectRecord = new int[7];
        final int[] total = {0};
        final int[] numberfeedback = {0};
        Query query = mDatabaseReference.child(mContext.getString(R.string.db_User))
                .child(mAuth.getCurrentUser().getUid()).child(mContext.getString(R.string.db_user_info));

        Query query1 = mDatabaseReference.child(mContext.getString(R.string.db_objective))
                .child(driveKey).orderByValue();

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ObjectiveFeedBack object = dataSnapshot.getValue(ObjectiveFeedBack.class);
                try {
                    objectRecord[0] = object.getOne() + one;
                    objectRecord[1] = object.getTwo() + two;
                    objectRecord[2] = object.getThree() + three;
                    objectRecord[3] = object.getFour() + four;
                    objectRecord[4] = object.getFive() + five;
                    objectRecord[5] = object.getFeedbacknumber();
                }catch (NullPointerException e){
                    objectRecord[0] = 0;
                    objectRecord[1] = 0;
                    objectRecord[2] = 0;
                    objectRecord[3] = 0;
                    objectRecord[4] = 0;
                    objectRecord[5] = 0;

                }

                for(int i=0; i<5; i++){
                    total[0] += objectRecord[i];
                }

                numberfeedback[0] = objectRecord[5] + 1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User_SignUp signup = dataSnapshot.getValue(User_SignUp.class);

                String userID = (String) signup.getUser_ID();
//              Toast.makeText(mContext, "userName "+userName, Toast.LENGTH_SHORT).show();

                final int one1 = objectRecord[0];
                final int two1 = objectRecord[1];
                final int three1 = objectRecord[2];
                final int four1 = objectRecord[3];
                final int five1 = objectRecord[4];
                final int total1  = total[0];
                final int totalfeedback = numberfeedback[0];
                insertObjectiveFeedBack(driveKey,companyName,one1,two1,three1,four1,five1,totalfeedback,total1,userID,authority);

                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void insertObjectiveFeedBack(String driveKey,String companyName,final int one, final int two, final int three,
                                         final int four, final int five,final int totalfeed,final int total
            ,String userID, final String authority){

        ObjectiveFeedBack objectiveFeed = new ObjectiveFeedBack(one,two,three,four,five,totalfeed,
                        total,userID,driveKey,companyName,authority);

        mDatabaseReference.child(mContext.getString(R.string.db_objective))
                .child(driveKey)
                .setValue(objectiveFeed);

    }
    /**
     * FeedBack operation start
     */

    public void addFeedBack(final String driveKey, final String companyName, final String technical, final String hr,
                            final String extra, final String authority){

        Query query = mDatabaseReference.child(mContext.getString(R.string.db_User))
                .child(mAuth.getCurrentUser().getUid()).child(mContext.getString(R.string.db_user_info));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User_SignUp signup = dataSnapshot.getValue(User_SignUp.class);

                String userID = (String) signup.getUser_ID();
//                Toast.makeText(mContext, "userName "+userName, Toast.LENGTH_SHORT).show();
                insertFeedBack(driveKey, companyName,userID,technical,hr,extra,authority);

                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void insertFeedBack(String driveKey, String companyName,String userID,String technical,String hr,String extra
                                 , String authority){
        StudentFeedModel studentFeedback =  new StudentFeedModel(userID,companyName,technical,hr,extra,driveKey,authority);
        mDatabaseReference.child(mContext.getString(R.string.db_student_feedBack))
                .child(driveKey)
                .push().setValue(studentFeedback);

    }

    public void uploadDrive(final String driveName, final String driveDate, final String saleryPack, final String standingArea,
                            final String jobProfile, final String skillRequired, final String bondDetail,
                            final String aboutComp, final String joiningDate, final String jobLocation){

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                insertDrive(driveName,driveDate,saleryPack,standingArea,
                        jobProfile,skillRequired,bondDetail,aboutComp,joiningDate,jobLocation);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void insertDrive(String driveName, String driveDate, String saleryPack, String standingArea,
                             String jobProfile, String skillRequired, String bondDetail,
                             String aboutComp, String joiningDate, String jobLocation){

        UploadDriveModel driveModel = new UploadDriveModel(driveName,driveDate,saleryPack,standingArea,
                jobProfile,skillRequired,bondDetail,aboutComp,joiningDate,jobLocation);

        mDatabaseReference.child(mContext.getString(R.string.db_upload_drive))
                .push().setValue(driveModel);

    }


    public void aMCATDATA(final String regNum, final String english, final String quant, final String logical, final String ampi,
                          final String info, final String compProg, final String compSci){



        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                addAmCatValues(regNum,english,quant,logical,ampi,info,compProg,compSci);
                mContext.startActivity(new Intent(mContext,MainActivity.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addAmCatValues(String regNum, String english, String quant, String logical, String ampi,
                                String info, String compProg, String compSci){

        AmcatMarksUpload amcatMarksUpload = new AmcatMarksUpload(regNum, english, quant, logical,
                ampi, info, compProg, compSci);

        mDatabaseReference.child(mContext.getString(R.string.db_amcat_marks))
                .child(mAuth.getCurrentUser().getUid())
                .setValue(amcatMarksUpload);

    }

    public void addTrendingQuestion(final String categoryName, final String drivekey, final String companyName,
                                    final String question, final String answer){

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                insertTrendingQuestion(categoryName,drivekey,companyName,question,answer);
                mContext.startActivity(new Intent(mContext,AdminTrendingQuestion.class));

             }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void insertTrendingQuestion(String categoryName,String driveKey,String companyName
            ,String question,String answer){

        TrendingQuestionModel trendingQuestionModel = new TrendingQuestionModel(companyName,question,answer,driveKey);

        mDatabaseReference.child(mContext.getString(R.string.db_trending_questions)).child(categoryName)
                .push().setValue(trendingQuestionModel);

    }

}
