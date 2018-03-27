package com.masson.alex.jsonplaceholder.ui.userprofile.albums;


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
import com.masson.alex.jsonplaceholder.viewmodel.AlbumViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAlbumsFragment extends Fragment implements UserProfileAlbumPresenter.View, SwipeRefreshLayout.OnRefreshListener, UserProfileAlbumRecyclerAdapter.ItemClickListener {

    public static final String PRESENTER_STATE = "PRESENTER_STATE";
    public static final String USERPROFILE_EXTRA = "USERPROFILE_EXTRA";
    @BindView(R.id.rec_albumlist)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager viewManager;

    UserProfileAlbumPresenter presenter;
    UserProfileAlbumRecyclerAdapter adapter;
    private UserViewModel uvm;

    public static UserAlbumsFragment newInstance() {
        UserAlbumsFragment fragment = new UserAlbumsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uvm = (UserViewModel) getArguments().getSerializable(USERPROFILE_EXTRA);
        return inflater.inflate(R.layout.fragment_user_albums, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PRESENTER_STATE, presenter);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);

        viewManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewManager);
        presenter = new UserProfileAlbumPresenter(this);
        adapter = new UserProfileAlbumRecyclerAdapter(this);
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
    public void albumListUpdated(List<AlbumViewModel> albums) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setAlbumList(albums);
    }

    @Override
    public void onRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        presenter.getUserAlbums(uvm.getId());
    }

    @Override
    public void onItemClicked(int position) {
        presenter.albumClicked(position);
    }

    @Override
    public void displayAlbum(AlbumViewModel album) {
        Toast.makeText(getContext(), "album : " + album.getTitle(), Toast.LENGTH_LONG).show();
    }
}