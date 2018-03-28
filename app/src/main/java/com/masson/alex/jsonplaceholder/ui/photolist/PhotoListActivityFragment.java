package com.masson.alex.jsonplaceholder.ui.photolist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.ui.commentlist.CommentListPresenter;
import com.masson.alex.jsonplaceholder.ui.commentlist.CommentRecyclerAdapter;
import com.masson.alex.jsonplaceholder.viewmodel.AlbumViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.PhotoViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.PostViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class PhotoListActivityFragment extends Fragment implements PhotoListPresenter.View, PhotoListRecyclerAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String PRESENTER_STATE = "PRESENTER_STATE";
    public static final String ALBUM_EXTRAS = "ALBUM_EXTRAS";

    @BindView(R.id.rec_photolist)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private GridLayoutManager viewManager;

    private PhotoListPresenter presenter;
    private PhotoListRecyclerAdapter adapter;

    private AlbumViewModel avm;



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PRESENTER_STATE, presenter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        avm = (AlbumViewModel) getArguments().getSerializable(ALBUM_EXTRAS);

        viewManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(viewManager);
        presenter = new PhotoListPresenter(this, MyApplication.app().getPhotoRepository());
        adapter = new PhotoListRecyclerAdapter(this, getContext());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        //If restoring from state, load the list from the bundle
        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(PRESENTER_STATE);
            presenter.bind(this, MyApplication.app().getPhotoRepository());
        }
        else{
            onRefresh();
        }
    }

    public static Fragment newInstance() {
        Fragment f= new PhotoListActivityFragment();
        return f;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void displayErrorMessage(String message) {
        Toast.makeText(getContext(),message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void photoListUpdated(List<PhotoViewModel> photos) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setPhotoList(photos);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }

        presenter.getPhotoForAlbum(avm.getId());
    }
}
