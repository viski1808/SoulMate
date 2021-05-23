package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Test extends AppCompatActivity {
    private final int quantity = 8;
    private static final String MODULE = "module";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView question1 = findViewById(R.id.question1);
        TextView question2 = findViewById(R.id.question2);
        TextView question3 = findViewById(R.id.question3);
        TextView question4 = findViewById(R.id.question4);
        TextView question5 = findViewById(R.id.question5);
        TextView question6 = findViewById(R.id.question6);
        TextView question7 = findViewById(R.id.question7);
        TextView question8 = findViewById(R.id.question8);
        TextView[] questionsTextView = {question1, question2, question3, question4, question5, question6, question7, question8};

        EditText answer1 = findViewById(R.id.answer1);
        EditText answer2 = findViewById(R.id.answer2);
        EditText answer3 = findViewById(R.id.answer3);
        EditText answer4 = findViewById(R.id.answer4);
        EditText answer5 = findViewById(R.id.answer5);
        EditText answer6 = findViewById(R.id.answer6);
        EditText answer7 = findViewById(R.id.answer7);
        EditText answer8 = findViewById(R.id.answer8);

        EditText[] answersEditText = {answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8};

        int module = getIntent().getIntExtra(MODULE, 0);
        int row = 0;

        ImageButton back = findViewById(R.id.testBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Test.this, Modules.class);
                startActivity(intent);
            }
        });

        ImageButton check = findViewById(R.id.testCheck);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase mydatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = mydatabase.rawQuery("SELECT Russian, Translate FROM content;", null);
        cursor.moveToFirst();
        switch (module){
            case 1:
                row = 0;
                break;
            case 2:
                row = 8;
                break;
        }
        cursor.moveToPosition(row);
        ArrayList<Integer> questions = new ArrayList<>();
        for (int i = 0; i < quantity; i++){
            questions.add(i);
        }
        Collections.shuffle(questions);
        for (int i = 0; i < quantity; i++){
            cursor.move(questions.get(i));
            questionsTextView[i].setText(cursor.getString(0));
            cursor.moveToPosition(row);
        }

        boolean[] correct_answer = new boolean[quantity];
        for (int i = 0; i < quantity; i++){
            correct_answer[i] = false;
        }
        int finalRow = row;
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < quantity; i++){
                    cursor.move(questions.get(i));
                    if (answersEditText[i].getText().toString().equals(cursor.getString(1))){
                        answersEditText[i].setTextColor(Color.GREEN);
                        correct_answer[i] = true;
                    } else {
                        answersEditText[i].setTextColor(Color.RED);
                    }
                    cursor.moveToPosition(finalRow);
                }
            }
        });
    }
}