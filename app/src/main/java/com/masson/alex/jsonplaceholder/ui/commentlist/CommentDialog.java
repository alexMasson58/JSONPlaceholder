package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.masson.alex.jsonplaceholder.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class CommentDialog extends DialogFragment {

    static CommentDialog newInstance() {
        CommentDialog f = new CommentDialog();



        return f;
    }

    interface CommentDialogListener{
        void comment(String email, String comment);
    }

    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.tilComment)
    TextInputLayout tilComment;

    @BindView(R.id.ed_mail)
    EditText email;
    @BindView(R.id.ed_comment)
    EditText comment;

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
        getDialog().setTitle(getString(R.string.commenter));

        return rootView;
    }

    @OnClick(R.id.btn_commenter)
    public void comment(){
        boolean valid = true;

        if(email.getText().length() ==0){
            valid = false;
            tilEmail.setError(getString(R.string.error_email_empty));
        }
        else{
            if(!mailValid(email.getText())){
                valid=false;
                tilEmail.setError(getString(R.string.error_email_invalid));
            }
        }


        if(comment.getText().length()==0){
            valid = false;
            tilComment.setError(getString(R.string.error_comment));
        }



        if(valid && listener != null){
            tilComment.setError("");
            tilEmail.setError("");
            listener.comment(email.getText().toString(), comment.getText().toString());
        }
    }

    private boolean mailValid(Editable text) {
        return !TextUtils.isEmpty(text.toString()) && android.util.Patterns.EMAIL_ADDRESS.matcher(text.toString()).matches();
    }

}
