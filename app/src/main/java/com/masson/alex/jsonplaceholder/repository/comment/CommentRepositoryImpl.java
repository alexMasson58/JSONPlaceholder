package com.masson.alex.jsonplaceholder.repository.comment;

import com.masson.alex.jsonplaceholder.model.Comment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class CommentRepositoryImpl extends CommentRepositorySharedPref implements ICommentRepository, ICommentRepository.ICommentRepositoryListener {

    private final CommentRepositoryRetrofit retrofit = new CommentRepositoryRetrofit();

    private ICommentRepositoryListener mListener;
    private int mPostId;

    @Override
    public void postComment(int postid, int id, String name, String email, String comment, ICommentRepositoryListener listener) {
        mListener = listener;
        mPostId = postid;
        retrofit.postComment(postid, id, name, email, comment, this);
    }

    @Override
    public void getCommentListForPost(int postid, ICommentRepositoryListener listener) {
        mListener = listener;
        mPostId = postid;
        retrofit.getCommentListForPost(postid, this);
    }

    @Override
    public void commentsForPost(List<Comment> comments) {
        List<Comment> locals = new ArrayList<>();
        if (this.comments.containsKey(mPostId+"")) {
            locals.addAll(this.comments.get(mPostId+""));
        }
        comments.addAll(locals);
        mListener.commentsForPost(comments);
    }

    @Override
    public void commentSuccess(Comment comment) {

        //comment succesfully added to the WS
        //need to post it into Sharedprefs
        super.postComment(comment.getPostId(), comment.getId(), comment.getName(), comment.getEmail(), comment.getBody(), this);
        mListener.commentSuccess(comment);

    }

    @Override
    public void onError(String message) {
        mListener.onError(message);
    }
}
