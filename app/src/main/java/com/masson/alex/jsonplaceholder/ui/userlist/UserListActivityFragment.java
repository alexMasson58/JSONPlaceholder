package com.masson.alex.jsonplaceholder.ui.userlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.ui.userprofile.UserProfileActivity;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserListActivityFragment extends Fragment implements UserListPresenter.View, SwipeRefreshLayout.OnRefreshListener, UserRecyclerAdapter.ItemClickListener {


    public static final String USERPROFILE_EXTRA = "USERPROFILE_EXTRA";

    @BindView(R.id.rec_userlist)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager viewManager;
    private UserListPresenter presenter;
    private UserRecyclerAdapter adapter;


    public UserListActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);

        viewManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewManager);
        presenter = new UserListPresenter(MyApplication.app().getUserRepository(), this);
        adapter = new UserRecyclerAdapter(this, presenter.getUserList());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            refreshUserList();
        }
    }

    @Override
    public void refreshUserList() {
        adapter.setUserList(presenter.getUserList());
    }

    @Override
    public void displayUserProfile(UserViewModel u) {
        Intent i = new Intent(getActivity(), UserProfileActivity.class);
        i.putExtra(USERPROFILE_EXTRA, u);
    }


    @Override
    public void onRefresh() {
        refreshUserList();
    }

    @Override
    public void onItemClicked(int position) {
        presenter.userClicked(position);
    }
}
