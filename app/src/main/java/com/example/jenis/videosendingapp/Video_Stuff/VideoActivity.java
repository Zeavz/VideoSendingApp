package com.example.jenis.videosendingapp.Video_Stuff;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jenis.videosendingapp.MainActivity;
import com.example.jenis.videosendingapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by dan on 2016-08-19.
 */
public class VideoActivity extends AppCompatActivity{

    private String filePath;

    private Button redoButton;
    private Button sendButton;
    private StorageReference storageReference;
    private DatabaseReference reference;

    private Button saveVideoButton;
    private Boolean saveVideo = false;

    private MediaScannerConnection mediaScanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        redoButton = (Button) findViewById(R.id.redoButton);
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File vidFile = new File(filePath);
                vidFile.delete();
                finish();
            }
        });


        Intent i = getIntent();
        filePath = i.getExtras().getString("file");

        mediaScanner = new MediaScannerConnection(getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {
                mediaScanner.scanFile(filePath, "video/mp4");
            }

            @Override
            public void onScanCompleted(String s, Uri uri) {
                mediaScanner.disconnect();
            }
        });

        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri filenNa = Uri.fromFile(new File(filePath));
                String id = updateTheDataBase();
                storageReference = FirebaseStorage.getInstance().getReference("Games/" + id);
                UploadTask uploadTask = storageReference.putFile(filenNa);
                if(!saveVideo) {
                    File vidFile = new File(filePath);
                    vidFile.delete();
                }
                else{
                    mediaScanner.connect();
                }

                Toast.makeText(getApplicationContext(), "Video Sent", Toast.LENGTH_SHORT).show();
                goBackToMainActivity();
            }
        });

        saveVideoButton = (Button) findViewById(R.id.saveVideoButton);
        saveVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveVideo = true;
                saveVideoButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Video will be saved", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void goBackToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public String updateTheDataBase(){
        String sender = getIntent().getExtras().getString("Sender");
        String reciever = getIntent().getExtras().getString("Reciever");
        String word = getIntent().getExtras().getString("Word");

        reference = FirebaseDatabase.getInstance().getReference("Users/"+sender+"/Games/sent");
        String id = reference.push().getKey();
        reference.child(id).setValue(reciever);

        reference = FirebaseDatabase.getInstance().getReference("Users/"+reciever+"/Games/recieved");
        reference.child(id).setValue(sender);

//        Game newGame = new Game("none","nothing","nothing",reciever,0,sender,word);
//        reference = FirebaseDatabase.getInstance().getReference("Games");
//        reference.child(id).push().setValue(newGame);

        return id;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus){
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}
