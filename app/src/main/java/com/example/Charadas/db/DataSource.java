package com.example.Charadas.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
    private SQLiteDatabase adaBaziDatabase;
    private DatabaseHelper databaseHelper;
    private Context mContext;

    public DataSource(Context context) {
        mContext = context;
        databaseHelper = new DatabaseHelper(mContext);
    }

    public void open() throws SQLException {
        adaBaziDatabase = databaseHelper.getReadableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public void insert() {

    }

    public Cursor select(String category, int score) {

        Cursor cursor = adaBaziDatabase.query(
                databaseHelper.WORDS_TABLE,
                new String[] { "WORD" },
                "category=? and score= ?", // where clause
                new String[] { category, String.valueOf(score) }, //where params
                null, //groupby
                null, //having
                "random()", //orderby
                "1"
        );
        return cursor;
    }
}
