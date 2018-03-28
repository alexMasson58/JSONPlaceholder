package com.masson.alex.jsonplaceholder.network.comment;

import com.masson.alex.jsonplaceholder.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by frup66058 on 27/03/2018.
 */

public interface CommentAPI {
    @GET("posts/{id}/comments")
    Call<List<Comment>> getAlbumsForUser(@Path("id") int userid);

    @POST("comments")
    /*Call<Comment> commentPost(@Field("postid") int postid, @Field("name") String name, @Field("email") String email, @Field("body") String body);*/
    Call<Comment> commentPost(@Body Comment comment);

}
