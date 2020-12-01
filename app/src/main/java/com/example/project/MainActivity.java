package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.project.Utils.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting...");
        setupBottomNavView();
    }

    //Bottom Nav View Setup
    private void setupBottomNavView(){
        Log.d(TAG, "setupBottomNavView: Setting up Bottom Navigation View...");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById((R.id.home_bottom_toolbar));
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);

    }
}