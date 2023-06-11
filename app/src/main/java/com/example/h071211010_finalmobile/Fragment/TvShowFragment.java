package com.example.h071211010_finalmobile.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h071211010_finalmobile.Adapter.MovieAdapter;
import com.example.h071211010_finalmobile.Adapter.TvShowAdapter;
import com.example.h071211010_finalmobile.Connect.ApiService;
import com.example.h071211010_finalmobile.Connect.Connect;
import com.example.h071211010_finalmobile.Model.MovieModel;
import com.example.h071211010_finalmobile.Model.TvShowModel;
import com.example.h071211010_finalmobile.R;
import com.example.h071211010_finalmobile.Response.MovieResponse;
import com.example.h071211010_finalmobile.Response.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TvShowFragment extends Fragment {
    ProgressBar progressBar;
    private TvShowAdapter tvShowAdapter;
    private RecyclerView rvTvShow;
    private TextView tvAlert;
    private ImageView btnRefresh;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        progressBar = view.findViewById(R.id.progressBarTvShow);
        rvTvShow = view.findViewById(R.id.rvTvShow);
        tvAlert = view.findViewById(R.id.tvAlert);
        btnRefresh = view.findViewById(R.id.btnRefresh);
        showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService Service = retrofit.create(ApiService.class);
        Call<TvShowResponse> tvShows = Connect.getApiService().getTvShow();
        tvShows.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
               if (response.isSuccessful()){
                   goneLoading();
                   TvShowResponse tvShowResponse = response.body();
                     List<TvShowModel> tvShowModels = tvShowResponse.getResults();
                        tvShowAdapter = new TvShowAdapter((ArrayList<TvShowModel>) tvShowModels);
                        rvTvShow.setAdapter(tvShowAdapter);
                        int count = 2;
                   GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), count);
                        rvTvShow.setLayoutManager(gridLayoutManager);
               }
               else {
                   showAlert();
                   Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void showAlert() {
        btnRefresh.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvAlert.setVisibility(View.VISIBLE);
        rvTvShow.setVisibility(View.GONE);
    }
    private void goneLoading() {
        btnRefresh.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tvAlert.setVisibility(View.GONE);
        rvTvShow.setVisibility(View.VISIBLE);
    }
    private void showLoading() {
        btnRefresh.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        tvAlert.setVisibility(View.GONE);
        rvTvShow.setVisibility(View.GONE);
    }
}


