package com.masson.alex.jsonplaceholder.repository.user;

import com.masson.alex.jsonplaceholder.model.User;

import java.util.List;
import java.util.Observable;

import javax.security.auth.callback.Callback;

/**
 * Created by alex on 24/03/2018.
 */

public interface IUserRepository {
    List<User> getUserList();
    User getUser(int id);
}
