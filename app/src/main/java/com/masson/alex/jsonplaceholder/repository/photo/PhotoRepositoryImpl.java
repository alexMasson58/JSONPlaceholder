package com.masson.alex.jsonplaceholder.repository.photo;

import com.masson.alex.jsonplaceholder.model.Photo;
import com.masson.alex.jsonplaceholder.network.photo.PhotoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class PhotoRepositoryImpl implements IPhotoRepository {

    private final PhotoService service;

    public PhotoRepositoryImpl() {
        service = new PhotoService();
    }


    @Override
    public void getPhotoListForAlbum(int albumid, final IPhotoRepositoryListener listener) {
        service.getAPI().getAlbumsForUser(albumid).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                if (response.isSuccessful()) {
                    List<Photo> data = response.body();
                    if (data != null) {
                        if (listener != null) {
                            listener.photosForAlbum(data);
                        }
                    } else {
                        if (listener != null) {
                            listener.onError("Erreur durant la récupération des albums");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                if (listener != null) {
                    listener.onError("Erreur durant la récupération des albums");
                }
            }
        });
    }
}
