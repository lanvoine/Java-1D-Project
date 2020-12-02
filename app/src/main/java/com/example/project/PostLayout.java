package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.net.URL;

public class PostLayout extends AppCompatActivity {
    TextView title = findViewById(R.id.title);
    TextView description = findViewById(R.id.description);
    ImageView postimage = findViewById(R.id.postimage);
    Button signuplink = findViewById(R.id.signuplink);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_layout);
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        DataSnapshot data = info.getParcelable("Data");
        title.setText(data.child("Name").getValue().toString());
        description.setText(data.child("Content").getValue().toString());
        postimage.setImageBitmap();
        signuplink.setOnClickListener((v) -> {
           Uri link = Uri.parse(data.child("Link").getValue().toString());
           Intent intent2 = new Intent(Intent.ACTION_VIEW, link);
           startActivity(intent2);
        });

    }
}