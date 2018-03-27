package com.masson.alex.jsonplaceholder.ui.userprofile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.masson.alex.jsonplaceholder.R;
import com.masson.alex.jsonplaceholder.ui.userprofile.albums.UserAlbumsFragment;
import com.masson.alex.jsonplaceholder.ui.userprofile.posts.UserPostsFragment;
import com.masson.alex.jsonplaceholder.ui.userprofile.profile.UserProfileFragment;

public class UserProfileActivity extends AppCompatActivity {

    private static final java.lang.String ITEMSELECTED = "ITEMSELECTED";
    private int itemSelected;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() != itemSelected) {
                itemSelected = item.getItemId();
                switch (itemSelected) {
                    case R.id.navigation_profile:
                        profileFragment();
                        return true;
                    case R.id.navigation_album:
                        albumsFragment();
                        return true;
                    case R.id.navigation_posts:
                        postsFragment();
                        return true;
                }
                return false;
            } else {
                return true;
            }
        }
    };


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ITEMSELECTED, itemSelected);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState != null) {
            itemSelected = savedInstanceState.getInt(ITEMSELECTED);
            switch (itemSelected) {
                case R.id.navigation_profile:
                    profileFragment();
                    break;
                case R.id.navigation_album:
                    albumsFragment();
                    break;
                case R.id.navigation_posts:
                    postsFragment();
                    break;
            }
        } else {
            profileFragment();
        }
    }

    void postsFragment() {
        Fragment frag = UserPostsFragment.newInstance();
        frag.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, frag);
        transaction.commit();
    }

    void profileFragment() {
        Fragment frag = UserProfileFragment.newInstance();
        frag.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, frag);
        transaction.commit();
    }

    private void albumsFragment() {
        Fragment frag = UserAlbumsFragment.newInstance();
        frag.setArguments(getIntent().getExtras());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, frag);
        transaction.commit();
    }


}
