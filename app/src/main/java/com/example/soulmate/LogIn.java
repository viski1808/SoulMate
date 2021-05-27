package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    final String SAVED_LOGIN = "LOGIN";
    final String SAVED_PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ImageButton log = findViewById(R.id.log);
        EditText login = findViewById(R.id.loginLogin);
        EditText password = findViewById(R.id.loginPassword);
        sharedPreferences = getSharedPreferences("SignIn",MODE_PRIVATE);
        String savedLogin = sharedPreferences.getString(SAVED_LOGIN, "");
        String savedPassword = sharedPreferences.getString(SAVED_PASSWORD, "");
        if(savedLogin.equals("")){
            Intent intent = new Intent(LogIn.this, SignIn.class);
            startActivity(intent);
        }
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (login.getText().toString().equals(savedLogin) && password.getText().toString().equals(savedPassword)){
                    Intent intent = new Intent(LogIn.this, MainMenu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Неправильный логин/пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageButton back = findViewById(R.id.loginBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}