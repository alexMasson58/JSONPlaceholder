package com.masson.alex.jsonplaceholder.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.Album;

import java.io.Serializable;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class AlbumViewModel implements Serializable, Parcelable {

    private int userId;
    private int id;
    private String title;

    public AlbumViewModel(Album u) {

        this.id = u.getId();
        this.title = u.getTitle();
        this.userId = u.getUserId();
    }

    protected AlbumViewModel(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
    }

    public static final Creator<AlbumViewModel> CREATOR = new Creator<AlbumViewModel>() {
        @Override
        public AlbumViewModel createFromParcel(Parcel in) {
            return new AlbumViewModel(in);
        }

        @Override
        public AlbumViewModel[] newArray(int size) {
            return new AlbumViewModel[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeInt(id);
        parcel.writeString(title);
    }
}
