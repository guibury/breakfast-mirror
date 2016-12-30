package com.jovensprofissionais.breakfastitismytreat.repository;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Guilherme Bury
 * Date: 30/12/16
 */

public class CreateHistoryOfWhatPersonBroughtDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "breakfast.db";
    private static final String TABLE = "ranking";
    private static final String DATE = "date";
    private static final String FOOD = "food";
    private static final int VERSAO = 1;

    public CreateHistoryOfWhatPersonBroughtDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CreateHistoryOfWhatPersonBroughtDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}