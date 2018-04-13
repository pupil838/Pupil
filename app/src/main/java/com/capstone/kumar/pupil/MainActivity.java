package com.capstone.kumar.pupil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.capstone.kumar.pupil.AdminOperations.AdminFeedBack;
import com.capstone.kumar.pupil.AdminUploadDrive.UploadDrive;
import com.capstone.kumar.pupil.Section.SectionCreateFragment;
import com.capstone.kumar.pupil.TrendQuest.AdminTrendingQuestion;
import com.capstone.kumar.pupil.studentFeedBack.GiveFeedBack;
import com.capstone.kumar.pupil.studentfragments.AmcatEnterFeildFragment;
import com.capstone.kumar.pupil.studentfragments.LandingFragment;
import com.capstone.kumar.pupil.studentfragments.Profile_Fragment;
import com.capstone.kumar.pupil.studentfragments.UpComingDrive;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements
                  NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";

    //firebase
    private FirebaseAuth mAuth;
    String mAdmin;


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggel;
    private Toolbar mToolbar;

    private FloatingActionMenu mFloatingActionMenu;
    private FloatingActionButton mEdit,mRecord,mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAuth = FirebaseAuth.getInstance();
        mAdmin = mAuth.getCurrentUser().getUid();

        mFloatingActionMenu = (FloatingActionMenu) findViewById(R.id.floatingActionMenu);
        mEdit  = (FloatingActionButton)  findViewById(R.id.floatingActionItem3);
        mPhoto  = (FloatingActionButton) findViewById(R.id.floatingActionItem1);
        mRecord =  (FloatingActionButton) findViewById(R.id.floatingActionItem2);

        /**
         * when user login then this fragment show
         */
        LandingFragment landingFragment = new LandingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame,landingFragment)
                .addToBackStack(null)
                .commit();


        /**
         * for admin when he login then he see diffrent options on floating button.
         */

        if(mAdmin.equals(getString(R.string.admin_ID))) {

            mEdit.setLabelText("FeedBack");
            mPhoto.setLabelText("Upload Drive");
            mRecord.setLabelText("Trending Question");

            mEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(MainActivity.this, "Admin Operation Clicked", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MainActivity.this, AdminFeedBack.class));
                    mFloatingActionMenu.close(true);

                }
            });

            mPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(MainActivity.this, "Upload Drive Clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, UploadDrive.class));
                    mFloatingActionMenu.close(true);

                }
            });

            mRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this,AdminTrendingQuestion.class));
                    mFloatingActionMenu.close(true);

                }
            });

        }else{

            /**
             * student floating  button options start
             */

            mEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,StudentFeedback.class));
                    mFloatingActionMenu.close(true);
                }
            });

            mPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Photo Clicked", Toast.LENGTH_SHORT).show();
                    mFloatingActionMenu.close(true);
                }
            });

            mRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Record Clicked", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,AdminTrendingQuestion.class));
                    mFloatingActionMenu.close(true);
                }
            });

        }

        mDrawerToggel = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggel);
        mDrawerToggel.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * sidebar nav when click navbar then all option appear
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_analyze){
            LandingFragment landingFragment = new LandingFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,landingFragment)
                    .addToBackStack(null)
                    .commit();
        }else if (id == R.id.nav_camera) {
            // Handle the camera action
            UpComingDrive pf = new UpComingDrive();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame,pf)
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_gallery) {


            if(mAdmin.equals("qd89XnePKIV2DlydvLucseFdMUO2")) {
                SectionCreateFragment pf = new SectionCreateFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, pf)
                        .addToBackStack(null)
                        .commit();
            }else {
                AmcatEnterFeildFragment pf = new AmcatEnterFeildFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, pf)
                        .addToBackStack(null)
                        .commit();
            }
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * when click back button then app finish all activity  and close
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityCompat.finishAffinity(MainActivity.this);

    }

}