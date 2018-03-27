package com.masson.alex.jsonplaceholder.ui.userprofile.posts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.viewmodel.PostViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class UserProfilePostRecyclerAdapter extends RecyclerView.Adapter<UserProfilePostRecyclerAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    private final ItemClickListener listener;
    private List<PostViewModel> postList;

    public UserProfilePostRecyclerAdapter(ItemClickListener listener) {
        this.listener = listener;
        this.postList = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserProfilePostRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserProfilePostRecyclerAdapter.ViewHolder holder, int position) {
        PostViewModel pvm = postList.get(position);
        holder.setTitle(pvm.getTitle());
        holder.setId(pvm.getId() + "");
        holder.setBody(pvm.getBody());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(holder.getAdapterPosition());
                }
            }
        });
    }

    public void setPostList(List<PostViewModel> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        @BindView(R.id.id)
        TextView id;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.body)
        TextView body;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setId(String id) {
            this.id.setText(id);
        }

        public void setBody(String body) {
            this.body.setText(body);
        }
    }
}
