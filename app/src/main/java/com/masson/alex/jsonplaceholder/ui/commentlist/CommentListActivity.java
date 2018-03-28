package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.masson.alex.jsonplaceholder.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentListActivity extends AppCompatActivity implements CommentFragment.CommentDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        android.support.v4.app.Fragment frag = CommentListActivityFragment.newInstance();
        frag.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, frag);
        transaction.commit();

    }

    @OnClick(R.id.fab)
    public void comment(){
        FragmentManager fm = getSupportFragmentManager();
        CommentFragment alertDialog = CommentFragment.newInstance();
        alertDialog.setArguments(getIntent().getExtras());
        alertDialog.setListener(this);
        alertDialog.show(fm, "dialog");
    }

    @Override
    public void comment(String email, String comment) {

    }
}
