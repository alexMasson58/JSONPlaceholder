package com.masson.alex.jsonplaceholder.viewmodel;

import com.masson.alex.jsonplaceholder.model.Photo;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class PhotoViewModel {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public PhotoViewModel(Photo p) {
        this.albumId = p.getAlbumId();
        this.id = p.getId();
        this.title = p.getTitle();
        this.url = p.getUrl();
        this.thumbnailUrl = p.getThumbnailUrl();
    }


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
