package com.example.h071211010_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView btnMovie, btnFavorite, btnTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnMovie = findViewById(R.id.btnMovie);
        btnFavorite =  findViewById(R.id.btnFavorite);
        btnTvShow = findViewById(R.id.btnTvShow);


    }
}