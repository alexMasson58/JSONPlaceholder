package com.masson.alex.jsonplaceholder.repository.comment;

import com.masson.alex.jsonplaceholder.model.Comment;

import java.util.List;

/**
 * Created by alex on 24/03/2018.
 */

public interface ICommentRepository {
    List<Comment> getCommentListForPost(int postid);
}
