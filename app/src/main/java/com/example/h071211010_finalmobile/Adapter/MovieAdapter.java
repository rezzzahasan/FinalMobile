package com.example.h071211010_finalmobile.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211010_finalmobile.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    TextView tvTitle,tvRilis;
    ImageView ivFilm;


    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRilis = itemView.findViewById(R.id.tvRilis);
            ivFilm = itemView.findViewById(R.id.ivFilm);
        }
    }
}
