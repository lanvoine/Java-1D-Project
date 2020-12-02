package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.project.Utils.BottomNavViewHelper;
import com.example.project.Utils.BottomNavigationViewHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button buttonCategories;

    private Context mContext = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting...");
        //setupBottomNavView();
        setupBottomNavigationView();

        FloatingActionButton fab = findViewById(R.id.create_post_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(settingsIntent);
            }

        });

    }



    //example menu remove if needed
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }


    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
    }

    //Pseudocode
    //ArrayList<String> tags = new ArrayList<String>();
    //private void showPosts() {
    //  FirebaseDatabase database = FirebaseDatabase.getInstance();
    //  DatabaseReference myRef = database.getReference("Subscriptions");
    //  Query newQuery = database.getReference("Uploaded_Posts");
    //        newQuery.addValueEventListener(new ValueEventListener() {
    //            @Override
    //            public void onDataChange(DataSnapshot dataSnapshot) {
    //                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
    //                    if (dataSnapshot1.child("email").getValue().toString().equals(get_email) {
    //                        tags.add((String) dataSnapshot1.child("hashtag").getValue());
        //                    Set<String> set = new LinkedHashSet<String>();
        //                    set.addAll(tags);
        //                    tags.clear();
        //                    tags.addAll(set);
    //                    }
    //
    //                }
    //                for (String tag : tags) {
    //                    Print out posts if tags match
    //            }


}