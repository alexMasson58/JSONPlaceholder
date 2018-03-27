package com.masson.alex.jsonplaceholder.ui.userprofile.posts;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.ui.userlist.UserRecyclerAdapter;
import com.masson.alex.jsonplaceholder.ui.userprofile.albums.UserProfileAlbumPresenter;
import com.masson.alex.jsonplaceholder.ui.userprofile.albums.UserProfileAlbumRecyclerAdapter;
import com.masson.alex.jsonplaceholder.viewmodel.PostViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserPostsFragment extends Fragment implements UserProfilePostPresenter.View, UserProfilePostRecyclerAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    public static final String PRESENTER_STATE = "PRESENTER_STATE";
    public static final String USERPROFILE_EXTRA = "USERPROFILE_EXTRA";
    @BindView(R.id.rec_postlist)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager viewManager;

    UserProfilePostPresenter presenter;
    UserProfilePostRecyclerAdapter adapter;
    private UserViewModel uvm;

    public static UserPostsFragment newInstance() {
        UserPostsFragment fragment = new UserPostsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uvm = (UserViewModel) getArguments().getSerializable(USERPROFILE_EXTRA);
        return inflater.inflate(R.layout.fragment_user_posts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);

        viewManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewManager);
        presenter = new UserProfilePostPresenter(this);
        adapter = new UserProfilePostRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        //If restoring from state, load the list from the bundle
        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(PRESENTER_STATE);
            presenter.bind(this);
        } else {
            onRefresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
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
    public void postListUpdated(List<PostViewModel> posts) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setPostList(posts);
    }

    @Override
    public void onRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        presenter.getUserPosts(uvm.getId());
    }

    @Override
    public void displayPost(PostViewModel post) {

        Toast.makeText(getContext(), "album : " + post.getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(int position) {
        presenter.postClicked(position);
    }

}
