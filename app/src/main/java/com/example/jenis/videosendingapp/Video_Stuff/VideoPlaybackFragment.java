package com.example.jenis.videosendingapp.Video_Stuff;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.jenis.videosendingapp.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by dan on 2016-08-23.
 */
public class VideoPlaybackFragment extends Fragment {

    private VideoView mVideoView;
    private String vidFilePath;
    private StorageReference storageReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_playback, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVideoView = (VideoView) getView().findViewById(R.id.fVideoView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        storageReference = FirebaseStorage.getInstance().getReference("Games");
        vidFilePath = getActivity().getIntent().getExtras().getString("file");
        playVideo(vidFilePath);
    }

    private void playVideo(String file){
        mVideoView.setMediaController(null);
        mVideoView.setVideoPath(file);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        mVideoView.start();
    }
}