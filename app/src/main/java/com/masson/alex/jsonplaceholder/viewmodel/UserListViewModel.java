package com.masson.alex.jsonplaceholder.viewmodel;

import com.masson.alex.jsonplaceholder.model.User;

/**
 * Created by alex on 25/03/2018.
 */

public class UserListViewModel{


    private String name;
    private String username;
    private String email;

    public UserListViewModel(User u) {
        this.name = u.getName();
        this.username = u.getUsername();
        this.email = u.getEmail();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
