package com.masson.alex.jsonplaceholder.network.post;

import com.masson.alex.jsonplaceholder.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by frup66058 on 27/03/2018.
 */

public interface PostAPI {
    @GET("users/{id}/posts")
    Call<List<Post>> getPostsForUser(@Path("id") int userid);
}
