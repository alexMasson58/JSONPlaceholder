package com.masson.alex.jsonplaceholder.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.Post;

import java.io.Serializable;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class PostViewModel implements Serializable,Parcelable{

    private int id;
    private int userId;
    private String title;
    private String body;

    public PostViewModel(Post u) {
        this.id = u.getId();
        this.userId = u.getUserId();
        this.title = u.getTitle();
        this.body = u.getBody();
    }

    protected PostViewModel(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<PostViewModel> CREATOR = new Creator<PostViewModel>() {
        @Override
        public PostViewModel createFromParcel(Parcel in) {
            return new PostViewModel(in);
        }

        @Override
        public PostViewModel[] newArray(int size) {
            return new PostViewModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(userId);
        parcel.writeString(title);
        parcel.writeString(body);
    }
}
