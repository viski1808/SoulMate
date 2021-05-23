package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    final String SAVED_NAME = "NAME";
    final String SAVED_LOGIN = "LOGIN";
    final String SAVED_PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ImageButton sign = findViewById(R.id.sign);
        EditText name = findViewById(R.id.signinName);
        EditText login = findViewById(R.id.signinLogin);
        EditText password = findViewById(R.id.signinPassword);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SAVED_NAME, name.getText().toString());
                editor.putString(SAVED_LOGIN, login.getText().toString());
                editor.putString(SAVED_PASSWORD, password.getText().toString());
                editor.apply();
                Intent intent = new Intent(SignIn.this, LogIn.class);
                startActivity(intent);
            }
        });
        ImageButton back = findViewById(R.id.signinBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}