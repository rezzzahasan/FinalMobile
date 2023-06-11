package com.example.h071211010_finalmobile.Connect;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connect {
    public static final String BASE_URL = "https://api.themoviedb.org/";


    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}

