package com.masson.alex.jsonplaceholder.ui.userprofile.profile;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class UserProfilePresenter {

    private UserViewModel userViewModel;
    View view;


    public UserProfilePresenter(View view, UserViewModel userViewModel) {
        this.view = view;
        this.userViewModel = userViewModel;
        view.displayUserProfile(userViewModel);
    }





    /*public void bind(View view, UserViewModel userViewModel) {
        this.view = view;
        this.userViewModel = userViewModel;
        view.displayUserProfile(userViewModel);
    }
*/

    public void onError(String message) {
        view.displayErrorMessage(message);
    }


    public interface View {
        void displayUserProfile(UserViewModel userViewModel);

        void displayErrorMessage(String message);

    }
}
