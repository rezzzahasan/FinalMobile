package com.example.h071211010_finalmobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211010_finalmobile.DetailActivity;
import com.example.h071211010_finalmobile.Model.MovieModel;
import com.example.h071211010_finalmobile.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ArrayList<MovieModel> movies;

    public MovieAdapter(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        MovieModel movie = movies.get(position);
        holder.setData(movie, context);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvRilis;
        private ImageView ivFilm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRilis = itemView.findViewById(R.id.tvRilis);
            ivFilm = itemView.findViewById(R.id.ivFilm);
        }

        public void setData(MovieModel movie, Context context) {
            tvTitle.setText(movie.getOriginal_title());
            tvRilis.setText(movie.getRelease_date());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                    .into(ivFilm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movie", movie);
                    context.startActivity(intent);
                }
            });
        }
    }
}
