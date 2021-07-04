package com.example.soulmate;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CollectionAdapter extends ArrayAdapter<CollectionItem> {

    public CollectionAdapter(@NonNull Context context, @NonNull CollectionItem[] objects) {
        super(context, R.layout.collection_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CollectionItem collectionItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.collection_item, null);
        }
        ((TextView) convertView.findViewById(R.id.textViewRussian)).setText(collectionItem.getRussian());
        ((TextView) convertView.findViewById(R.id.textViewKorean)).setText(collectionItem.getKorean());
        return convertView;
    }
}
