package com.masson.alex.jsonplaceholder.ui.userprofile.posts;

import android.os.Parcel;
import android.os.Parcelable;

import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Post;
import com.masson.alex.jsonplaceholder.repository.post.IPostRepository;
import com.masson.alex.jsonplaceholder.viewmodel.PostViewModel;

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
        postRepository.getPostsListForUser(userid, this);
    }

   /* @Override
    public void postlistUpdated(List<Post> posts) {

    }*/

    @Override
    public void postForUsers(List<Post> posts) {
        this.posts = posts;
        ArrayList<PostViewModel> res = new ArrayList<>();
        if (posts != null) {
            for (Post u : posts
                    ) {
                res.add(new PostViewModel(u));
            }
        }
        view.postListUpdated(res);
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

    public void postClicked(int position) {
        view.displayPost(new PostViewModel(posts.get(position)));
    }

    interface View {
        void displayErrorMessage(String message);

        void postListUpdated(List<PostViewModel> posts);

        void onRefresh();

        void displayPost(PostViewModel post);
    }
}
