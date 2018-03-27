package com.masson.alex.jsonplaceholder.ui.userprofile.albums;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.viewmodel.AlbumViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by frup66058 on 27/03/2018.
 */

public class UserProfileAlbumRecyclerAdapter extends RecyclerView.Adapter<UserProfileAlbumRecyclerAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    private final ItemClickListener listener;
    private List<AlbumViewModel> albumList;

    public UserProfileAlbumRecyclerAdapter(ItemClickListener listener) {
        this.listener = listener;
        albumList = new ArrayList<>();

    }

    @NonNull
    @Override
    public UserProfileAlbumRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserProfileAlbumRecyclerAdapter.ViewHolder holder, int position) {
        AlbumViewModel avm = albumList.get(position);
        holder.setTitle(avm.getTitle());
        holder.setId(avm.getId() + "");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(holder.getAdapterPosition());
                }
            }
        });

    }

    public void setAlbumList(List<AlbumViewModel> albumList) {
        this.albumList = albumList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.id)
        TextView id;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        void setTitle(String title) {
            this.title.setText(title);
        }

        void setId(String id) {
            this.id.setText(id);
        }
    }
}
