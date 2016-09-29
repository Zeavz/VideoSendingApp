package com.example.jenis.videosendingapp.Video_Stuff;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jenis.videosendingapp.R;

import java.util.List;

/**
 * Created by Jenis on 9/25/2016.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private List<Videos> videoList;
    private int selectedItem = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mFriendName;
        private ImageView mFriendPicture;

        public MyViewHolder (View view){
            super(view);
            mFriendName = (TextView)view.findViewById(R.id.textViewFriendName);
            mFriendPicture = (ImageView) view.findViewById(R.id.imageViewFriendPicture);
        }
    }

    public VideoAdapter(List<Videos> videoList){
        this.videoList = videoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.videolist,parent,false);
        return new MyViewHolder(itemView);
    }

    public void addMessage(Videos info){
        videoList.add(info);
        notifyItemInserted(videoList.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Videos info = videoList.get(position);
        holder.mFriendName.setText(info.getFriendName());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
