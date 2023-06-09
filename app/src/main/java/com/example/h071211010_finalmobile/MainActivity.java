package com.example.h071211010_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.h071211010_finalmobile.Fragment.FavoriteFragment;
import com.example.h071211010_finalmobile.Fragment.MovieFragment;
import com.example.h071211010_finalmobile.Fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    ImageView btnMovie, btnFavorite, btnTvShow;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMovie = findViewById(R.id.btnMovie);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnTvShow = findViewById(R.id.btnTvShow);
        fragmentManager = getSupportFragmentManager();

        MovieFragment movieFragment = new MovieFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());
        if (!(fragment instanceof MovieFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frameContainer, movieFragment, MovieFragment.class.getSimpleName())
                    .commit();
        }

        navigationListener();
    }

    private void navigationListener() {
        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new MovieFragment(), MovieFragment.class.getSimpleName())
                        .commit();
            }
        });
        btnTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new TvShowFragment(), TvShowFragment.class.getSimpleName())
                        .commit();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new FavoriteFragment(), FavoriteFragment.class.getSimpleName())
                        .commit();
            }
        });
    }
}