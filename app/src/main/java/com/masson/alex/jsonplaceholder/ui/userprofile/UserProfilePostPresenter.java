package com.masson.alex.jsonplaceholder.ui.userprofile;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Post;
import com.masson.alex.jsonplaceholder.repository.post.IPostRepository;
import com.masson.alex.jsonplaceholder.viewmodel.PostListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 26/03/2018.
 */

public class UserProfilePostPresenter implements Parcelable, IPostRepository.IPostRepositoryListener {

    IPostRepository postRepository;
    List<Post> posts;
    private View view;


    public UserProfilePostPresenter(View view) {
        this.view = view;
        posts = new ArrayList<>();
        postRepository = MyApplication.app().getPostRepository();
    }

    protected UserProfilePostPresenter(Parcel in) {
        posts = in.createTypedArrayList(Post.CREATOR);
        postRepository = MyApplication.app().getPostRepository();
    }

    public static final Creator<UserProfilePostPresenter> CREATOR = new Creator<UserProfilePostPresenter>() {
        @Override
        public UserProfilePostPresenter createFromParcel(Parcel in) {
            return new UserProfilePostPresenter(in);
        }

        @Override
        public UserProfilePostPresenter[] newArray(int size) {
            return new UserProfilePostPresenter[size];
        }
    };

    public void bind(View view) {
        this.view = view;

        if (posts != null) {
            postForUsers(this.posts);
        }
    }

    public void getUserPosts(int userid) {
        postRepository.getPostsListForUser(userid);
    }

    @Override
    public void postlistUpdated(List<Post> posts) {
        this.posts = posts;
        ArrayList<PostListViewModel> res = new ArrayList<>();
        if (posts != null) {
            for (Post u : posts
                    ) {
                res.add(new PostListViewModel(u));
            }
        }
        view.postListUpdated(res);
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(posts);
    }

    interface View {
        void displayErrorMessage(String message);


        void postListUpdated(List<PostListViewModel> posts);
    }
}
