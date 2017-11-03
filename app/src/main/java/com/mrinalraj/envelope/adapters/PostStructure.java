package com.mrinalraj.envelope.adapters;

/**
 * Created by mrinal on 11/2/2017.
 */

public class PostStructure {
    public String time,date,text,likes,dislikes,comments,postID;

    public PostStructure(){}

    public PostStructure(String time, String date, String text, String likes, String dislikes, String comments, String postID){
        this.time = time;
        this.date = date;
        this.text = text;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
        this.postID = postID;
    }

    public String[] getPost(){
        String post[] = {time,date,text,likes,dislikes,comments,postID};
        return post;
    }

    public String getID(){
        return postID;
    }
}
