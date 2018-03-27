package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.ui.userlist.UserListPresenter;
import com.masson.alex.jsonplaceholder.ui.userlist.UserRecyclerAdapter;
import com.masson.alex.jsonplaceholder.viewmodel.CommentViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.PostViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class CommentListActivityFragment extends Fragment implements CommentListPresenter.View, UserRecyclerAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String PRESENTER_STATE = "PRESENTER_STATE";
    private static final String POST_EXTRA = "POST_EXTRA";

    @BindView(R.id.rec_commentlist)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager viewManager;

    private CommentListPresenter presenter;
    private CommentRecyclerAdapter adapter;

    private PostViewModel pvm;
    public CommentListActivityFragment() {
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PRESENTER_STATE, presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_comment_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        pvm = (PostViewModel) getArguments().getSerializable(POST_EXTRA);

        viewManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewManager);
        presenter = new CommentListPresenter(this, MyApplication.app().getCommentRepository());
        adapter = new CommentRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        //If restoring from state, load the list from the bundle
        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(PRESENTER_STATE);
            presenter.bind(this, MyApplication.app().getCommentRepository());
        }
        else{
            onRefresh();
        }
    }


    @Override
    public void displayErrorMessage(String message) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void commentListUpdated(List<CommentViewModel> comments) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setCommentList(comments);
    }

    @Override
    public void onRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }

        presenter.getPostComment(pvm.getId());
    }


    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClicked(int position) {

    }

    public static Fragment newInstance() {
        Fragment frag = new CommentListActivityFragment();
        return frag;
    }


}
