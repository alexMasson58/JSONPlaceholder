package com.masson.alex.jsonplaceholder.repository.user;

import com.masson.alex.jsonplaceholder.model.User;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface IUserRepository {
    List<User> getUserList();
    User getUser(int id);
}
