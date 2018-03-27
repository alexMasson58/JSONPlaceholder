package com.masson.alex.jsonplaceholder.network.user;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alex on 24/03/2018.
 */

public class UserService {
    private Retrofit retrofit = null;
    private UserAPI api;

    public UserAPI getAPI() {
        if (api == null) {
            String BASE_URL = "https://jsonplaceholder.typicode.com/";

            if (retrofit == null) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            api = retrofit.create(UserAPI.class);
        }
        return api;
    }
}
