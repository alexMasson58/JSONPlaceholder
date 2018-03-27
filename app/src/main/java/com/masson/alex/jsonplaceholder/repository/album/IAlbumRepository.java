package com.masson.alex.jsonplaceholder.repository.album;

import com.masson.alex.jsonplaceholder.model.Album;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface IAlbumRepository {
    void getAlbumsListForUser(int userid, IAlbumRepositoryListener listener);

    interface IAlbumRepositoryListener {
        /*void albumListUpdated(List<Album> albums);*/

        void albumsForUser(List<Album> albums);

        void onError(String message);
    }
}
