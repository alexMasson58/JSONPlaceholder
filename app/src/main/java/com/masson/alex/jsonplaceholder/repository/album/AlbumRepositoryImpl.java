package com.masson.alex.jsonplaceholder.repository.album;

import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.network.album.AlbumService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class AlbumRepositoryImpl implements IAlbumRepository {

    private final AlbumService service;

    public AlbumRepositoryImpl() {
        service = new AlbumService();
    }

    @Override
    public void getAlbumsListForUser(int userid, final IAlbumRepositoryListener listener) {
        service.getAPI().getAlbumsForUser(userid).enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    List<Album> data = response.body();

                    if (data != null) {
                        if (listener != null) {
                            listener.albumsForUser(data);
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onError("Erreur durant la récupération des albums");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                listener.onError("Erreur durant la récupération des albums");
            }
        });
    }
}
