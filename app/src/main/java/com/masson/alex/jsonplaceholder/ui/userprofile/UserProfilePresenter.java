package com.masson.alex.jsonplaceholder.ui.userprofile;

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

public class UserProfilePresenter implements IAlbumRepository.IAlbumRepositoryListener, IPostRepository.IPostRepositoryListener {

    View view;
    IAlbumRepository albumRepository;
    IPostRepository postRepository;
    List<Post> posts;
    List<Album> albums;

    public UserProfilePresenter(View view) {
        this.view = view;
        this.postRepository = MyApplication.app().getPostRepository();
        this.albumRepository = MyApplication.app().getAlbumRepository();
    }


    public void setView(View view) {
        this.view = view;
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
/*        ArrayList<UserListViewModel> res = new ArrayList<>();
        if (userlist != null) {
            for (User u : userlist
                    ) {
                res.add(new UserListViewModel(u));
            }
        }
        view.refreshUserList(res);*/
    }

    @Override
    public void postlistUpdated(List<Post> posts) {

    }

    @Override
    public void postForUsers(List<Post> posts) {

    }

    interface View {
        void displayUserProfile(UserViewModel userViewModel);

        void onError(String message);

        void albumListUpdated(List<AlbumListViewModel> albums);

        void postListUpdated(List<PostListViewModel> posts);
    }
}
