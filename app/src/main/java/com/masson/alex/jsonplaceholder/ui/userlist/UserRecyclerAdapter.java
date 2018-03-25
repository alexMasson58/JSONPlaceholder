package com.masson.alex.jsonplaceholder.ui.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.viewmodel.UserListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 25/03/2018.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    private final ItemClickListener listener;
    private List<UserListViewModel> userList;

    public UserRecyclerAdapter(ItemClickListener listener, List<UserListViewModel> userList) {
        this.userList = userList;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_user, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        UserListViewModel uvm = userList.get(position);
        holder.setName(uvm.getName());
        holder.setUsername(uvm.getUsername());
        holder.setEmail(uvm.getEmail());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void setUserList(List<UserListViewModel> userList) {
        this.userList = userList;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;

        @BindView(R.id.name)
        protected TextView name;

        @BindView(R.id.username)
        protected TextView username;

        @BindView(R.id.email)
        protected TextView email;

        protected ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        void setName(String name) {
            this.name.setText(name);
        }

        void setUsername(String username) {
            this.username.setText(username);
        }

        void setEmail(String email) {
            this.email.setText(email);
        }

    }
}
