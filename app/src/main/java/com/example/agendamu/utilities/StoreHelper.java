package com.example.agendamu.utilities;

import com.example.agendamu.model.EventModel;
import com.example.agendamu.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreHelper {
    private static List<EventModel> events;

    // fetch events from API
    public static void fetchEvents() {
        RetrofitService retrofitService = RetrofitService.getInstance();
        Call<List<EventModel>> call = retrofitService.getEvents();
        call.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                int statusCode = response.code();
//                System.out.println("onResponse: " + statusCode);
                events = response.body();
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });
    }

    // return events
    public static List<EventModel> getEvents() {
        return events;
    }
}
