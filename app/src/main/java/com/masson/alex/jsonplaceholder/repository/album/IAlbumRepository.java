package com.masson.alex.jsonplaceholder.repository.album;

import com.masson.alex.jsonplaceholder.model.Album;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface IAlbumRepository {
    List<Album> getAlbumsListForUser(int userid);
}
