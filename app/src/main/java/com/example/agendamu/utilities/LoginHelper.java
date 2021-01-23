package com.example.agendamu.utilities;

import com.example.agendamu.model.EventModel;
import com.example.agendamu.model.UserModel;
import com.example.agendamu.retrofit.RetrofitService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginHelper {
    private static UserModel currentUser;

    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserModel currentUser) {
        LoginHelper.currentUser = currentUser;
    }

    public static void logout() {
        FirebaseAuth.getInstance().signOut();
        ActivityHelper.getMainActivity().finishAndRemoveTask();
    }

    public static void login(String email, String password) {
        RetrofitService retrofitService = RetrofitService.getInstance();
        Call<UserModel> call = retrofitService.login(email, password);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                int statusCode = response.code();
//                System.out.println("onResponse: " + statusCode);
                currentUser = response.body();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });
    }
}

