package com.example.jenis.videosendingapp.Friend_Stuff;

/**
 * Created by Jenis on 9/26/2016.
 */

public class Friend {
    public String name;
    public String link;
    public int points;

    public Friend(){
    }
    public Friend(String name, String link, int points){
        this.name = name;
        this.link = link;
        this.points = points;
    }
    public String getName(){
        return name;
    }
    public String getLink(){
        return link;
    }
    public int getPoints(){
        return points;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLink(String link){
        this.link = link;
    }
    public void setPoints(int points){
        this.points = points;
    }
}
