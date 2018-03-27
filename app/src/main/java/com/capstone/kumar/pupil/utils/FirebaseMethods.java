package com.capstone.kumar.pupil.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.capstone.kumar.pupil.MainActivity;
import com.capstone.kumar.pupil.R;
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

    public void addObjective(final String driveKey, final int one, final int two, final int three, final int four,
                             final int five, final boolean authority){
        Query query = mDatabaseReference.child(mContext.getString(R.string.db_User))
                .child(mAuth.getCurrentUser().getUid()).child(mContext.getString(R.string.db_user_info));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User_SignUp signup = dataSnapshot.getValue(User_SignUp.class);

                String userID = (String) signup.getUser_ID();
//                Toast.makeText(mContext, "userName "+userName, Toast.LENGTH_SHORT).show();
                insertObjectiveFeedBack(driveKey,one,two,three,four,five,userID,authority);

                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void insertObjectiveFeedBack(String driveKey,final int one, final int two, final int three,
                                         final int four, final int five,String userID, final boolean authority){
        ObjectiveFeedBack objectiveFeed = new ObjectiveFeedBack(one,two,three,four,five,userID,authority);

        mDatabaseReference.child(mContext.getString(R.string.db_objective))
                .child(driveKey)
                .push()
                .setValue(objectiveFeed);

    }
    /**
     * FeedBack operation start
     */

    public void addFeedBack(final String driveKey,final String technical, final String hr, final String extra, final boolean authority){

        Query query = mDatabaseReference.child(mContext.getString(R.string.db_User))
                .child(mAuth.getCurrentUser().getUid()).child(mContext.getString(R.string.db_user_info));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User_SignUp signup = dataSnapshot.getValue(User_SignUp.class);

                String userID = (String) signup.getUser_ID();
//                Toast.makeText(mContext, "userName "+userName, Toast.LENGTH_SHORT).show();
                insertFeedBack(driveKey,userID,technical,hr,extra,authority);

                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void insertFeedBack(String driveKey,String userID,String technical,String hr,String extra
                                 , boolean authority){
        StudentFeedModel studentFeedback =  new StudentFeedModel(userID,technical,hr,extra,driveKey,authority);
        mDatabaseReference.child(mContext.getString(R.string.db_student_feedBack))
                .push().setValue(studentFeedback);

    }

    public void uploadDrive(String driveName){

        UploadDriveModel driveModel = new UploadDriveModel(driveName);
        mDatabaseReference.child(mContext.getString(R.string.db_upload_drive))
                .push().setValue(driveModel);

    }


    /**
     * upload objective feed back portion start from here
     */

    public void firstQuestion(int yes,int no){

    }
}
