package com.masson.alex.jsonplaceholder.repository.user;

import com.masson.alex.jsonplaceholder.model.User;
import com.masson.alex.jsonplaceholder.network.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alex on 24/03/2018.
 */

public class UserRepositoryImpl implements IUserRepository {

    private UserService service;

    public UserRepositoryImpl() {
        if (this.service == null) {
            this.service = new UserService();
        }
    }

    @Override
    public void getUserList(final IUserRepositoryListener listener) {
        service.getAPI().getUserList().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    List<User> data = response.body();

                    if (data != null) {
                        if(listener!=null){
                            listener.userListUpdated(data);
                        }
                    }
                }
                else{
                    if(listener!=null){
                        listener.onError("Erreur durant la récupération des utilisateurs");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                if(listener!=null){
                    listener.onError("Erreur durant la récupération des utilisateurs");
                }
            }
        });

    }

    @Override
    public void getUser(int id, final IUserRepositoryListener listener) {


        service.getAPI().getUserList(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User u = response.body();
                    if (u != null) {
                        if(listener!=null){
                            listener.userFound(u);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if(listener!=null){
                    listener.onError("Utilisateur non trouvé");
                }
            }
        });
    }
}
