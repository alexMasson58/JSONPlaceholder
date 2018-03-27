package com.masson.alex.jsonplaceholder.ui.userprofile.albums;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.repository.album.IAlbumRepository;
import com.masson.alex.jsonplaceholder.viewmodel.AlbumViewModel;

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

    /*@Override
    public void albumListUpdated(List<Album> albums) {

    }*/

    @Override
    public void albumsForUser(List<Album> albums) {
        this.albums = albums;
        ArrayList<AlbumViewModel> res = new ArrayList<>();
        if (albums != null) {
            for (Album u : albums
                    ) {
                res.add(new AlbumViewModel(u));
            }
        }
        view.albumListUpdated(res);
    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    public void getUserAlbums(int userid) {
        albumRepository.getAlbumsListForUser(userid, this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(albums);
    }

    public List<Album> getAlbumList() {
        return albums;
    }

    public void albumClicked(int position) {
        view.displayAlbum(new AlbumViewModel(albums.get(position)));
    }

    interface View {
        void displayErrorMessage(String message);

        void albumListUpdated(List<AlbumViewModel> albums);

        void onRefresh();

        void displayAlbum(AlbumViewModel album);
    }
}
