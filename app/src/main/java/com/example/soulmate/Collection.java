package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class Collection extends AppCompatActivity {
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ImageButton back = findViewById(R.id.collectionBack);
        ListView lv = findViewById(R.id.collectionList);
        CollectionItem[] collections = makeArray();
        CollectionAdapter adapter = new CollectionAdapter(getApplicationContext(), collections);
        lv.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    CollectionItem[] makeArray(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        mydatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = mydatabase.rawQuery("SELECT Russian, Translate, Collection FROM content WHERE Collection = 1;", null);
        int count = cursor.getCount();
        String[] arrRussian = new String[count];
        String[] arrKorean = new String[count];
        cursor.moveToFirst();
        for (int i = 0; i < count; i++){
            arrRussian[i] = cursor.getString(0);
            arrKorean[i] = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();
        CollectionItem[] collections = new CollectionItem[count];
        CollectionItem item;
        for (int i = 0; i < count; i++){
            item = new CollectionItem(arrRussian[i], arrKorean[i]);
            collections[i] = item;
        }
        return collections;
    }
}