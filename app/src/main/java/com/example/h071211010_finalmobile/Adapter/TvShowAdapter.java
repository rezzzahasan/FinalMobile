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
import com.example.h071211010_finalmobile.Model.TvShowModel;
import com.example.h071211010_finalmobile.R;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {
    private final ArrayList<TvShowModel> tvShows;

    public TvShowAdapter(ArrayList<TvShowModel> tvShows) {
        this.tvShows = tvShows;
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
        TvShowModel tvShow = tvShows.get(position);
        holder.setData(tvShow, context);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
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

        public void setData(TvShowModel tvShow, Context context) {
            tvTitle.setText(tvShow.getOriginal_name());
            tvRilis.setText(tvShow.getFirst_air_date());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + tvShow.getPoster_path())
                    .into(ivFilm);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("TvShow", tvShow);
                    context.startActivity(intent);
                }
            });
        }
    }
}
