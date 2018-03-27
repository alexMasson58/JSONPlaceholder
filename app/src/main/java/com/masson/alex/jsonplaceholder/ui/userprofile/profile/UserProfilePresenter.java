package com.masson.alex.jsonplaceholder.ui.userprofile.profile;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class UserProfilePresenter implements Parcelable {

    private final UserViewModel userViewModel;
    View view;


    public UserProfilePresenter(View view, UserViewModel userViewModel) {
        this.view = view;
        this.userViewModel = userViewModel;
        view.displayUserProfile(userViewModel);
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

    public interface View {
        void displayUserProfile(UserViewModel userViewModel);

        void displayErrorMessage(String message);

    }
}
