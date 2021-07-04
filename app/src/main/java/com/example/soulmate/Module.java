package com.example.soulmate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Module extends AppCompatActivity {

    private MyRecyclerViewAdapter adapter;
    SQLiteDatabase mydatabase;
    private final int quantity = 8;
    private static final String MODULE = "module";
    private int module;
//    final String CONTENT = "pic001-талия-허리(хори)\n" +
//            "pic002-цветок-꽃(кот’)\n" +
//            "pic003-сумка-가방(кабан)\n" +
//            "pic004-гора-산(сан)\n" +
//            "pic005-книга-책(чек’)\n" +
//            "pic006-муха-파리(пари)\n" +
//            "pic007-курица-닭(так’)\n" +
//            "pic008-штаны-바지(пачи)\n" +
//            "pic009-зонтик-우산(усан)\n" +
//            "pic010-лиса-여우(ёу)\n" +
//            "pic011-поцелуй-키스(кхисы)\n" +
//            "pic012-тетрадь-노트(нотхы)\n" +
//            "pic013-песок-모래(морэ)\n" +
//            "pic014-человек-사람(сарам)\n" +
//            "pic015-лес-숲(суп’)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ArrayList<Img> info = new ArrayList<>();
        ArrayList<Integer> images = new ArrayList<>();
        ArrayList<String> textTrans = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<String> desc = new ArrayList<>();
        ImageButton back = findViewById(R.id.moduleBack);
        ImageButton cards = findViewById(R.id.moduleCards);
        ImageButton test = findViewById(R.id.moduleTest);
        TextView cardDesc = findViewById(R.id.cardDescription);
        module = getIntent().getIntExtra(MODULE, 0);
        cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module.this, Cards.class);
                intent.putExtra(MODULE, module);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module.this, Test.class);
                intent.putExtra(MODULE, module);
                startActivity(intent);
            }
        });
        cardDesc.setVisibility(View.INVISIBLE);

        Thread t = new Thread(){
            @Override
            public void run() {
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                mydatabase = databaseHelper.getReadableDatabase();
                Cursor cursor = mydatabase.rawQuery("SELECT Image, Russian, Translate, Transcript, Description FROM content;", null);
                cursor.moveToFirst();
                switch (module){
                    case 1:
                        cursor.moveToPosition(0);
                        break;
                    case 2:
                        cursor.moveToPosition(8);
                        break;
                    case 3:
                        cursor.moveToPosition(16);
                        break;
                    case 4:
                        cursor.moveToPosition(24);
                        break;
                    case 5:
                        cursor.moveToPosition(32);
                        break;
                }
                for (int i = 0; i < quantity; i++){
                    Img img = new Img(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                    info.add(img);
                    desc.add(cursor.getString(4));
                    cursor.moveToNext();
                }
                for (int i = 0; i < info.size(); i++){
                    images.add(getApplicationContext().getResources().getIdentifier(info.get(i).getPath(),"raw", getApplicationContext().getPackageName()));
                    texts.add(info.get(i).getRussian() + " - " + info.get(i).getTranslate() + "(" + info.get(i).getTranscript() + ")");
                    textTrans.add(texts.get(i));
                }
                cursor.close();
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(Module.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new MyRecyclerViewAdapter(getApplicationContext(), images, textTrans);
        recyclerView.setAdapter(adapter);recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                cardDesc.setVisibility(View.INVISIBLE);
            }
        });
        adapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                for (int i = 0; i < adapter.getItemCount(); i++){
                    if (adapter.getItem(position).equals(texts.get(i))){
                        if (cardDesc.isShown()){
                            cardDesc.setVisibility(View.INVISIBLE);
                        } else {
                            cardDesc.setVisibility(View.VISIBLE);
                        }
                        cardDesc.setText(desc.get(i));
                    }
                }
            }
        });
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);



        // data to populate the RecyclerView with
//        images.add(getApplicationContext().getResources().getIdentifier("pic001","raw", getApplicationContext().getPackageName()));
//        images.add(getApplicationContext().getResources().getIdentifier("pic002","raw", getApplicationContext().getPackageName()));
//        images.add(getApplicationContext().getResources().getIdentifier("pic003","raw", getApplicationContext().getPackageName()));
//        images.add(getApplicationContext().getResources().getIdentifier("pic004","raw", getApplicationContext().getPackageName()));
//        images.add(getApplicationContext().getResources().getIdentifier("pic005","raw", getApplicationContext().getPackageName()));
//
//        textTrans.add("pic001-талия-허리(хори)");
//        textTrans.add("pic002-цветок-꽃(кот)");
//        textTrans.add("pic003-сумка-가방(кабан)");
//        textTrans.add("pic004-гора-산(сан)");
//        textTrans.add("pic005-книга-책(чек)");
//        ImageView img = findViewById(R.id.imageView3);
//        int rid = getApplicationContext().getResources().getIdentifier("pic004","raw", getApplicationContext().getPackageName());
//        img.setImageResource(rid);

        // set up the RecyclerView
//        LinearLayoutManager horizontalLayoutManager
//                = new LinearLayoutManager(Module.this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManager);
//        adapter = new MyRecyclerViewAdapter(this, images, textTrans);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
    }

}

class Img{
    String path;
    String russian;
    String translate;
    String transcript;

    public Img(String path, String russian, String translate, String transcript) {
        this.path = path;
        this.russian = russian;
        this.translate = translate;
        this.transcript = transcript;
    }

    public String getPath() {
        return path;
    }

    public String getRussian() {
        return russian;
    }

    public String getTranslate() {
        return translate;
    }

    public String getTranscript() {
        return transcript;
    }
}