package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class SearchResults extends AppCompatActivity {
    ImageButton newbtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent intent = getIntent();
        String search = intent.getStringExtra("Search");
        System.out.println(search);

        Query newQuery = database.getReference("Posts");
        newQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    System.out.println(dataSnapshot1.child("Tag").getValue().toString());
                    System.out.println(search);
                    if (dataSnapshot1.child("Tag").getValue().toString().equals(search)){
                        System.out.println(dataSnapshot1.child("Tag").getValue());
                        addButton(dataSnapshot1);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addButton(DataSnapshot datasnapshot) {
        System.out.println("yay");
        LinearLayout layout = (LinearLayout) findViewById(R.id.base);
        newbtn = new ImageButton(this);
        newbtn.setImageResource();
        layout.addView(newbtn);
    }
}