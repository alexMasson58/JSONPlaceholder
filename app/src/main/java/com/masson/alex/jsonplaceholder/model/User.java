package com.masson.alex.jsonplaceholder.model;

import java.io.Serializable;

/**
 * Created by alex on 24/03/2018.
 */

public class User implements Serializable{
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Compagny compagny;

    public User() {
    }

    public User(int id, String name, String username, String email, Address address, String phone, String website, Compagny compagny) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.compagny = compagny;
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
