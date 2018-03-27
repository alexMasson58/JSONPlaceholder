package com.masson.alex.jsonplaceholder.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.User;

import java.io.Serializable;

/**
 * Created by alex on 25/03/2018.
 */

public class UserLightViewModel implements Serializable, Parcelable{


    private String name;
    private String username;
    private String email;

    public UserLightViewModel(User u) {
        this.name = u.getName();
        this.username = u.getUsername();
        this.email = u.getEmail();
    }

    protected UserLightViewModel(Parcel in) {
        name = in.readString();
        username = in.readString();
        email = in.readString();
    }

    public static final Creator<UserLightViewModel> CREATOR = new Creator<UserLightViewModel>() {
        @Override
        public UserLightViewModel createFromParcel(Parcel in) {
            return new UserLightViewModel(in);
        }

        @Override
        public UserLightViewModel[] newArray(int size) {
            return new UserLightViewModel[size];
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
