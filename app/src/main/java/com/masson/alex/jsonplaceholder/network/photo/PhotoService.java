package com.masson.alex.jsonplaceholder.network.photo;

import com.masson.alex.jsonplaceholder.network.comment.CommentAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class PhotoService {
    private Retrofit retrofit = null;
    private PhotoAPI api;

    public PhotoAPI getAPI() {
        if(api == null) {
            String BASE_URL = "https://jsonplaceholder.typicode.com/";


            if (retrofit == null) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            api = retrofit.create(PhotoAPI.class);
        }
        return api;
    }
}
