package com.masson.alex.jsonplaceholder.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.User;

/**
 * Created by alex on 25/03/2018.
 */

public class UserListViewModel implements Parcelable{


    private String name;
    private String username;
    private String email;

    public UserListViewModel(User u) {
        this.name = u.getName();
        this.username = u.getUsername();
        this.email = u.getEmail();
    }

    protected UserListViewModel(Parcel in) {
        name = in.readString();
        username = in.readString();
        email = in.readString();
    }

    public static final Creator<UserListViewModel> CREATOR = new Creator<UserListViewModel>() {
        @Override
        public UserListViewModel createFromParcel(Parcel in) {
            return new UserListViewModel(in);
        }

        @Override
        public UserListViewModel[] newArray(int size) {
            return new UserListViewModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(email);
    }
}
