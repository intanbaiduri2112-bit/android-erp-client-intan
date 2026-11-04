package com.example.mynoesantara.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynoesantara.models.LoginResponse;
import com.example.mynoesantara.models.Prospek;
import com.example.mynoesantara.repository.ProspekRepository;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProspekViewModel extends ViewModel {
    private ProspekRepository repository;

    private MutableLiveData<List<Prospek>> listProspek = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();
    private MutableLiveData<String> tokenLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public ProspekViewModel() {
        repository = new ProspekRepository();
    }

    public LiveData<List<Prospek>> getListProspek() { return listProspek; }
    public LiveData<Boolean> getLoginStatus() { return loginStatus; }
    public LiveData<String> getToken() { return tokenLiveData; }
    public LiveData<String> getErrorMessage() { return errorMessage; }

    public void loginUser(String email, String password) {
        repository.login(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tokenLiveData.setValue(response.body().getAccessToken());
                    loginStatus.setValue(true);
                } else {
                    loginStatus.setValue(false);
                    errorMessage.setValue("Email atau Password salah");
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginStatus.setValue(false);
                errorMessage.setValue("Gagal koneksi: " + t.getMessage());
            }
        });
    }

    public void fetchProspek(String token) {
        repository.getProspek(token).enqueue(new Callback<List<Prospek>>() {
            @Override
            public void onResponse(Call<List<Prospek>> call, Response<List<Prospek>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listProspek.setValue(response.body());
                } else {
                    errorMessage.setValue("Gagal mengambil data prospek");
                }
            }
            @Override
            public void onFailure(Call<List<Prospek>> call, Throwable t) {
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }
    public void logoutUser(String token) {
        repository.logout(token).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // Apapun hasilnya, kita anggap logout berhasil di sisi aplikasi
                loginStatus.setValue(false);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                errorMessage.setValue("Gagal logout: " + t.getMessage());
            }
        });
    }
}