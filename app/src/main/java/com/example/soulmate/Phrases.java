package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Phrases extends AppCompatActivity {
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        ImageButton back = findViewById(R.id.phrasesBack);
        ListView lv = findViewById(R.id.phrasesList);
        PhrasesItem[] phrases = makeArray();
        PhrasesAdapter adapter = new PhrasesAdapter(getApplicationContext(), phrases);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                View v = view.findViewById(R.id.phrasesExs);
                if(v.isShown()){
                    v.setVisibility(View.GONE);
                }
                else{
                    v.setVisibility(View.VISIBLE);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    PhrasesItem[] makeArray(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        mydatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = mydatabase.rawQuery("SELECT Phrase, Ex1, Ex2, Ex3, Ex4, Ex5 FROM phrase", null);
        int count = cursor.getCount();
        String[] arrPhrase = new String[count];
        String[] arrEx1 = new String[count];
        String[] arrEx2 = new String[count];
        String[] arrEx3 = new String[count];
        String[] arrEx4 = new String[count];
        String[] arrEx5 = new String[count];
        cursor.moveToFirst();
        for (int i = 0; i < count; i++){
            arrPhrase[i] = cursor.getString(0);
            arrEx1[i] = cursor.getString(1);
            arrEx2[i] = cursor.getString(2);
            arrEx3[i] = cursor.getString(3);
            arrEx4[i] = cursor.getString(4);
            arrEx5[i] = cursor.getString(5);
            cursor.moveToNext();
        }
        cursor.close();
        PhrasesItem[] phrases = new PhrasesItem[count];
        PhrasesItem item;
        for (int i = 0; i < count; i++){
            item = new PhrasesItem(arrPhrase[i], arrEx1[i], arrEx2[i], arrEx3[i], arrEx4[i], arrEx5[i]);
            phrases[i] = item;
        }
        return  phrases;
    }
}