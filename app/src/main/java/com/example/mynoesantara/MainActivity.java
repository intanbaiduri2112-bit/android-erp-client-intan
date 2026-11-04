package com.example.mynoesantara;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mynoesantara.viewmodel.ProspekViewModel;

public class MainActivity extends AppCompatActivity {
    private ProspekViewModel viewModel;
    private RecyclerView recyclerView;
    private String token; // Simpan token sebagai variabel global di class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ProspekViewModel.class);

        // Ambil token dari Intent
        token = getIntent().getStringExtra("TOKEN");

        viewModel.getListProspek().observe(this, prospeks -> {
            ProspekAdapter adapter = new ProspekAdapter(prospeks);
            recyclerView.setAdapter(adapter);
        });

        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        // --- TAMBAHKAN LOGIKA TOMBOL LOGOUT ---
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            if (token != null) {
                // Panggil API logout
                viewModel.logoutUser(token);

                // Pindah kembali ke halaman Login
                Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                // Hapus riwayat activity agar tidak bisa "Back" ke halaman list
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        // --------------------------------------

        if (token != null) {
            viewModel.fetchProspek(token);
        } else {
            Toast.makeText(this, "Token tidak ditemukan, silakan login ulang", Toast.LENGTH_SHORT).show();
        }
    }
}