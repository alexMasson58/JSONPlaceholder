package com.masson.alex.jsonplaceholder.repository.post;

import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.model.Post;
import com.masson.alex.jsonplaceholder.network.post.PostService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class PostRepositoryImpl implements IPostRepository {

    private final PostService service;

    public PostRepositoryImpl() {
        this.service = new PostService();
    }

    @Override
    public void getPostsListForUser(int userid, final IPostRepositoryListener listener) {
        service.getAPI().getPostsForUser(userid).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> data = response.body();

                    if (data != null) {
                        if(listener!=null){
                            listener.postForUsers(data);
                        }
                    }
                }
                else{
                    if(listener!=null){
                        listener.onError("Erreur durant la récupération des posts");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                listener.onError("Erreur durant la récupération des posts");
            }
        });
    }
}
