package com.example.h071211010_finalmobile.Connect;

import com.example.h071211010_finalmobile.Response.MovieResponse;
import com.example.h071211010_finalmobile.Response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    public static final String API_KEY = "65fb7de38e5a4ca28fa762d588e20e8e";

    @GET("3/movie/popular?api_key=" + API_KEY)
    Call<MovieResponse> getMovie();
    @GET("3/tv/top_rated?api_key=" + API_KEY)
    Call<TvShowResponse> getTvShow();
}
