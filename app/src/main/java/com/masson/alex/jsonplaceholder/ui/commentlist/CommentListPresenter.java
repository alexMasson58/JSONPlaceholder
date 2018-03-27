package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Comment;
import com.masson.alex.jsonplaceholder.repository.comment.ICommentRepository;
import com.masson.alex.jsonplaceholder.viewmodel.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class CommentListPresenter implements ICommentRepository.ICommentRepositoryListener, Parcelable{

    private ICommentRepository repository;
    private View view;

    private List<Comment> comments;

    public CommentListPresenter(View view) {
        this.view = view;
        this.repository = MyApplication.app().getCommentRepository();
        comments = new ArrayList<>();
    }

    protected CommentListPresenter(Parcel in) {
        comments = in.createTypedArrayList(Comment.CREATOR);
        repository = MyApplication.app().getCommentRepository();
    }

    public static final Creator<CommentListPresenter> CREATOR = new Creator<CommentListPresenter>() {
        @Override
        public CommentListPresenter createFromParcel(Parcel in) {
            return new CommentListPresenter(in);
        }

        @Override
        public CommentListPresenter[] newArray(int size) {
            return new CommentListPresenter[size];
        }
    };

    public void bind(View view) {
        this.view = view;
        if (comments != null) {
            commentsForPost(this.comments);
        }
    }

    @Override
    public void commentsForPost(List<Comment> comments) {
        this.comments = comments;
        ArrayList<CommentViewModel> res = new ArrayList<>();
        if (comments != null) {
            for (Comment u : comments
                    ) {
                res.add(new CommentViewModel(u));
            }
        }
        view.commentListUpdated(res);
    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    public void getPostComment(int postid){
        repository.getCommentListForPost(postid, this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(comments);
    }

    interface View {
        void displayErrorMessage(String message);

        void commentListUpdated(List<CommentViewModel> comments);

        void onRefresh();

    }

}
