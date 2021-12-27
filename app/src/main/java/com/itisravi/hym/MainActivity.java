package com.itisravi.hym;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    int code = 1;
    String table;

    private boolean exit = false;

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
    }

    private void bottomNav() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Goal()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navGoal:
                    selectedFragment = new Goal();
                    break;
                case R.id.navProfile:
                    selectedFragment = new Profile();
                    break;
                case R.id.navWorkout:
                    selectedFragment = new Workout();
                    break;
                case R.id.navTrack:
                    selectedFragment = new Track();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();

            return true;
        });

    }

    private void bottomNavClick() {
        bottomNavigationView.setOnItemSelectedListener(item -> {


            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navGoal:
                    selectedFragment = new Goal();
                    break;
                case R.id.navProfile:
                    selectedFragment = new Profile();
                    break;
                case R.id.navWorkout:
                    selectedFragment = new Workout();
                    break;
                case R.id.navTrack:
                    selectedFragment = new Track();
                    break;
            }

//            replaceFragment(selectedFragment);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, selectedFragment).commit();

            return true;
        });
    }

    private void fragmentNavigation() {
        switch (code) {
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.navTrack);

                Bundle bundle = new Bundle();
                bundle.putString("tableName", table);

                Track track = new Track();
                track.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, track).commit();
                break;
            case 3:
                bottomNavigationView.setSelectedItemId(R.id.navWorkout);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Workout()).commit();
                break;
            case 4:
                bottomNavigationView.setSelectedItemId(R.id.navProfile);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Profile()).commit();
                break;
            default:
                bottomNavigationView.setSelectedItemId(R.id.navGoal);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Goal()).commit();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        table = intent.getStringExtra("tableName");
        code = intent.getIntExtra("code", 0);


        initViews();

        bottomNavClick();
        fragmentNavigation();

    }

    @Override
    public void onBackPressed() {

        if (exit) {
            super.onBackPressed();
        }

        exit = true;
        Toast.makeText(this, "Tap/Slide again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 2000);
    }
}