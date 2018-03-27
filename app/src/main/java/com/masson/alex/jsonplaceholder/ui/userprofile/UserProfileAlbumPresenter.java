package com.masson.alex.jsonplaceholder.ui.userprofile;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.repository.album.IAlbumRepository;
import com.masson.alex.jsonplaceholder.viewmodel.AlbumListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 26/03/2018.
 */

public class UserProfileAlbumPresenter implements Parcelable, IAlbumRepository.IAlbumRepositoryListener {

    IAlbumRepository albumRepository;
    List<Album> albums;
    private View view;

    public UserProfileAlbumPresenter(View view) {
        this.view = view;
        albumRepository = MyApplication.app().getAlbumRepository();
        albums = new ArrayList<>();
    }

    protected UserProfileAlbumPresenter(Parcel in) {
        albums = in.createTypedArrayList(Album.CREATOR);
        albumRepository = MyApplication.app().getAlbumRepository();
    }

    public static final Creator<UserProfileAlbumPresenter> CREATOR = new Creator<UserProfileAlbumPresenter>() {
        @Override
        public UserProfileAlbumPresenter createFromParcel(Parcel in) {
            return new UserProfileAlbumPresenter(in);
        }

        @Override
        public UserProfileAlbumPresenter[] newArray(int size) {
            return new UserProfileAlbumPresenter[size];
        }
    };

    public void bind(View view) {
        this.view = view;
        if (albums != null) {
            albumsForUser(this.albums);
        }

    }

    @Override
    public void albumListUpdated(List<Album> albums) {

    }

    @Override
    public void albumsForUser(List<Album> albums) {
        this.albums = albums;
        ArrayList<AlbumListViewModel> res = new ArrayList<>();
        if (albums != null) {
            for (Album u : albums
                    ) {
                res.add(new AlbumListViewModel(u));
            }
        }
        view.albumListUpdated(res);
    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    public void getUserAlbums(int userid) {
        albumRepository.getAlbumsListForUser(userid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(albums);
    }

    interface View {
        void displayErrorMessage(String message);

        void albumListUpdated(List<AlbumListViewModel> albums);

    }
}
