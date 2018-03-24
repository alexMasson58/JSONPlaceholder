package com.masson.alex.jsonplaceholder.repository.post;

import com.masson.alex.jsonplaceholder.model.Post;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface IPostRepository {
    List<Post> getPostsListForUser(int userid);

}
