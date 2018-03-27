package com.masson.alex.jsonplaceholder.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.model.Address;
import com.masson.alex.jsonplaceholder.model.Company;
import com.masson.alex.jsonplaceholder.model.User;

import java.io.Serializable;

/**
 * Created by alex on 25/03/2018.
 */

public class UserViewModel implements Serializable, Parcelable {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public UserViewModel(User u) {
        this.id = u.getId();
        this.name = u.getName();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.address = u.getAddress();
        this.phone = u.getPhone();
        this.website = u.getWebsite();
        this.company = u.getCompany();
    }

    protected UserViewModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
        phone = in.readString();
        website = in.readString();
        company = in.readParcelable(Company.class.getClassLoader());
    }

    public static final Creator<UserViewModel> CREATOR = new Creator<UserViewModel>() {
        @Override
        public UserViewModel createFromParcel(Parcel in) {
            return new UserViewModel(in);
        }

        @Override
        public UserViewModel[] newArray(int size) {
            return new UserViewModel[size];
        }
    };

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeParcelable(address, i);
        parcel.writeString(phone);
        parcel.writeString(website);
        parcel.writeParcelable(company, i);
    }
}
