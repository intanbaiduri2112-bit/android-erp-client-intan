package com.example.mynoesantara.api;

import com.example.mynoesantara.models.LoginRequest;
import com.example.mynoesantara.models.LoginResponse;
import com.example.mynoesantara.models.Prospek;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("api/prospek")
    Call<List<Prospek>> getProspek(@Header("Authorization") String token);

    @POST("api/prospek")
    Call<Prospek> createProspek(@Header("Authorization") String token, @Body Prospek prospek);

    @POST("api/logout")
    Call<LoginResponse> logout(@Header("Authorization") String token);
}