package com.masson.alex.jsonplaceholder.ui.commentlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.ui.userlist.UserRecyclerAdapter;
import com.masson.alex.jsonplaceholder.viewmodel.CommentViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder> {


    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    private final ItemClickListener listener;
    private final List<CommentViewModel> commentList = new ArrayList<>();

    public CommentRecyclerAdapter(ItemClickListener listener) {
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentViewModel cmv = commentList.get(position);
        holder.setId(cmv.getId() + "");
        holder.setName(cmv.getName());
        holder.setBody(cmv.getBody());
        holder.setEmail(cmv.getEmail());
    }

    public void setCommentList(List<CommentViewModel> commentList) {
        this.commentList.clear();
        this.commentList.addAll(commentList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id)
        TextView id;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.email)
        TextView email;

        @BindView(R.id.body)
        TextView body;

        private final View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        void setId(String id) {
            this.id.setText(id);
        }

        void setName(String name) {
            this.name.setText(name);
        }

        void setEmail(String email) {
            this.email.setText(email);
        }

        void setBody(String body) {
            this.body.setText(body);
        }
    }
}
