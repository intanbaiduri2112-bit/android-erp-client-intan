package com.example.mynoesantara.repository;

import com.example.mynoesantara.api.ApiService;
import com.example.mynoesantara.models.LoginRequest;
import com.example.mynoesantara.models.LoginResponse;
import com.example.mynoesantara.models.Prospek;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProspekRepository {
    private ApiService apiService;
    // PENTING: 10.0.2.2 untuk Emulator. Ganti dengan IP Laptop jika pakai HP fisik
    private static final String BASE_URL = "http://192.168.11.176:8000/";

    public ProspekRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public Call<LoginResponse> login(String email, String password) {
        return apiService.login(new LoginRequest(email, password));
    }

    public Call<List<Prospek>> getProspek(String token) {
        return apiService.getProspek("Bearer " + token);
    }
    public Call<LoginResponse> logout(String token) {
        return apiService.logout("Bearer " + token);
    }
}