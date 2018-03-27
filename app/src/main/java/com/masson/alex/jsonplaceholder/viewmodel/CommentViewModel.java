package com.masson.alex.jsonplaceholder.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.Comment;

import java.io.Serializable;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class CommentViewModel implements Serializable {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public CommentViewModel(Comment c) {
        this.postId = c.getPostId();
        this.id = c.getId();
        this.name = c.getName();
        this.email = c.getEmail();
        this.body = c.getBody();
    }

    protected CommentViewModel(Parcel in) {
        postId = in.readInt();
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        body = in.readString();
    }



    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
