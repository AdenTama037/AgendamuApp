package com.example.agendamu.retrofit;

import com.example.agendamu.model.EventModel;
import com.example.agendamu.model.UserModel;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiEndpoints {


    @GET("events")
    Call<List<EventModel>> getEvents();

    @GET("events/{id}")
    Call<EventModel> getEventById(@Path("id") int eventId);

    @POST("events")
    Call<EventModel> createEvent(@Body EventModel event);

    @PUT("events/{id}")
    Call<EventModel> updateEvent(@Path("id") int eventId, @Body EventModel event);

    @DELETE("events/{id}")
    Call<EventModel> deleteEvent(@Path("id") int eventId);

    @FormUrlEncoded
    @POST("login")
    Call<UserModel> login(@Field("email") String email, @Field("password") String password);

}
