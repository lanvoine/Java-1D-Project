package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.project.Utils.BottomNavigationViewHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.LinkedHashMap;

import static com.example.project.R.layout.activity_profile;
import static com.example.project.Utils.BottomNavigationViewHelper.setupBottomNavigationView;

public class Profile extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext = Profile.this;
    Button MyPost;
    Button SavedPost;
    private RecyclerView PostList;
    private DatabaseReference Postsref;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile);

        setupBottomNavigationView();

        MyPost = findViewById(R.id.myposts);
        SavedPost = findViewById(R.id.savedposts);
        Postsref = FirebaseDatabase.getInstance().getReference().child("Posts");

        PostList = (RecyclerView) findViewById(R.id.mypostslist);
        PostList.setHasFixedSize(true);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        PostList.setLayoutManager(linearLayoutManager);
        PostList.setHasFixedSize(true);
        DisplayAllMyPosts();
    }



    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
    }


    private void DisplayAllMyPosts() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Uploaded_Posts");

        FirebaseRecyclerOptions<Posts> options =
                new FirebaseRecyclerOptions.Builder<Posts>()
                .setQuery(query, new SnapshotParser<Posts>() {
                    @NonNull
                    @Override
                    public Posts parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return new Posts(snapshot.child("id").getValue().toString(),
                                snapshot.child("description").getValue().toString(),
                                snapshot.child("encoded_photo").getValue().toString(),
                                snapshot.child("hashtag").getValue().toString(),
                                snapshot.child("link").getValue().toString(),
                                snapshot.child("title").getValue().toString());

                    }
                })
                .build();

        FirebaseRecyclerAdapter<Posts, PostsViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Posts, PostsViewHolder>(options) {
                    @NonNull
                    @Override
                    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.activity_profile, parent, false);
                        return new PostsViewHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull PostsViewHolder holder, int position, @NonNull Posts model) {

                    }
                };
        PostList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            //mView = itemView();
        }
    }

}
