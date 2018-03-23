package com.capstone.kumar.pupil.signUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.kumar.pupil.R;
import com.capstone.kumar.pupil.utils.FirebaseMethods;
import com.capstone.kumar.pupil.utils.User_SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private FirebaseMethods mFirebaseMethods;
    private ProgressDialog mProgressDialog;


    //var
    private String append = "";
    private Context mContext;

    private EditText mUserName,mEmail,mPass,mRe_Pass,mEnterSecurity,mCGPA;
    private String name,email,pass,retypePass;
    private Double cgpa;
    private TextView mRandomNumGenerate;
    private Button mSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }else{
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        initWidgets();
        startSignUp();
        setupFirebaseMethod();
    }

    private void initWidgets(){
        mUserName = (EditText) findViewById(R.id.am_username);
        mEmail = (EditText)  findViewById(R.id.am_email);
        mPass  = (EditText) findViewById(R.id.am_password);
        mRe_Pass = (EditText) findViewById(R.id.am_retype_password);
        mCGPA = (EditText) findViewById(R.id.am_cgpa);
        mRandomNumGenerate = (TextView) findViewById(R.id.security_random);
        mEnterSecurity = (EditText) findViewById(R.id.enter_security_random);
        mSignUp  = (Button) findViewById(R.id.signup);

        mContext = SignUpActivity.this;
        mProgressDialog = new ProgressDialog(mContext);

        mFirebaseMethods = new FirebaseMethods(mContext);

    }

//
//    private void getInitValues(){
//        name = mUserName.getText().toString();
//        email = mEmail.getText().toString();
//        pass = mPass.getText().toString();
//        retypePass = mRe_Pass.getText().toString();
//
//    }

    private void startSignUp() {
        final int random = new Random().nextInt(900) + 100; //[0, 899] + 100  = [100, 999]

        mRandomNumGenerate.setText(Integer.toString(random));

        mEnterSecurity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEnterSecurity.setHint("");
                } else {
                    mEnterSecurity.setHint("UserName");
                }
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mUserName.getText().toString();
                email = mEmail.getText().toString();
                pass = mPass.getText().toString();
                retypePass = mRe_Pass.getText().toString();
                cgpa = Double.parseDouble(mCGPA.getText().toString());



                String mSecurityRandom = mEnterSecurity.getText().toString();
                String value = Integer.toString(random);

                if(mSecurityRandom.equals(value)){
                    //Toast.makeText(getApplicationContext(), "Value Matched", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong number entered", Toast.LENGTH_SHORT).show();
                }

                if(pass.equals(retypePass)) {

                    if (checkInput(email, pass, name)) {
                        mFirebaseMethods.registerNewEmail(email, pass);

                        mProgressDialog.setMessage("Sending Email verification Code");
                        mProgressDialog.show();

                    }
                }else{
                    Toast.makeText(mContext, "Password doesnt match check and try again!...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private boolean checkInput(String email,String password,String username){
        if(email.equals("")||username.equals("")||password.equals("")){

            Toast.makeText(mContext, "All field must be feild out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /***
     * SetUp Firebase Methods
     */

    private void checkUserNameExists(final String username){
        Log.d(TAG,"checkIfUsernameExists : checking if " + username + "already exists");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query mQuery = reference.child(getString(R.string.db_User))
                .child("user_Name")
                .equalTo(username);

        mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren() ){
                    if(singleSnapshot.exists()) {
                        Log.d(TAG, "checkIfUsernameExists: FOUND & MATCH: " + singleSnapshot.getValue(User_SignUp.class).getUser_Name());

                        append = mRef.push().getKey().substring(3, 7);

                        Log.d(TAG, "checkIfUsernameExists: username already exists append to random string to name: " + append);
                    }
                }

                String mUserName = "";
                mUserName = username + append;
                mFirebaseMethods.addNewUser(email,mUserName,retypePass,cgpa);

                mAuth.signOut();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setupFirebaseMethod(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        mAuthListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    Log.d(TAG,"onAuthStateChanged: Signed_IN" + user.getUid());

                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            checkUserNameExists(name);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    finish();
                }else{
                    Log.d(TAG,"onAuthStateChangedListener : Signed_Out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuth != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
