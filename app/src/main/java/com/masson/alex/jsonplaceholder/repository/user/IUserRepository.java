package com.masson.alex.jsonplaceholder.repository.user;

import com.masson.alex.jsonplaceholder.model.User;

import java.util.List;
import java.util.Observable;

import javax.security.auth.callback.Callback;

/**
 * Created by alex on 24/03/2018.
 */

public interface IUserRepository {
    void getUserList(IUserRepositoryListener listener);
    void getUser(int id, IUserRepositoryListener listener);

     interface IUserRepositoryListener{
        void userListUpdated(List<User> users);
        void userFound(User u);
        void onError(String message);
    }
}
