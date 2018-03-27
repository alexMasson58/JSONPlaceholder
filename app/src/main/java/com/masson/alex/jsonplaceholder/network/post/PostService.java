package com.masson.alex.jsonplaceholder.network.post;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class PostService {
    private Retrofit retrofit = null;

    public PostAPI getAPI() {
        String BASE_URL = "https://jsonplaceholder.typicode.com/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(PostAPI.class);
    }
}
