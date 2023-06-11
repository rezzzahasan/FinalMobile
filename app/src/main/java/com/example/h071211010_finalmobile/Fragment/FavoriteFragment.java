package com.example.h071211010_finalmobile.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.h071211010_finalmobile.Adapter.FavoriteAdapter;
import com.example.h071211010_finalmobile.Database.DatabaseContract;
import com.example.h071211010_finalmobile.Database.DatabaseHelper;
import com.example.h071211010_finalmobile.Model.FavoriteModel;
import com.example.h071211010_finalmobile.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    RecyclerView rvFavorite;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavorite = view.findViewById(R.id.rvFavorite);
        progressBar = view.findViewById(R.id.progressBarFav);
        hideProgressBar();
        List<FavoriteModel> favoriteList = getFavorite();
        rvFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(favoriteList);
        rvFavorite.setAdapter(favoriteAdapter);
    }

    private List<FavoriteModel> getFavorite() {
        List<FavoriteModel> favoriteList = new ArrayList<>();
        DatabaseHelper databaseSQL = new DatabaseHelper(getActivity());
        Cursor cursor = databaseSQL.getAllMovies();

        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_TITLE);
            int overviewColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW);
            int posterColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_POSTER);
            int backdropUrlColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL);
            int releaseColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE);
            int voteAverageColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE);

            do {
                int id = (idColumnIndex != -1) ? cursor.getInt(idColumnIndex) : -1;
                String title = (titleColumnIndex != -1) ? cursor.getString(titleColumnIndex) : null;
                String release = (releaseColumnIndex != -1) ? cursor.getString(releaseColumnIndex) : null;
                String overview = (overviewColumnIndex != -1) ? cursor.getString(overviewColumnIndex) : null;
                String poster = (posterColumnIndex != -1) ? cursor.getString(posterColumnIndex) : null;
                String backdropUrl = (backdropUrlColumnIndex != -1) ? cursor.getString(backdropUrlColumnIndex) : null;
                String voteAverage = (voteAverageColumnIndex != -1) ? cursor.getString(voteAverageColumnIndex) : null;

                FavoriteModel favoriteModel = new FavoriteModel(id, title, overview, poster, backdropUrl, release, voteAverage);
                favoriteList.add(favoriteModel);
            } while (cursor.moveToNext());

        }
        if (cursor != null) {
            cursor.close();
        }
        return favoriteList;
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}