package com.masson.alex.jsonplaceholder.ui.userlist;

import android.arch.lifecycle.LifecycleOwner;

import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.repository.user.IUserRepository;
import com.masson.alex.jsonplaceholder.viewmodel.UserListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 25/03/2018.
 */

public class UserListPresenter {

    private final IUserRepository userRepository;
    private View view;
    private List<User> userlist;
    LifecycleOwner lifecycleOwner;

    public UserListPresenter(LifecycleOwner lifecycleOwner, IUserRepository userRepository, View v) {
        this.userRepository = userRepository;
        this.view = v;
        this.lifecycleOwner = lifecycleOwner;
    }

    public List<UserListViewModel> getUserList() {
        userlist = userRepository.getUserList();
        ArrayList<UserListViewModel> res = new ArrayList<>();
        if (userlist != null) {
            for (User u : userlist
                    ) {
                res.add(new UserListViewModel(u));
            }
        }
        return res;
    }

    public void userClicked(int position) {
        UserViewModel uvm = new UserViewModel(userlist.get(position));
        view.displayUserProfile(uvm);
    }

    public interface View {

        void refreshUserList();

        void displayUserProfile(UserViewModel u);
    }
}
