package com.masson.alex.jsonplaceholder.network.user;

import com.masson.alex.jsonplaceholder.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alex on 24/03/2018.
 */

public interface UserAPI {

    @GET("users")
    Call<List<User>> getUserList();

    @GET("users/{i}")
    Call<User> getUserList(@Path("id") int userid);


}
