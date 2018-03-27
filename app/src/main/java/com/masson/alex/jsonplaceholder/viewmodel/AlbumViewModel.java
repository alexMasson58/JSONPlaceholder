package com.masson.alex.jsonplaceholder.viewmodel;

import com.masson.alex.jsonplaceholder.model.Album;

import java.io.Serializable;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class AlbumViewModel implements Serializable {

    private int userId;
    private int id;
    private String title;

    public AlbumViewModel(Album u) {

        this.id = u.getId();
        this.title = u.getTitle();
        this.userId = u.getUserId();
    }




    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
