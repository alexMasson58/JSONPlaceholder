package com.masson.alex.jsonplaceholder.repository.photo;

import com.masson.alex.jsonplaceholder.model.Photo;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface IPhotoRepository {
    interface IPhotoRepositoryListener {
        void photosForAlbum(List<Photo> photos);

        void onError(String message);
    }

    void getPhotoListForAlbum(int albumid, IPhotoRepositoryListener listener);
}
