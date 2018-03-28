package com.masson.alex.jsonplaceholder.application;

import android.app.Application;

import com.masson.alex.jsonplaceholder.repository.album.AlbumRepositoryImpl;
import com.masson.alex.jsonplaceholder.repository.album.IAlbumRepository;
import com.masson.alex.jsonplaceholder.repository.comment.CommentRepositoryImpl;
import com.masson.alex.jsonplaceholder.repository.comment.CommentRepositoryRetrofit;
import com.masson.alex.jsonplaceholder.repository.comment.ICommentRepository;
import com.masson.alex.jsonplaceholder.repository.photo.IPhotoRepository;
import com.masson.alex.jsonplaceholder.repository.photo.PhotoRepositoryImpl;
import com.masson.alex.jsonplaceholder.repository.post.IPostRepository;
import com.masson.alex.jsonplaceholder.repository.post.PostRepositoryImpl;
import com.masson.alex.jsonplaceholder.repository.user.IUserRepository;
import com.masson.alex.jsonplaceholder.repository.user.UserRepositoryImpl;

/**
 * Created by alex on 24/03/2018.
 */

public class MyApplication extends Application {

    private static MyApplication INSTANCE;
    private IUserRepository userRepository;
    private IAlbumRepository albumRepository;
    private IPostRepository postRepository;
    private IPhotoRepository photoRepository;
    private ICommentRepository commentRepository;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        commentRepository = new CommentRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        albumRepository = new AlbumRepositoryImpl();
        postRepository = new PostRepositoryImpl();
        photoRepository = new PhotoRepositoryImpl();
    }

    public static MyApplication app() {
        return INSTANCE;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public IAlbumRepository getAlbumRepository() {
        return albumRepository;
    }

    public IPostRepository getPostRepository() {
        return postRepository;
    }

    public IPhotoRepository getPhotoRepository() {
        return photoRepository;
    }

    public ICommentRepository getCommentRepository() {
        return commentRepository;
    }
}
