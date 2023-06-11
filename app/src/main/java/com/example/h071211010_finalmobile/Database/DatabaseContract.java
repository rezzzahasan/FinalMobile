package com.example.h071211010_finalmobile.Database;

import android.provider.BaseColumns;

public class DatabaseContract {

    private DatabaseContract() {
    }
    public static class DatabaseEntry implements BaseColumns {

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_BACKDROP_URL = "backdrop_url";
        public static final String COLUMN_GENRE_IDS ="genre_ids" ;

        // Ini adalah query untuk membuat tabel
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_POSTER + " TEXT," +
                COLUMN_OVERVIEW + " TEXT," +
                COLUMN_RELEASE_DATE + " TEXT," +
                COLUMN_BACKDROP_URL + " TEXT," +
                COLUMN_VOTE_AVERAGE + " TEXT, " +
                COLUMN_GENRE_IDS + " TEXT)";

        // Ini adalah query untuk menghapus tabel
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
