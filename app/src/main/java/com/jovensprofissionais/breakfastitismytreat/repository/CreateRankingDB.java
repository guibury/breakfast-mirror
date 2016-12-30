package com.jovensprofissionais.breakfastitismytreat.repository;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Thiago Osiro
 * Date: 30/12/16
 */

public class CreateRankingDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "breakfast.db";
    private static final String RANKING_TABLE = "ranking";
    private static final String ID_RANKING = "id_ranking";
    private static final String NAME = "name";
    private static final String RATING = "rating";
    private static final int VERSION = 1;

    public CreateRankingDB(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ RANKING_TABLE +"("
                + ID_RANKING + " integer primary key autoincrement, "
                + NAME + " varchar2(20), "
                + RATING + " long"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + RANKING_TABLE);
        onCreate(db);
    }

    public CreateRankingDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CreateRankingDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static String getRankingTable() {
        return RANKING_TABLE;
    }

    public static String getIdRanking() {
        return ID_RANKING;
    }

    public static String getRATING() {
        return RATING;
    }

    public static int getVERSION() {
        return VERSION;
    }

    public static String getNAME() {
        return NAME;
    }
}