package com.example.jenis.videosendingapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jenis.videosendingapp.Recieved_Stuff.VideoAdapter;
import com.example.jenis.videosendingapp.Recieved_Stuff.Videos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mUserName;
    private ImageView mProfilePicture;
    private Button mStart;
    private RecyclerView mVideoList;
    private VideoAdapter mVideoAdapter;
    private String user;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    private FirebaseUser user12;

    private ChildEventListener childEventListener;
    private List<Videos> videoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoList = (RecyclerView)findViewById(R.id.my_recycler_view);
        mUserName = (TextView)findViewById(R.id.textViewUserName);
        mProfilePicture = (ImageView)findViewById(R.id.imageView3);
        mStart = (Button)findViewById(R.id.buttonStart);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        //user = auth.getCurrentUser().getDisplayName().toString();
        user = "Zeavz";

        // Remove
        //updateUserInfo();
        //Remove

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mVideoList.setLayoutManager(linearLayoutManager);
        mVideoList.setHasFixedSize(false);

        mVideoAdapter = new VideoAdapter(videoList);
        mVideoList.setAdapter(mVideoAdapter);
        mVideoAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                mVideoList.smoothScrollToPosition(mVideoAdapter.getItemCount());
            }
        });

        reference = database.getReference("Users/" + user + "/recieved");
        childEventListener = reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Videos info = dataSnapshot.getValue(Videos.class);
                mVideoAdapter.addMessage(info);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void updateUserInfo(){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName("Zeavz")
                .setPhotoUri(Uri.parse("gs://tabbedpractice.appspot.com/Profile Pictures/"))
                .build();
        user12.updateProfile(profileChangeRequest);

    }
    @Override
    protected void onStart() {
        super.onStart();
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
