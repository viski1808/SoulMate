package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Modules extends AppCompatActivity {
    private static final String MODULE = "module";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        ImageButton back = findViewById(R.id.modulesBack);
        TextView lesson1 = findViewById(R.id.lesson1);
        TextView lesson2 = findViewById(R.id.lesson2);
        TextView lesson3 = findViewById(R.id.lesson3);
        TextView lesson4 = findViewById(R.id.lesson4);
        TextView lesson5 = findViewById(R.id.lesson5);
        lesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lesson 1", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Modules.this, Module.class);
                intent.putExtra(MODULE, 1);
                startActivity(intent);
            }
        });
        lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lesson 2", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Modules.this, Module.class);
                intent.putExtra(MODULE, 2);
                startActivity(intent);
            }
        });
        lesson3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lesson 3", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Modules.this, Module.class);
                intent.putExtra(MODULE, 3);
                startActivity(intent);
            }
        });
        lesson4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lesson 4", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Modules.this, Module.class);
                intent.putExtra(MODULE, 4);
                startActivity(intent);
            }
        });
        lesson5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lesson 5", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Modules.this, Module.class);
                intent.putExtra(MODULE, 5);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}