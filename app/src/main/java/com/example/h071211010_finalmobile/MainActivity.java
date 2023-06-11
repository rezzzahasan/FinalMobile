package com.example.h071211010_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h071211010_finalmobile.Fragment.FavoriteFragment;
import com.example.h071211010_finalmobile.Fragment.MovieFragment;
import com.example.h071211010_finalmobile.Fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    ImageView btnMovie, btnFavorite, btnTvShow;
    FragmentManager fragmentManager;
    TextView tvTittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTittle = findViewById(R.id.tvTittle);
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
                    .addToBackStack(null)
                    .commit();
        }

        navigationListener();
    }

    private void navigationListener() {
        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTittle.setText("Movie");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 200);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new MovieFragment(), MovieFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });
        btnTvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTittle.setText("TV Show");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 200);

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new TvShowFragment(), TvShowFragment.class.getSimpleName())
                        .commit();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTittle.setText("Favorites");
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new FavoriteFragment(), FavoriteFragment.class.getSimpleName())
                        .commit();
            }
        });
    }
}