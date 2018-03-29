package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Comment;
import com.masson.alex.jsonplaceholder.viewmodel.PostViewModel;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentFragment extends DialogFragment implements CommentFragmentPresenter.View {


    private static final String POST_EXTRA = "POST_EXTRA";
    private CommentFragmentPresenter presenter;
    private PostViewModel pvm;

    static CommentFragment newInstance() {
        CommentFragment f = new CommentFragment();


        return f;
    }


    interface CommentDialogListener {
        void comment(String email, String comment);
    }

    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.tilComment)
    TextInputLayout tilComment;

    @BindView(R.id.tilName)
    TextInputLayout tilName;

    @BindView(R.id.ed_mail)
    EditText email;
    @BindView(R.id.ed_comment)
    EditText comment;

    @BindView(R.id.ed_name)
    EditText name;

    CommentDialogListener listener;

    public void setListener(CommentDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comment_dialog, container,
                false);
        ButterKnife.bind(this, rootView);
        presenter = new CommentFragmentPresenter(this, MyApplication.app().getCommentRepository());
        pvm = (PostViewModel) getArguments().getSerializable(POST_EXTRA);
        return rootView;
    }

    @OnClick(R.id.btn_commenter)
    public void comment() {
        tilComment.setError("");
        tilEmail.setError("");
        tilName.setError("");
        presenter.comment(pvm.getId(), name.getText().toString(), email.getText().toString(), comment.getText().toString());

    }


    @Override
    public void commentSuccess(Comment comment) {

        EventBus.getDefault().post(new CommentEvent());
        dismiss();
    }

    @Override
    public void emptyEmail() {
        tilEmail.setError(getString(R.string.error_email_empty));
    }

    @Override
    public void invalidEmail() {
        tilEmail.setError(getString(R.string.error_email_invalid));
    }

    @Override
    public void emptyComment() {
        tilComment.setError(getString(R.string.error_comment));
    }

    @Override
    public void displayErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void emptyName() {
        tilName.setError(getString(R.string.error_name));
    }
}
