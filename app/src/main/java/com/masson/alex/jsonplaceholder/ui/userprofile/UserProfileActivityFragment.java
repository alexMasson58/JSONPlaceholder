package com.masson.alex.jsonplaceholder.ui.userprofile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masson.alex.jsonplaceholder.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserProfileActivityFragment extends Fragment {

    public UserProfileActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }
}
