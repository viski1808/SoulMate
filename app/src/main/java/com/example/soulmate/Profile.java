package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    final String SAVED_PASSWORD = "PASSWORD";
    final String SAVED_LOGIN = "LOGIN";
    final String SAVED_NAME = "NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageButton back = findViewById(R.id.profileBack);
        sharedPreferences = getSharedPreferences("SignIn",MODE_PRIVATE);
        String savedLogin = sharedPreferences.getString(SAVED_LOGIN, "");
        String savedName = sharedPreferences.getString(SAVED_NAME, "");
        TextView profileLogin = findViewById(R.id.profileLogin);
        TextView profileName = findViewById(R.id.profileName);
        profileLogin.setText(savedLogin);
        profileName.setText(savedName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageButton exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}