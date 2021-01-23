package com.example.agendamu.retrofit;


import com.example.agendamu.model.EventModel;
import com.example.agendamu.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private ApiEndpoints api;
    public static final String BASE_URL = "http://10.0.2.2:8000/api/";

    private static RetrofitService service;

    private RetrofitService() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiEndpoints.class);
    }

    public ApiEndpoints getAPIInterface() {
        return this.api;
    }

    public static RetrofitService getInstance() {
        if (service == null) {
            service = new RetrofitService();
        }
        return service;
    }

    public Call<List<EventModel>> getEvents() {
        return api.getEvents();
    }

    public Call<UserModel> login(String email, String password) {
        return api.login(email, password);
    }
}
