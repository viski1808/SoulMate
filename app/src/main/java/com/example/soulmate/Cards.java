package com.example.soulmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Cards extends AppCompatActivity {
    private GridView mGrid;
    private GridAdapter mAdapter;
    private TextView mStepScreen;
    private Chronometer mTimeScreen;
    private Integer StepCount; // кол-во ходов
    private static final String MODULE = "module";
    private int module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        mTimeScreen = findViewById(R.id.timeview);
        mStepScreen = findViewById(R.id.stepview);
        ImageButton back = findViewById(R.id.cardsBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        module = getIntent().getIntExtra(MODULE, 0);
        StepCount = 0;
        mStepScreen.setText(StepCount.toString());

        mTimeScreen.start();

        mGrid = (GridView)findViewById(R.id.field);
        mGrid.setNumColumns(4);
        mGrid.setEnabled(true);

        mAdapter = new GridAdapter(this, 4, 4, module);
        mGrid.setAdapter(mAdapter);

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter.checkOpenCells();
                if (mAdapter.openCell(i)) {
                    StepCount ++;
                    mStepScreen.setText(StepCount.toString());
                }
                if (mAdapter.checkGameOver()) {
                    mTimeScreen.stop();
                    String time = mTimeScreen.getText().toString();
                    String TextToast = "Игра закончена \nХодов: " + StepCount.toString() + "\nВремя: " + time;
                    Toast.makeText(getApplicationContext(), TextToast, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}