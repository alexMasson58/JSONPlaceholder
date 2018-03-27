package com.masson.alex.jsonplaceholder.ui.userlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.repository.user.IUserRepository;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 25/03/2018.
 */

public class UserListPresenter implements IUserRepository.IUserRepositoryListener , Parcelable{

    private  IUserRepository repository;
    private View view;
    private List<User> userlist;

    public UserListPresenter(View v, IUserRepository repository) {
        this.repository = repository;
        this.view = v;
        userlist = new ArrayList<>();
    }

    protected UserListPresenter(Parcel in) {
        userlist = in.readArrayList(User.class.getClassLoader());
        if(userlist == null){
            userlist = new ArrayList<>();
        }

    }

    public void bind(View view, IUserRepository repository) {
        this.view = view;
        this.repository = repository;
        if(userlist!=null){
            userListUpdated(this.userlist);
        }
    }

    public static final Creator<UserListPresenter> CREATOR = new Creator<UserListPresenter>() {
        @Override
        public UserListPresenter createFromParcel(Parcel in) {
            return new UserListPresenter(in);
        }

        @Override
        public UserListPresenter[] newArray(int size) {
            return new UserListPresenter[size];
        }
    };

    public void getUserList() {
        repository.getUserList(this);
    }



    public void userClicked(int position) {
        userFound(userlist.get(position));
    }

    @Override
    public void userListUpdated(List<User> users) {
        userlist = users;
        ArrayList<UserViewModel> res = new ArrayList<>();
        if (userlist != null) {
            for (User u : userlist
                    ) {
                res.add(new UserViewModel(u));
            }
        }
        view.userListUpdated(res);
    }

    @Override
    public void userFound(User u) {
        UserViewModel uvm = new UserViewModel(u);
        view.displayUserProfile(uvm);
    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(userlist);
    }

    public interface View {

        void userListUpdated(List<UserViewModel> users);

        void displayUserProfile(UserViewModel u);

        void displayErrorMessage(String message);
    }
}
