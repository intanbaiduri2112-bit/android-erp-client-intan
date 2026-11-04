package com.example.mynoesantara;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.mynoesantara.viewmodel.ProspekViewModel;

public class LoginActivity extends AppCompatActivity {
    private ProspekViewModel viewModel;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        viewModel = new ViewModelProvider(this).get(ProspekViewModel.class);

        viewModel.getLoginStatus().observe(this, isSuccess -> {
            if (isSuccess) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                String token = viewModel.getToken().getValue();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("TOKEN", token);
                startActivity(intent);
                finish();
            }
        });

        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Isi semua field!", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.loginUser(email, password);
        });
    }
}