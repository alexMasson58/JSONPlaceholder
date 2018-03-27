package com.masson.alex.jsonplaceholder.repository.comment;

import com.masson.alex.jsonplaceholder.model.Comment;
import com.masson.alex.jsonplaceholder.network.comment.CommentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class CommentRepositoryImpl implements ICommentRepository {


    private final CommentService service;

    public CommentRepositoryImpl() {
        service = new CommentService();
    }
    @Override
    public void getCommentListForPost(int postid, final ICommentRepositoryListener listener) {
        service.getAPI().getAlbumsForUser(postid).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    List<Comment> data = response.body();

                    if (data != null) {
                        if (listener != null) {
                            listener.commentsForPost(data);
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onError("Erreur durant la récupération des albums");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                listener.onError("Erreur durant la récupération des albums");
            }
        });
    }
}
