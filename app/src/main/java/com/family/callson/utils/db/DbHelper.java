package com.family.callson.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wengshinan on 2015/6/20.
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "callson.db";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_TABLE_USER = "";
    private static final String SQL_UPDATE_TABLE_USER = "";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_UPDATE_TABLE_USER);
    }
}
