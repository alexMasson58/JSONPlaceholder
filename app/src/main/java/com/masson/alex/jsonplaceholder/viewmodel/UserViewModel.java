package com.masson.alex.jsonplaceholder.viewmodel;

import com.masson.alex.jsonplaceholder.model.Address;
import com.masson.alex.jsonplaceholder.model.Compagny;
import com.masson.alex.jsonplaceholder.model.User;

/**
 * Created by alex on 25/03/2018.
 */

public class UserViewModel {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Compagny compagny;

    public UserViewModel(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.address = u.getAddress();
        this.phone = u.getPhone();
        this.website = u.getWebsite();
        this.compagny = u.getCompagny();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Compagny getCompagny() {
        return compagny;
    }

    public void setCompagny(Compagny compagny) {
        this.compagny = compagny;
    }
}
