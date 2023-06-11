package com.example.h071211010_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.h071211010_finalmobile.Database.DatabaseHelper;
import com.example.h071211010_finalmobile.Model.FavoriteModel;
import com.example.h071211010_finalmobile.Model.MovieModel;
import com.example.h071211010_finalmobile.Model.TvShowModel;

public class DetailActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ImageView ivBack, ivPoster, ivBackground, ivFav;
    private TextView tvTittle, tvVote, tvOverview, tvRilis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBackground = findViewById(R.id.ivBackground);
        ivPoster = findViewById(R.id.ivPoster);
        ivFav = findViewById(R.id.btnFav);
        ivBack = findViewById(R.id.btnBack);
        tvTittle = findViewById(R.id.tvTittle);
        tvRilis = findViewById(R.id.tvRilis);
        tvVote = findViewById(R.id.tvVote);
        tvOverview = findViewById(R.id.tvOverview);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            MovieModel movie = intent.getParcelableExtra("movie");
            showMovieDetails(movie);
        } else if (intent.getParcelableExtra("TvShow") != null) {
            TvShowModel tvShow = intent.getParcelableExtra("TvShow");
            showTvShowDetails(tvShow);
        } else if (intent.getParcelableExtra("favorite") != null) {
            FavoriteModel favorite = intent.getParcelableExtra("favorite");
            showFavoriteDetails(favorite);
        }

        ivBack.setOnClickListener(v -> onBackPressed());
    }

    private void showMovieDetails(MovieModel movie) {
        tvTittle.setText(movie.getOriginal_title());
        tvRilis.setText(movie.getRelease_date());
        tvVote.setText(movie.getVote_average());
        tvOverview.setText(movie.getOverview());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .into(ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.getBackdrop_path())
                .into(ivBackground);

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!databaseHelper.checkMovie(movie.getOriginal_title())) {
                    addMovieToFavorite(movie.getId(), movie.getOriginal_title(), movie.getPoster_path(), movie.getBackdrop_path(), movie.getRelease_date(), movie.getVote_average(), movie.getOverview());
                } else {
                    deleteMovieFromFavorite(movie.getOriginal_title());
                }
            }
        });
    }

    private void showTvShowDetails(TvShowModel tvShow) {
        tvTittle.setText(tvShow.getOriginal_name());
        tvRilis.setText(tvShow.getFirst_air_date());
        tvVote.setText(tvShow.getVote_average());
        tvOverview.setText(tvShow.getOverview());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tvShow.getPoster_path())
                .into(ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + tvShow.getBackdrop_path())
                .into(ivBackground);

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!databaseHelper.checkMovie(tvShow.getOriginal_name())) {
                    addMovieToFavorite(tvShow.getId(), tvShow.getOriginal_name(), tvShow.getPoster_path(), tvShow.getBackdrop_path(), tvShow.getFirst_air_date(), tvShow.getVote_average(), tvShow.getOverview());
                } else {
                    deleteMovieFromFavorite(tvShow.getOriginal_name());
                }
            }
        });
    }

    private void showFavoriteDetails(FavoriteModel favorite) {
        tvTittle.setText(favorite.getOriginal_title());
        tvRilis.setText(favorite.getRelease_date());
        tvVote.setText(favorite.getVote_average());
        tvOverview.setText(favorite.getOverview());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + favorite.getPoster_path())
                .into(ivPoster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + favorite.getBackdropUrl())
                .into(ivBackground);

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMovieFromFavorite(favorite.getOriginal_title());
            }
        });
    }

    private void addMovieToFavorite(int id, String title, String poster, String backdrop, String release, String vote, String overview) {
        MovieModel movieModel = new MovieModel(id, title, poster, backdrop, release, vote, overview);
        long result = databaseHelper.insertMovie(movieModel);
        if (result != -1) {
            Toast.makeText(this, "Berhasil Menambahkan ke Favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal Menambahkan ke Favorite", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMovieFromFavorite(String title) {
        long result = databaseHelper.deleteMovie(title);
        if (result != -1) {
            Toast.makeText(this, "Berhasil Menghapus dari Favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal Menghapus dari Favorite", Toast.LENGTH_SHORT).show();
        }
    }
}
