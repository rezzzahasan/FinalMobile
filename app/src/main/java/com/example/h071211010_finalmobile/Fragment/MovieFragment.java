package com.example.h071211010_finalmobile.Fragment;

import android.graphics.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.h071211010_finalmobile.Adapter.MovieAdapter;
import com.example.h071211010_finalmobile.Connect.ApiService;
import com.example.h071211010_finalmobile.Connect.Connect;
import com.example.h071211010_finalmobile.Model.MovieModel;
import com.example.h071211010_finalmobile.R;
import com.example.h071211010_finalmobile.Response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieFragment extends Fragment {
    ProgressBar progressBar;
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        rvMovie = view.findViewById(R.id.rvMovie);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService movieService = retrofit.create(ApiService.class);
        Call<MovieResponse> movies = Connect.getApiService().getMovie();
        movies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<MovieModel> movies = response.body().getData();
                        movieAdapter = new MovieAdapter((ArrayList<MovieModel>) movies);
                        rvMovie.setAdapter(movieAdapter);
                        progressBar.setVisibility(View.GONE);
                        int count = 2;
                        rvMovie.setLayoutManager(new androidx.recyclerview.widget.GridLayoutManager(getContext(), count));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}