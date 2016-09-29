package com.example.jenis.videosendingapp.Friend_Stuff;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jenis.videosendingapp.R;

import java.util.List;

/**
 * Created by Jenis on 9/26/2016.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {
    private List<Friend> friendList;
    private int selectedItem = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView mFriendProfile;

        public MyViewHolder (View view){
            super(view);
            mFriendProfile = (ImageView) view.findViewById(R.id.imageViewFriendProfile);
        }
    }

    public FriendAdapter(List<Friend> friendList){
        this.friendList = friendList;
    }

    @Override
    public FriendAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist,parent,false);
        return new FriendAdapter.MyViewHolder(itemView);
    }

    public void addMessage(Friend info){
        friendList.add(info);
        notifyItemInserted(friendList.size());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Friend info = friendList.get(position);
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }
}