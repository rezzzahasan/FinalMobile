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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvAlert;
    ImageView btnRefresh;
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        tvAlert = view.findViewById(R.id.tvAlert);
        btnRefresh = view.findViewById(R.id.btnRefresh);
        rvMovie = view.findViewById(R.id.rvMovie);
        showLoading();
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
                        goneLoading();
                        List<MovieModel> movies = response.body().getData();
                        movieAdapter = new MovieAdapter((ArrayList<MovieModel>) movies);
                        rvMovie.setAdapter(movieAdapter);
                        progressBar.setVisibility(View.GONE);
                        int count = 2;
                        rvMovie.setLayoutManager(new androidx.recyclerview.widget.GridLayoutManager(getContext(), count));
                    } else {
                        showAlert();
                        Toast.makeText(getActivity(), "Failed to get data !", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
        return view;
    }

    private void showAlert() {
        btnRefresh.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvAlert.setVisibility(View.VISIBLE);
        rvMovie.setVisibility(View.GONE);
    }
    private void goneLoading() {
        btnRefresh.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tvAlert.setVisibility(View.GONE);
        rvMovie.setVisibility(View.VISIBLE);
    }
    private void showLoading() {
        btnRefresh.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        tvAlert.setVisibility(View.GONE);
        rvMovie.setVisibility(View.GONE);
    }

}