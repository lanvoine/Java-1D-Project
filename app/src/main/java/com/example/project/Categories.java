package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.project.Utils.BottomNavigationViewHelper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.project.Utils.BottomNavigationViewHelper.setupBottomNavigationView;

public class Categories extends AppCompatActivity {
    private static final String TAG = "Categories";
    private Context mContext = Categories.this;
    SearchView searchView;
    private ArrayList<String> tags = new ArrayList<String>();
    ImageButton fifthrow;
    ImageButton hackathon;
    ImageButton exhibition;
    ImageButton intern;
    ImageButton scholarships;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        fifthrow = findViewById(R.id.fifthrow);
        hackathon = findViewById(R.id.hackathon);
        exhibition = findViewById(R.id.exhibition);
        intern = findViewById(R.id.intern);
        scholarships = findViewById(R.id.scholarships);

        setupBottomNavigationView();

        searchView = findViewById(R.id.searchView);
        Query newQuery = database.getReference("Uploaded_Posts");
        System.out.println(newQuery);
        newQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getChildren());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    //System.out.println(dataSnapshot1);
                    tags.add((String) dataSnapshot1.child("hashtag").getValue());
                    System.out.println(tags);
                    Set<String> set = new LinkedHashSet<String>();
                    set.addAll(tags);
                    tags.clear();
                    tags.addAll(set);
                    System.out.println(tags);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {

                if(tags.contains(query)){
                    Intent intent = new Intent(Categories.this, SearchResults.class);
                    intent.putExtra("Search", query);
                    startActivity(intent);
                }else{
                    Toast.makeText(Categories.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        fifthrow.setOnClickListener((v) -> {
            if(tags.contains("#fifthrowrecruitment")){
                Intent intent = new Intent(Categories.this, SearchResults.class);
                intent.putExtra("Search", "#fifthrowrecruitment");
                startActivity(intent);
            }else{
                Toast.makeText(Categories.this, "No Posts Available",Toast.LENGTH_LONG).show();
            }
        });

        hackathon.setOnClickListener((v) -> {
            if(tags.contains("#hackathon")){
                Intent intent = new Intent(Categories.this, SearchResults.class);
                intent.putExtra("Search", "#hackathon");
                startActivity(intent);
            }else{
                Toast.makeText(Categories.this, "No Posts Available",Toast.LENGTH_LONG).show();
            }
        });

        exhibition.setOnClickListener((v) -> {
            if(tags.contains("#exhibition")){
                Intent intent = new Intent(Categories.this, SearchResults.class);
                intent.putExtra("Search", "#exhibition");
                startActivity(intent);
            }else{
                Toast.makeText(Categories.this, "No Posts Available",Toast.LENGTH_LONG).show();
            }
        });

        intern.setOnClickListener((v) -> {
            if(tags.contains("#career")){
                Intent intent = new Intent(Categories.this, SearchResults.class);
                intent.putExtra("Search", "#career");
                startActivity(intent);
            }else{
                Toast.makeText(Categories.this, "No Posts Available",Toast.LENGTH_LONG).show();
            }
        });

        scholarships.setOnClickListener((v) -> {
            if(tags.contains("#scholarships")){
                Intent intent = new Intent(Categories.this, SearchResults.class);
                intent.putExtra("Search", "#scholarships");
                startActivity(intent);
            }else{
                Toast.makeText(Categories.this, "No Posts Available",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
    }
}