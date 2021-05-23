package com.example.soulmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Integer> mImages;
    private List<String> mTextTrans;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Integer> images, List<String> textTrans) {
        this.mInflater = LayoutInflater.from(context);
        this.mImages = images;
        this.mTextTrans = textTrans;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int images = mImages.get(position);
        String textTrans = mTextTrans.get(position);
        holder.myView.setImageResource(images);
        holder.myTextView.setText(textTrans);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mTextTrans.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;
        EasyFlipView myEasyFlipView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imageFrontCard);
            myTextView = itemView.findViewById(R.id.textBackCard);
            myEasyFlipView = itemView.findViewById(R.id.myEasyFlipView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myEasyFlipView.flipTheView();
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mTextTrans.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
