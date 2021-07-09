package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ImageButton profile = findViewById(R.id.menuProfile);
        ImageButton phrases = findViewById(R.id.menuPhrases);
        ImageButton modules = findViewById(R.id.menuModules);
        ImageButton collection = findViewById(R.id.menuCollection);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Profile.class);
                startActivity(intent);
            }
        });
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Phrases.class);
                startActivity(intent);
            }
        });
        modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Modules.class);
                startActivity(intent);
            }
        });
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Collection.class);
                startActivity(intent);
            }
        });
    }
}