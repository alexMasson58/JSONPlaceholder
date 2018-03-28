package com.masson.alex.jsonplaceholder.network.photo;

import com.masson.alex.jsonplaceholder.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by frup66058 on 28/03/2018.
 */

public interface PhotoAPI {
    @GET("albums/{id}/photos")
    Call<List<Photo>> getAlbumsForUser(@Path("id") int userid);

}
