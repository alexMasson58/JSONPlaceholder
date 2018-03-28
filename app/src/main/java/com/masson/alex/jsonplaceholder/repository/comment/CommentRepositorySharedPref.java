package com.masson.alex.jsonplaceholder.repository.comment;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masson.alex.jsonplaceholder.application.MyApplication;
import com.masson.alex.jsonplaceholder.model.Comment;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frup66058 on 28/03/2018.
 * Required to store locally created comments since WS pmock repsonse and doesn't create object server side
 */

public class CommentRepositorySharedPref implements ICommentRepository {

    private static final String PREFS_COMMENTS = "PREFS_COMMENT";
    private static final String PREFS = "PREFS_JSONPLACEHOLDER";


    protected final Map<String, List<Comment>> comments = new HashMap<>();

    CommentRepositorySharedPref() {
        SharedPreferences mPrefs = MyApplication.app().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(PREFS_COMMENTS, "");
        if(json != null && json.length()>0) {
            Type listType = new TypeToken<HashMap<String, List<Comment>>>() {
            }.getType();
            Map<String, List<Comment>> commentPrefs = (Map<String, List<Comment>>) gson.fromJson(json, listType);

            if (commentPrefs != null) {
                comments.clear();
                comments.putAll(commentPrefs);
            }
        }
    }

    @Override
    public void postComment(int postid, int id, String name, String email, String comment, ICommentRepositoryListener listener) {
        Comment c = new Comment(postid, id, name, email, comment);
        if (!comments.containsKey(postid+"")) {
            comments.put(postid+"", new ArrayList<Comment>());
        }
        comments.get(postid+"").add(c);

        saveComments();

    }

    private void saveComments() {
        SharedPreferences mPrefs = MyApplication.app().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(PREFS_COMMENTS, json);
        prefsEditor.apply();
    }

    @Override
    public void getCommentListForPost(int postid, ICommentRepositoryListener listener) {
        if (comments.containsKey(postid+"")) {
            if (listener != null) {
                listener.commentsForPost(comments.get(postid+""));
            }
        } else {
            if (listener != null) {
                listener.commentsForPost(new ArrayList<Comment>());
            }
        }
    }
}
