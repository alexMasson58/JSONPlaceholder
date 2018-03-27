package com.masson.alex.jsonplaceholder.repository.comment;

import com.masson.alex.jsonplaceholder.model.Comment;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface ICommentRepository {

    interface ICommentRepositoryListener {
        void commentsForPost(List<Comment> comments);

        void onError(String message);
    }

    void getCommentListForPost(int postid, ICommentRepositoryListener listener);
}
