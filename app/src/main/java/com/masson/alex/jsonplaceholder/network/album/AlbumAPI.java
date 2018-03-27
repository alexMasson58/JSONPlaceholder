package com.masson.alex.jsonplaceholder.network.album;

import com.masson.alex.jsonplaceholder.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by frup66058 on 27/03/2018.
 */

public interface AlbumAPI {
    @GET("users/{id}/albums")
    Call<List<Album>> getAlbumsForUser(@Path("id") int userid);
}
