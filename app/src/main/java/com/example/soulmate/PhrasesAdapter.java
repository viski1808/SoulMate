package com.example.soulmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PhrasesAdapter extends ArrayAdapter<PhrasesItem> {

    public PhrasesAdapter(@NonNull Context context, @NonNull PhrasesItem[] objects) {
        super(context, R.layout.phrases_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PhrasesItem phrasesItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.phrases_item, null);
        }
        ((TextView) convertView.findViewById(R.id.textViewPhrase)).setText(phrasesItem.getPhrase());
        ((TextView) convertView.findViewById(R.id.textViewEx1)).setText(phrasesItem.getEx1());
        ((TextView) convertView.findViewById(R.id.textViewEx2)).setText(phrasesItem.getEx2());
        ((TextView) convertView.findViewById(R.id.textViewEx3)).setText(phrasesItem.getEx3());
        ((TextView) convertView.findViewById(R.id.textViewEx4)).setText(phrasesItem.getEx4());
        ((TextView) convertView.findViewById(R.id.textViewEx5)).setText(phrasesItem.getEx5());
        return convertView;
    }
}
