package com.example.jenis.videosendingapp.Video_Stuff;

/**
 * Created by Jenis on 9/25/2016.
 */

public class Videos {
    public String name;
    public String message;
    public String friendName;

    public Videos(){
    }
    public Videos(String message, String name){
        this.message = message;
        this.friendName = name;
    }
    public String getFriendName(){
        return friendName;
    }
    public String getMessage(){
        return message;
    }
    public String getName(){return name;}
    public void setFriendName(String name){
        this.friendName = name;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setName(String name){this.name = name;}
}
