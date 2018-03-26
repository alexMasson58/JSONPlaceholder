package com.masson.alex.jsonplaceholder.ui.userlist;

import android.content.Intent;
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
import com.masson.alex.jsonplaceholder.ui.userprofile.UserProfileActivity;
import com.masson.alex.jsonplaceholder.viewmodel.UserListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserListActivityFragment extends Fragment implements UserListPresenter.View, SwipeRefreshLayout.OnRefreshListener, UserRecyclerAdapter.ItemClickListener {


    public static final String USERPROFILE_EXTRA = "USERPROFILE_EXTRA";
    public static final String USERLIST_STATE = "USERLIST_STATE";
    public static final String PRESENTER_STATE = "PRESENTER_STATE";

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*outState.putParcelableArrayList(USERLIST_STATE, (ArrayList<? extends Parcelable>) adapter.getUserList());*/
        outState.putParcelable(PRESENTER_STATE, presenter);
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
        presenter = new UserListPresenter(this);
        adapter = new UserRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        //If restoring from state, load the list from the bundle
        if (savedInstanceState != null) {
           /* ArrayList<UserListViewModel> list = savedInstanceState.getParcelableArrayList(USERLIST_STATE);
            adapter.setUserList(list);*/
            presenter = savedInstanceState.getParcelable(PRESENTER_STATE);
            presenter.bind(this);
        }
        else{
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
    public void refreshUserList(List<UserListViewModel> users) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        adapter.setUserList(users);
    }

    @Override
    public void displayUserProfile(UserViewModel u) {
        Intent i = new Intent(this.getContext(), UserProfileActivity.class);
        i.putExtra(USERPROFILE_EXTRA, u);
        getContext().startActivity(i);
    }

    @Override
    public void displayErrorMessage(String message) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRefresh() {
        presenter.getUserList();
    }

    @Override
    public void onItemClicked(int position) {
        presenter.userClicked(position);
    }
}
