package com.example.h071211010_finalmobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.h071211010_finalmobile.Model.MovieModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmoviecatalogue";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.DatabaseEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.DatabaseEntry.DROP_TABLE);
        onCreate(db);
    }

    public long insertMovie(MovieModel movieModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_TITLE, movieModel.getOriginal_title());
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_POSTER, movieModel.getPoster_path());
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW, movieModel.getOverview());
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE, movieModel.getRelease_date());
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE, movieModel.getVote_average());
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL, movieModel.getBackdropUrl());
        contentValues.put(DatabaseContract.DatabaseEntry.COLUMN_GENRE_IDS, movieModel.getId());

        return db.insert(DatabaseContract.DatabaseEntry.TABLE_NAME, null, contentValues);

    }

    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DatabaseContract.DatabaseEntry._ID,
                DatabaseContract.DatabaseEntry.COLUMN_TITLE,
                DatabaseContract.DatabaseEntry.COLUMN_POSTER,
                DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW,
                DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE,
                DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE,
                DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL,
                DatabaseContract.DatabaseEntry.COLUMN_GENRE_IDS
        };
        return db.query(DatabaseContract.DatabaseEntry.TABLE_NAME, projection, null, null, null, null, null);
    }
    public int deleteMovie(String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = DatabaseContract.DatabaseEntry.COLUMN_TITLE + " = ?";

        return db.delete(DatabaseContract.DatabaseEntry.TABLE_NAME, selection, new String[]{nama});
    }
    public boolean checkMovie(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM " + DatabaseContract.DatabaseEntry.TABLE_NAME + " WHERE " + DatabaseContract.DatabaseEntry.COLUMN_TITLE + " = ?";
            String[] selectionArgs = {String.valueOf(title)};
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.getCount() > 0) {
                return true;
            }
        } finally {
            if (cursor != null){
                cursor.close();
            }
        }
        return false;
    }
}
