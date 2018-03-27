package com.masson.alex.jsonplaceholder.network.comment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class CommentService {
    private Retrofit retrofit = null;
    private CommentAPI api;

    public CommentAPI getAPI() {
        if(api == null) {
            String BASE_URL = "https://jsonplaceholder.typicode.com/";


            if (retrofit == null) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            api = retrofit.create(CommentAPI.class);
        }
        return api;
    }
}
