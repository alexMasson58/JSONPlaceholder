package com.masson.alex.jsonplaceholder.ui.userprofile;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Album;
import com.masson.alex.jsonplaceholder.model.Post;
import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.repository.album.IAlbumRepository;
import com.masson.alex.jsonplaceholder.repository.post.IPostRepository;
import com.masson.alex.jsonplaceholder.viewmodel.AlbumListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.PostListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserListViewModel;
import com.masson.alex.jsonplaceholder.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frup66058 on 26/03/2018.
 */

public class UserProfilePresenter implements Parcelable, IAlbumRepository.IAlbumRepositoryListener, IPostRepository.IPostRepositoryListener {

    private final UserViewModel userViewModel;
    View view;
    IAlbumRepository albumRepository;
    IPostRepository postRepository;
    List<Post> posts;
    List<Album> albums;

    public UserProfilePresenter(View view, UserViewModel userViewModel) {
        this.view = view;
        this.postRepository = MyApplication.app().getPostRepository();
        this.albumRepository = MyApplication.app().getAlbumRepository();
        this.userViewModel = userViewModel;
    }


    protected UserProfilePresenter(Parcel in) {
        userViewModel = in.readParcelable(UserViewModel.class.getClassLoader());
        posts = in.createTypedArrayList(Post.CREATOR);
        albums = in.createTypedArrayList(Album.CREATOR);
    }

    public static final Creator<UserProfilePresenter> CREATOR = new Creator<UserProfilePresenter>() {
        @Override
        public UserProfilePresenter createFromParcel(Parcel in) {
            return new UserProfilePresenter(in);
        }

        @Override
        public UserProfilePresenter[] newArray(int size) {
            return new UserProfilePresenter[size];
        }
    };

    public void bind(View view) {
        this.view = view;
        if(albums!=null){
            albumsForUser(this.albums);
        }
        if(posts!=null){
            postForUsers(this.posts);
        }
    }

    public void getUserAlbums(int userid) {
        albumRepository.getAlbumsListForUser(userid);
    }

    public void getUserPosts(int userid) {
        postRepository.getPostsListForUser(userid);
    }

    @Override
    public void albumListUpdated(List<Album> albums) {

    }

    @Override
    public void albumsForUser(List<Album> albums) {
        this.albums = albums;
        
    }

    @Override
    public void postlistUpdated(List<Post> posts) {

    }

    @Override
    public void postForUsers(List<Post> posts) {

    }

    @Override
    public void onError(String message) {
        view.displayErrorMessage(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(userViewModel, i);
        parcel.writeTypedList(posts);
        parcel.writeTypedList(albums);
    }

    interface View {
        void displayUserProfile(UserViewModel userViewModel);

        void displayErrorMessage(String message);

        void albumListUpdated(List<AlbumListViewModel> albums);

        void postListUpdated(List<PostListViewModel> posts);
    }
}
