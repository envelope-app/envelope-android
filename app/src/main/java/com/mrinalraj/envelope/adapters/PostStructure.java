package com.mrinalraj.envelope.adapters;

/**
 * Created by mrinal on 11/2/2017.
 */

public class PostStructure {
    public String time,date,text,likes,comments,postID;
    public String[] commentsArray;
    public int likeimg;

    public PostStructure(){}

    public PostStructure(String time, String date, String text, String likes, String comments, String postID, int likeimg,String[] commentsArray){
        this.time = time;
        this.date = date;
        this.text = text;
        this.likes = likes;
        this.comments = comments;
        this.postID = postID;
        this.likeimg = likeimg;
        this.commentsArray = commentsArray;
    }

    public String[] getPost(){
        String post[] = {time,date,text,likes,comments,postID};
        return post;
    }

    public int getLikeimg(){
        return likeimg;
    }

    public void setLikeimg(int likeimg){
        this.likeimg = likeimg;
    }

    public String[] getCommentsArray(){
        return this.commentsArray;
    }

    public void decLikes(){
        this.likes = String.valueOf((Integer.valueOf(this.likes)-1));
    }

    public void incLikes(){
        this.likes = String.valueOf((Integer.valueOf(this.likes)+1));
    }

    public String getID(){
        return postID;
    }
}
