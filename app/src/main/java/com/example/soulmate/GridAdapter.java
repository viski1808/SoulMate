package com.example.soulmate;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Collections;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private Integer mCols, mRows;
    private ArrayList<String> arrPict; // массив картинок
    private static enum Status {CELL_OPEN, CELL_CLOSE, CELL_DELETE}
    private ArrayList<Status> arrStatus;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase mydatabase;
    private Cursor cursor;
    private int mModule = 0;
    private int step = 0;

    public GridAdapter(Context context, int cols, int rows, int module) {
        mContext = context;
        mCols = cols;
        mRows = rows;
        mModule = module;
        switch (mModule){
            case 1:
                step = 0;
                break;
            case 2:
                step = 8;
                break;
            case 3:
                step = 16;
                break;
            case 4:
                step = 24;
                break;
            case 5:
                step = 32;
                break;
        }
        arrPict = new ArrayList<>();
        arrStatus = new ArrayList<>();
        // добавляем
        databaseHelper = new DatabaseHelper(mContext);
        mydatabase = databaseHelper.getReadableDatabase();
        cursor = mydatabase.rawQuery("SELECT Russian, Translate FROM content;", null);
        // Метод заполняющий массив vecPict
        makePictArray();
        // Метод устанавливающий всем ячейкам статус CELL_CLOSE
        closeAllCells();
    }

    private void closeAllCells() {
        arrStatus.clear();
        for (int i = 0; i < mCols * mRows; i++)
            arrStatus.add(Status.CELL_CLOSE);
    }

    private void makePictArray () {
        // очищаем вектор
        arrPict.clear();
        cursor.moveToFirst();
        cursor.moveToPosition(step);
        for (int i = 0; i < ((mCols * mRows) / 2); i++)
        {
            arrPict.add(cursor.getString(0));
            arrPict.add(cursor.getString(1));
            cursor.moveToNext();
        }
        // перемешиваем
        Collections.shuffle(arrPict);
    }

    public void checkOpenCells() {
        int first = arrStatus.indexOf(Status.CELL_OPEN);
        int second = arrStatus.lastIndexOf(Status.CELL_OPEN);
        if (first == second)
            return;
        String word1 = arrPict.get(first);
        String word2 = arrPict.get(second);
        cursor.moveToFirst();
        cursor.moveToPosition(step);
        for (int i = 0; i < ((mCols * mRows) / 2); i++){
            if (word1.equals(cursor.getString(0)) && word2.equals(cursor.getString(1))){
                arrStatus.set(first, Status.CELL_DELETE);
                arrStatus.set(second, Status.CELL_DELETE);
                break;
            } else  if (word1.equals(cursor.getString(1)) && word2.equals(cursor.getString(0))){
                arrStatus.set(first, Status.CELL_DELETE);
                arrStatus.set(second, Status.CELL_DELETE);
                break;
            }
            if (i == ((mCols * mRows) / 2) - 1){
                arrStatus.set(first, Status.CELL_CLOSE);
                arrStatus.set(second, Status.CELL_CLOSE);
            }
            cursor.moveToNext();
        }
    }

    public boolean  openCell(int position) {
        if (arrStatus.get(position) == Status.CELL_DELETE
                || arrStatus.get(position) == Status.CELL_OPEN)
            return false;
        if (arrStatus.get(position) != Status.CELL_DELETE)
            arrStatus.set(position, Status.CELL_OPEN);
        notifyDataSetChanged();
        return true;
    }

    public boolean checkGameOver() {
        return !arrStatus.contains(Status.CELL_CLOSE);
    }

    @Override
    public int getCount() {
        return mCols*mRows;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        CardView view; // для вывода картинки
        if (convertView == null)
            view = new CardView(mContext);
        else
            view = (CardView) convertView;
        TextView txt = new TextView(mContext);
        txt.setText(arrPict.get(i));
        txt.setTextSize(18);
        txt.setTextColor(Color.BLACK);
        txt.setGravity(Gravity.CENTER);
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        view.setMinimumHeight((height - mRows*20)/mRows);
        view.setMinimumWidth((width - mCols*20)/mCols);
        view.addView(txt);
        switch (arrStatus.get(i)){
            case CELL_CLOSE:
                view.setBackgroundColor(Color.rgb(0, 200, 0));
                break;
            case CELL_OPEN:
                view.setBackgroundColor(Color.rgb(200, 0, 0));
                break;
        }
        return view;
    }
}
