package com.masson.alex.jsonplaceholder.repository.photo;

import com.masson.alex.jsonplaceholder.model.Photo;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface IPhotoRepository {
    List<Photo> getPhotoListForAlbum(int albumid);
}
