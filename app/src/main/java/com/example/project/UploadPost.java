package com.example.project;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.project.Utils.BitmapEncoder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadPost  {
    private static final String TAG = "In UploadPost";
    private FirebaseAuth mAuth;

    UploadPost(){
    }

    public void uploadPost(String title, String hashtag, String description, Bitmap photo, String link) {
        Log.i(TAG, "Inside UploadPost: Starting...");
        Encode post = new Encode(title, description, photo, link);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        String userId = user.getUid();
        DatabaseReference mRef = database.getReference().child(hashtag).child(userId);
        mRef.setValue(post);
        Log.i(TAG, "Inside UploadPost: Successful...");
    }

}
