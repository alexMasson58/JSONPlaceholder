package com.masson.alex.jsonplaceholder.ui.photolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by frup66058 on 28/03/2018.
 */

public class PhotoListRecyclerAdapter extends RecyclerView.Adapter<PhotoListRecyclerAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClicked(int position);
    }

    private ItemClickListener listener;
    private final List<PhotoViewModel> photoList = new ArrayList<>();
    private final Context mContext;

    public PhotoListRecyclerAdapter(ItemClickListener listener, Context context) {
        this.listener = listener;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoViewModel pvm = photoList.get(position);
        holder.setThumbnail(mContext, pvm.getThumbnailUrl());
        holder.setTitre(pvm.getTitle());

    }

    public void setPhotoList(List<PhotoViewModel> photos) {
        this.photoList.clear();
        this.photoList.addAll(photos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        @BindView(R.id.titre)
        TextView titre;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        void setThumbnail(Context mContext, String url) {
            Glide.with(mContext).load(url).apply(new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher))
                    .into(this.thumbnail);
        }

        void setTitre(String titre) {
            this.titre.setText(titre);
        }
    }
}
