package com.example.sjj.help4reword.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sjj.help4reword.bean.MissionListBean;

/**
 * Created by sjj on 2018/4/14.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String MISSION_TITLE = "mission_title";
    private static final String MISSION_DATE = "mission_date";
    private static final String MISSION_REWORD = "mission_reword";
    private static final String MISSION_CONTEXT = "mission_context";
    private static final String MISSION_LOCATION = "mission_location";
    private static final String MISSION = "mission";


    public DatabaseHelper(Context context) {
        super(context,MISSION,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mission("+
                "id integer primary key autoincrement, "+
                "title varchar(20), "+
                "reword varchar(20), "+
                "location varchar(200)"+
                "context text(200), "+
                "date varchar(20))"
        );
    }

    public void insertMission(MissionListBean missionListBean){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MISSION_TITLE, missionListBean.title);
        cv.put(MISSION_REWORD, missionListBean.reword);
        cv.put(MISSION_CONTEXT, missionListBean.context);
        cv.put(MISSION_LOCATION, missionListBean.location);
        cv.put(MISSION_DATE, missionListBean.time);
        database.insert(MISSION,null,cv);
    }

    public Cursor getAllMissionData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("MISSION",null,null,null,null,null,MISSION_DATE +"ASC");
    }

    public void deleteAllDate(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("MISSION",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
