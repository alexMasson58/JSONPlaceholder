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

public class CommentRepositoryRetrofit implements ICommentRepository {


    private final CommentService service;

    public CommentRepositoryRetrofit() {
        service = new CommentService();
    }

    @Override
    public void postComment(int postid, int id, String name, String email, String comment, final ICommentRepositoryListener listener) {
        service.getAPI().commentPost(new Comment(postid, 0, name, email, comment)).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    Comment data = response.body();
                    if (data != null) {
                        if (listener != null) {
                            listener.commentSuccess(data);
                        }
                    }
                }
                else{
                    if (listener != null) {
                        listener.onError("Erreur durant l'envoi du commentaire");
                    }
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                if (listener != null) {
                    listener.onError("Erreur durant l'envoi du commentaire");
                }
            }
        });
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
