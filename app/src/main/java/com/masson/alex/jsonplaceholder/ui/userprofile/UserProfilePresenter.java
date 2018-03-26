package com.masson.alex.jsonplaceholder.ui.userprofile;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.model.Post;
import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.repository.album.IAlbumRepository;
import com.masson.alex.jsonplaceholder.repository.post.IPostRepository;
import com.masson.alex.jsonplaceholder.viewmodel.AlbumListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.PostListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class UserProfilePresenter implements Parcelable {

    private final UserViewModel userViewModel;
    View view;


    public UserProfilePresenter(View view, UserViewModel userViewModel) {
        this.view = view;
        this.userViewModel = userViewModel;
    }


    protected UserProfilePresenter(Parcel in) {
        userViewModel = in.readParcelable(UserViewModel.class.getClassLoader());
    }

    public static final Creator<UserProfilePresenter> CREATOR = new Creator<UserProfilePresenter>() {
        @Override
        public UserProfilePresenter createFromParcel(Parcel in) {
            return new UserProfilePresenter(in);
        }

        @Override
        public UserProfilePresenter[] newArray(int size) {
            return new UserProfilePresenter[size];
        }
    };

    public void bind(View view) {
        this.view = view;
        view.displayUserProfile(userViewModel);
    }


    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(userViewModel, i);
    }

    interface View {
        void displayUserProfile(UserViewModel userViewModel);

        void displayErrorMessage(String message);

    }
}
