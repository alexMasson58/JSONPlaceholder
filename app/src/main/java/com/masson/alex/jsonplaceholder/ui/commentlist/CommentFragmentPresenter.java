package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.text.TextUtils;

import com.masson.alex.jsonplaceholder.model.Comment;
import com.masson.alex.jsonplaceholder.repository.comment.ICommentRepository;

import java.util.List;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class CommentFragmentPresenter implements ICommentRepository.ICommentRepositoryListener {

    ICommentRepository repository;
    private View view;

    public CommentFragmentPresenter(View view, ICommentRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    void comment(int postid, String name, String email, String comment) {
        boolean valid = true;

        if (email.length() == 0) {
            valid = false;
            view.emptyEmail();
        } else {
            if (!mailValid(email)) {
                valid = false;
                view.invalidEmail();
            }
        }

        if (name.length() == 0) {
            valid = false;
            view.emptyName();
        }


        if (comment.length() == 0) {
            valid = false;
            view.emptyComment();
        }
        if (valid) {
            repository.postComment(postid, 0, name, email, comment, this);
        }

    }

    private boolean mailValid(String text) {
        return !TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    @Override
    public void commentsForPost(List<Comment> comments) {

    }

    @Override
    public void commentSuccess(Comment comment) {
        view.commentSuccess(comment);
    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    interface View {
        void commentSuccess(Comment comment);

        void emptyEmail();

        void invalidEmail();

        void emptyComment();

        void displayErrorMessage(String message);

        void emptyName();
    }

}
