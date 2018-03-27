package com.masson.alex.jsonplaceholder.network.album;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class AlbumService {
    private Retrofit retrofit = null;
    private AlbumAPI api;

    public AlbumAPI getAPI() {
        if(api == null) {
            String BASE_URL = "https://jsonplaceholder.typicode.com/";

            if (retrofit == null) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            api = retrofit.create(AlbumAPI.class);
        }
        return api;
    }
}
