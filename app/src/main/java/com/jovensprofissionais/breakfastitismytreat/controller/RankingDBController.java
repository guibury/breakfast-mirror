package com.jovensprofissionais.breakfastitismytreat.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.jovensprofissionais.breakfastitismytreat.repository.CreateRankingDB;

/**
 * Created with IntelliJ IDEA.
 * User: Thiago Osiro
 * Date: 30/12/16
 */

public class RankingDBController {

    private static final String CLASS_NAME = "RankingDBController";
    private static final String ERROR = "error";
    private CreateRankingDB rankingDB;
    private SQLiteDatabase sqlDB;

    public RankingDBController(Context context){
        rankingDB = new CreateRankingDB(context);
    }

    public boolean insert(String name, float rating){

        ContentValues values;

        sqlDB = rankingDB.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateRankingDB.getNAME(), name);
        values.put(CreateRankingDB.getRATING(), rating);

        try{
            sqlDB.insert(CreateRankingDB.getRankingTable(), null, values);
            sqlDB.close();
            return true;
        } catch (Exception e) {
            Log.e(CLASS_NAME, ERROR + e);
            sqlDB.close();
        }
        return false;
    }
}