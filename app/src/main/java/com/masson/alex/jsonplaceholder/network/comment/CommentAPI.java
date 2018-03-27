package com.masson.alex.jsonplaceholder.network.comment;

import com.masson.alex.jsonplaceholder.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by frup66058 on 27/03/2018.
 */

public interface CommentAPI {
    @GET("posts/{id}/comments")
    Call<List<Comment>> getAlbumsForUser(@Path("id") int userid);
}
