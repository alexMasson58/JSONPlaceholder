package com.masson.alex.jsonplaceholder.network.user;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alex on 24/03/2018.
 */

public class UserService {
    private Retrofit retrofit = null;
    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public UserAPI getAPI() {
        String BASE_URL = "https://jsonplaceholder.typicode.com/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(UserAPI.class);
    }
}
