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
import com.example.h071211010_finalmobile.Model.FavoriteModel;
import com.example.h071211010_finalmobile.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteModel> favorites;

    public FavoriteAdapter(List<FavoriteModel> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        FavoriteModel favorite = favorites.get(position);
        holder.setData(favorite, context);
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTittle, tvRilis;
        private ImageView ivFilm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTittle = itemView.findViewById(R.id.tvTittleFavorite);
            tvRilis = itemView.findViewById(R.id.tvRilisFavorite);
            ivFilm = itemView.findViewById(R.id.ivFilmFavorite);
        }

        public void setData(FavoriteModel favorite, Context context) {
            String title = favorite.getRelease_date();
            String rilis = favorite.getPoster_path();
            String poster = favorite.getOriginal_title();
            tvTittle.setText(title);
            tvRilis.setText(rilis);
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + poster)
                    .into(ivFilm);

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("favorite", favorite);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
